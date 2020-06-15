package br.com.embracon.service;

import br.com.embracon.controller.response.PlanosResponse;
import br.com.embracon.controller.response.ProdutoResponse;

import java.util.List;
import java.util.stream.Stream;

public interface SimulacaoService {
    Stream<ProdutoResponse> obterTiposDeBens();

    List<PlanosResponse> obterPlanos(Integer idBem, Integer creditoSolicitado);
}
