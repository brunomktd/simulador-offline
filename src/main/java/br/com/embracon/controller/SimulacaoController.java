package br.com.embracon.controller;

import br.com.embracon.controller.response.PlanosResponse;
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
    public List<PlanosResponse> buscarPlanos(
            @PathVariable Integer idBem,
            @RequestParam(required = false) Integer creditoSolicitado){

         return simulacaoService.obterPlanos(idBem, creditoSolicitado);
    }

}
