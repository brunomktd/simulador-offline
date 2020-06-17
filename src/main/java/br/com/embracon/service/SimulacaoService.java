package br.com.embracon.service;

import br.com.embracon.controller.response.PlanosResponse;
import br.com.embracon.controller.response.PlanosPorCreditoResponse;

import java.util.List;

public interface SimulacaoService {

    List<PlanosPorCreditoResponse> obterPlanos(Integer idBem, Integer creditoSolicitado);

    List<PlanosResponse> obterPlanosPorTipoDeBem(Integer idBem);
}
