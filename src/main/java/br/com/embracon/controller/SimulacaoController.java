package br.com.embracon.controller;

import br.com.embracon.controller.response.PlanosResponse;
import br.com.embracon.controller.response.PlanosPorCreditoResponse;
import br.com.embracon.service.SimulacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/simulacoes")
public class SimulacaoController {

    @Autowired
    private SimulacaoService simulacaoService;

    @GetMapping("/planos/{idBem}")
    public List<PlanosResponse> buscarPlanosPorIdBem(
            @PathVariable Integer idBem){

        return simulacaoService.obterPlanosPorTipoDeBem(idBem);
    }

    @GetMapping("/planos/{idBem}/creditos")
    public List<PlanosPorCreditoResponse> buscarPlanosPorCreditos(
            @PathVariable Integer idBem,
            @RequestParam Integer creditoSolicitado){

         return simulacaoService.obterPlanos(idBem, creditoSolicitado);
    }

}
