package br.com.embracon.service;

import br.com.embracon.controller.response.PlanosResponse;

import java.util.List;

public interface SimulacaoService {

    List<PlanosResponse> obterPlanos(Integer idBem, Integer creditoSolicitado);
}
