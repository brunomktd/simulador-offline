package br.com.embracon.service.impl;

import br.com.embracon.common.utils.MapperUtil;
import br.com.embracon.controller.response.UnidadeResponse;
import br.com.embracon.model.Unidade;
import br.com.embracon.repository.UnidadeRepository;
import br.com.embracon.service.UnidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UnidadeServiceImpl implements UnidadeService {
    @Autowired
    private MapperUtil mapperUtil;
    @Autowired
    private UnidadeRepository unidadeRepository;

    @Override
    public UnidadeResponse obterUnidadeEspecifica(Integer id) {
        Optional<Unidade> unidade = unidadeRepository.findById(id);
        if (unidade.isPresent()) {
            return mapperUtil.map(unidade.get(), UnidadeResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id da unidade não existe");
        }
    }

    @Override
    public List<UnidadeResponse> obterTodasUnidades() {
        List<Unidade> listaDeUnidades = unidadeRepository.findAll();
        return listaDeUnidades.stream().map(unidade -> mapperUtil.map(unidade, UnidadeResponse.class)).collect(Collectors.toList());
    }
}
