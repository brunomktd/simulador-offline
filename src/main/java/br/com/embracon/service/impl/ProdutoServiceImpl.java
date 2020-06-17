package br.com.embracon.service.impl;

import br.com.embracon.common.utils.MapperUtil;
import br.com.embracon.controller.response.ProdutoResponse;
import br.com.embracon.repository.ProdutoRepository;
import br.com.embracon.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private MapperUtil mapperUtil;
    @Autowired
    private ProdutoRepository produtoRepository;


    @Override
    public List<ProdutoResponse> obterTodosProdutos() {
        return produtoRepository.findAll().stream().map(produto -> mapperUtil.map(produto, ProdutoResponse.class)).collect(Collectors.toList());
    }
}
