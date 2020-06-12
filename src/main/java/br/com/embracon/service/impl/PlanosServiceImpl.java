package br.com.embracon.service.impl;

import br.com.embracon.common.utils.MapperUtil;
import br.com.embracon.controller.request.PlanosRequest;
import br.com.embracon.model.Grupo;
import br.com.embracon.model.Produto;
import br.com.embracon.model.Unidade;
import br.com.embracon.repository.GrupoRepository;
import br.com.embracon.repository.ProdutoRepository;
import br.com.embracon.repository.UnidadeRepository;
import br.com.embracon.service.PlanosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static java.util.Objects.isNull;

@Service
public class PlanosServiceImpl implements PlanosService {

    @Autowired
    private MapperUtil mapperUtil;

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    @Override
    public Grupo execute(PlanosRequest planosRequest) {
        if(!isNull(planosRequest)){
            planosRequest.setNomeUnidade(planosRequest.getNomeUnidade().toLowerCase());
            planosRequest.setBem(planosRequest.getBem().toLowerCase());
            return verificaGrupo(planosRequest);
        }
        System.out.println("O request enviado está em branco");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O request enviado está em branco");
    }

    private Unidade verificaUnidadeDeNegocio(PlanosRequest planosRequest) {
        var unidade = unidadeRepository.findByCodigoUnidade(planosRequest.getCodigoUnidade());
        if(unidade.isEmpty()){
            var novaUnidade = mapperUtil.map(planosRequest, Unidade.class);
            return unidadeRepository.save(novaUnidade);
        }
        return unidade.get();
    }

    private Produto verificaBemInteresse(PlanosRequest planosRequest) {
        var produto = produtoRepository.findByBem(planosRequest.getBem());
        if(produto.isEmpty()){
            var bem = mapperUtil.map(planosRequest, Produto.class);
            return produtoRepository.save(bem);
        }
        return produto.get();
    }

    private Grupo verificaGrupo(PlanosRequest planosRequest) {
        try {
            var existingGroup = grupoRepository.findByGrupo(planosRequest.getGrupo());
            var unidade = verificaUnidadeDeNegocio(planosRequest);
            var tipoBem = verificaBemInteresse(planosRequest);

            if(existingGroup.isPresent() && existingGroup.get().getUnidade().getCodigoUnidade().equals(unidade.getCodigoUnidade())){
                System.out.println("Grupo já está cadastrado para essa unidade de negócio");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Grupo já está cadastrado para essa unidade de negócio");
            }

            Grupo novoGrupo = mapperUtil.map(planosRequest, Grupo.class);
            novoGrupo.setUnidade(unidade);
            novoGrupo.setBem(tipoBem);

            return grupoRepository.save(novoGrupo);
        } catch (Exception e) {
            System.out.println("Alguma chave primária está duplicada");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Alguma chave primária está duplicada");
        }
    }
}
