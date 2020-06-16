package br.com.embracon.service.impl;

import br.com.embracon.common.utils.MapperUtil;
import br.com.embracon.controller.response.PlanosResponse;
import br.com.embracon.model.Grupo;
import br.com.embracon.repository.GrupoRepository;
import br.com.embracon.service.SimulacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
public class SimulacaoServiceImpl implements SimulacaoService {

    @Autowired
    private MapperUtil mapperUtil;
    @Autowired
    private GrupoRepository grupoRepository;

    public List<PlanosResponse> obterPlanos(Integer idBem, Integer creditoSolicitado) {
        if(creditoSolicitado == null){
            return obterPlanosPorBem(idBem);
        } else {
            List<PlanosResponse> planos = new ArrayList<>();
            var planosEncontrados = obterPlanosPorBem(idBem);
            var listDePlanosAscCreditoMax = planosEncontrados.stream().filter(p -> p.getCreditoMax() <= creditoSolicitado).collect(toList());

            if(listDePlanosAscCreditoMax.size() == 0){
                var plano = planosEncontrados.stream().findFirst();
                if(plano.isPresent()){
                    planos.add(plano.get());
                    return planos;
                }
            }

            var primeiroPlanoDesc = listDePlanosAscCreditoMax
                    .stream().max(Comparator.comparing(PlanosResponse::getCreditoMax));

            var primeiroCredito = primeiroPlanoDesc.get().getCreditoMax();

            var count = 0;
            var novoCredito = 0;

            for(var i = primeiroCredito; i <= creditoSolicitado; i+=primeiroCredito ){
                novoCredito += primeiroCredito;
                count += 1;
                planos.add(primeiroPlanoDesc.get());
            }

            var restante = creditoSolicitado - novoCredito;

            if(restante > 0 ){
                if(restante >= listDePlanosAscCreditoMax.stream().findFirst().get().getCreditoMin() && restante <= listDePlanosAscCreditoMax.stream().findFirst().get().getCreditoMax()){
                    System.out.println("O valor da carta de crédito para completar é de: " + restante);
                    planos.add(listDePlanosAscCreditoMax.stream().findFirst().get());
                } else if(restante <= listDePlanosAscCreditoMax.stream().findFirst().get().getCreditoMin()){
                    System.out.println("O valor da carta de crédito para completar é de: " + listDePlanosAscCreditoMax.stream().findFirst().get().getCreditoMin());
                    planos.add(listDePlanosAscCreditoMax.stream().findFirst().get());
                } else if(restante > listDePlanosAscCreditoMax.stream().findFirst().get().getCreditoMax()) {
                    Optional<PlanosResponse> restantePlus = planosEncontrados.stream().filter(p -> restante >= p.getCreditoMin() && restante <= p.getCreditoMax()).findFirst();
                    if(restantePlus.isPresent()){
                        System.out.println("O valor da carta de crédito para completar é de: " + restante);
                        planos.add(restantePlus.get());
                    }
                }
            }

            return planos;
        }
    }

    private List<PlanosResponse> obterPlanosPorBem(Integer idBem) {
        return grupoRepository.findAllByBemId(idBem)
                .stream()
                .filter(Grupo::getStatus)
                .map(p -> mapperUtil.map( p, PlanosResponse.class))
                .peek(this::calculaParcela)
                .collect(toList());
    }

    private void calculaParcela(PlanosResponse p) {
        p.setParcelaAntes(new DecimalFormat("#,##0.00").format(p.getCreditoMax() / p.getPrazo()));
        p.setParcelaDepois(new DecimalFormat("#,##0.00").format(parcelaPosContemplacao(p)));
    }

    private double parcelaPosContemplacao(PlanosResponse p) {
        return ((((p.getPrazoComercial() * p.getTaxa()) * p.getCreditoMax())/100)+p.getCreditoMax())/p.getPrazoComercial();
    }


}
