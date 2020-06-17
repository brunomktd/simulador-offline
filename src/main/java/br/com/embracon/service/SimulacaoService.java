package br.com.embracon.service;

import br.com.embracon.controller.response.PlanosPorBensResponse;
import br.com.embracon.controller.response.PlanosPorCreditoResponse;

import java.util.List;

public interface SimulacaoService {

    List<PlanosPorCreditoResponse> obterPlanos(Integer idBem, Integer creditoSolicitado);

    List<PlanosPorBensResponse> obterPlanosPorTipoDeBem(Integer idBem);
}
