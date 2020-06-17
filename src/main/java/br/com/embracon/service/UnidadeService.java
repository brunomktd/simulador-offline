package br.com.embracon.service;

import br.com.embracon.controller.response.UnidadeResponse;

import java.util.List;

public interface UnidadeService {
    UnidadeResponse obterUnidadeEspecifica(Integer id);

    List<UnidadeResponse> obterTodasUnidades();
}
