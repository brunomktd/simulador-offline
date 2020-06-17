package br.com.embracon.service;

import br.com.embracon.controller.response.ProdutoResponse;

import java.util.List;

public interface ProdutoService {

    List<ProdutoResponse> obterTodosProdutos();
}
