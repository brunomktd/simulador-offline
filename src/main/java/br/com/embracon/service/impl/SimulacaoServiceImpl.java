package br.com.embracon.service.impl;

import br.com.embracon.common.utils.CalculosUtil;
import br.com.embracon.common.utils.MapperUtil;
import br.com.embracon.controller.response.PlanosResponse;
import br.com.embracon.model.Grupo;
import br.com.embracon.repository.GrupoRepository;
import br.com.embracon.service.SimulacaoService;
import br.com.embracon.service.model.SimulacaoServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
public class SimulacaoServiceImpl implements SimulacaoService {

    @Autowired
    private MapperUtil mapperUtil;
    @Autowired
    private CalculosUtil calculosUtil;
    @Autowired
    private GrupoRepository grupoRepository;

    public List<PlanosResponse> obterPlanos(Integer idBem, Integer creditoSolicitado) {
        if(creditoSolicitado == null){
            return obterPlanosPorBem(idBem).stream().map(p -> mapperUtil.map(p, PlanosResponse.class)).collect(toList());
        } else {
            var planosEncontrados = obterPlanosPorBem(idBem);
            var listDePlanosAscCreditoMax = planosEncontrados.stream().filter(p -> p.getCreditoMax() <= creditoSolicitado).collect(toList());

            var planos = new ArrayList<>();

            if(listDePlanosAscCreditoMax.size() == 0){
                var novoPlano = montaPlanosComCreditoMin(planosEncontrados);
                planos.add(novoPlano);
                return planos.stream().map(pl -> mapperUtil.map(pl, PlanosResponse.class)).collect(toList());
            }

            var planoDecrecente = listDePlanosAscCreditoMax
                    .stream().max(Comparator.comparing(SimulacaoServiceDto::getCreditoMax)).get();

            Integer creditoMaxDoGrupo = planoDecrecente.getCreditoMax();


            var count = 0;
            var novoCredito = 0;

            for(var i = creditoMaxDoGrupo; i <= creditoSolicitado; i+=creditoMaxDoGrupo ){
                novoCredito += creditoMaxDoGrupo;
                count += 1;
                var novoPlano = montaPlanosComCreditoMax(planoDecrecente);
                planos.add(novoPlano);
//                planos.add(planoDecrecente);
            }

            var restante = creditoSolicitado - novoCredito;

            var primeiroItemDaLista = listDePlanosAscCreditoMax.stream().findFirst().get();

            if(restante > 0 ){
                if(restante >= primeiroItemDaLista.getCreditoMin() && restante <= primeiroItemDaLista.getCreditoMax()){
                    planos.add(montaPlanosComCreditoRestante(primeiroItemDaLista, restante));
                } else if(restante <= primeiroItemDaLista.getCreditoMin()){
                    planos.add(montaPlanosComCreditoRestante(primeiroItemDaLista, restante));
                } else if(restante > primeiroItemDaLista.getCreditoMax()) {
                    // Cria um objeto com o que falta para completar
                    var restantePlus = planosEncontrados.stream().filter(p -> restante >= p.getCreditoMin() && restante <= p.getCreditoMax()).findFirst();
                    restantePlus.ifPresent(simulacaoServiceDto -> planos.add(montaPlanosComCreditoRestante(simulacaoServiceDto, restante)));
                }
            }

            return planos.stream().map(p -> mapperUtil.map(p, PlanosResponse.class)).collect(toList());
        }
    }

    private PlanosResponse montaPlanosComCreditoRestante(SimulacaoServiceDto p, Integer restante) {
        PlanosResponse novoPlano = new PlanosResponse();

        novoPlano.setId(p.getId());
        novoPlano.setBem(p.getBem());
        novoPlano.setGrupo(p.getGrupo());
        novoPlano.setCredito(restante);
        novoPlano.setPrazo(p.getPrazo());
        novoPlano.setPrazoComercial(p.getPrazoComercial());
        novoPlano.setQtdParticipantes(p.getQtdParticipantes());
        novoPlano.setTaxa(p.getTaxa());
        novoPlano.setStatus(p.getStatus());
        novoPlano.setVencimento(p.getVencimento());
        novoPlano.setCodigoUnidade(p.getCodigoUnidade());
        novoPlano.setParcelaAntes(calculosUtil.parcelaAntesContemplacao(restante, p.getPrazoComercial()));
        novoPlano.setParcelaDepois(calculosUtil.parcelaPosContemplacao(p.getPrazoComercial(), p.getTaxa(), restante));

        return novoPlano;
    }

    private PlanosResponse montaPlanosComCreditoMax(SimulacaoServiceDto p) {
        PlanosResponse novoPlano = new PlanosResponse();

        novoPlano.setId(p.getId());
        novoPlano.setBem(p.getBem());
        novoPlano.setGrupo(p.getGrupo());
        novoPlano.setCredito(p.getCreditoMax());
        novoPlano.setPrazo(p.getPrazo());
        novoPlano.setPrazoComercial(p.getPrazoComercial());
        novoPlano.setQtdParticipantes(p.getQtdParticipantes());
        novoPlano.setTaxa(p.getTaxa());
        novoPlano.setStatus(p.getStatus());
        novoPlano.setVencimento(p.getVencimento());
        novoPlano.setCodigoUnidade(p.getCodigoUnidade());
        novoPlano.setParcelaAntes(calculosUtil.parcelaAntesContemplacao(p.getCreditoMax(), p.getPrazoComercial()));
        novoPlano.setParcelaDepois(calculosUtil.parcelaPosContemplacao(p.getPrazoComercial(), p.getTaxa(), p.getCreditoMax()));

        return novoPlano;
    }

    private PlanosResponse montaPlanosComCreditoMin(List<SimulacaoServiceDto> planosEncontrados) {
        var plano = planosEncontrados.stream().findFirst();
        if(plano.isPresent()){
            var p = plano.get();

            PlanosResponse novoPlano = new PlanosResponse();

            novoPlano.setId(p.getId());
            novoPlano.setBem(p.getBem());
            novoPlano.setGrupo(p.getGrupo());
            novoPlano.setCredito(p.getCreditoMin());
            novoPlano.setPrazo(p.getPrazo());
            novoPlano.setPrazoComercial(p.getPrazoComercial());
            novoPlano.setQtdParticipantes(p.getQtdParticipantes());
            novoPlano.setTaxa(p.getTaxa());
            novoPlano.setStatus(p.getStatus());
            novoPlano.setVencimento(p.getVencimento());
            novoPlano.setCodigoUnidade(p.getCodigoUnidade());
            novoPlano.setParcelaAntes(calculosUtil.parcelaAntesContemplacao(p.getCreditoMin(), p.getPrazoComercial()));
            novoPlano.setParcelaDepois(calculosUtil.parcelaPosContemplacao(p.getPrazoComercial(), p.getTaxa(), p.getCreditoMin()));

            return novoPlano;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possivel construir os planos");
    }

    private List<SimulacaoServiceDto> obterPlanosPorBem(Integer idBem) {
        return grupoRepository.findAllByBemId(idBem)
                .stream()
                .filter(Grupo::getStatus)
                .map(p -> mapperUtil.map( p, SimulacaoServiceDto.class))
                .peek(this::calculaParcela)
                .collect(toList());
    }

    private void calculaParcela(SimulacaoServiceDto p) {
        p.setParcelaAntes(calculosUtil.parcelaAntesContemplacao(p.getCreditoMax(), p.getPrazoComercial()));
        p.setParcelaDepois(calculosUtil.parcelaPosContemplacao(p.getPrazoComercial(), p.getTaxa(), p.getCreditoMax()));
    }

}
