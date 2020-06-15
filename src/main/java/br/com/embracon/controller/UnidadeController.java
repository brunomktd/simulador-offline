package br.com.embracon.controller;

import br.com.embracon.controller.response.UnidadeResponse;
import br.com.embracon.service.UnidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UnidadeController {

    @Autowired
    private UnidadeService unidadeService;

    @GetMapping("/unidades/{id}")
    public ResponseEntity<UnidadeResponse> show(@PathVariable Integer id){
        return ResponseEntity.ok(unidadeService.obterUnidadeEspecifica(id));
    }

    @GetMapping("/unidades")
    public ResponseEntity<List<UnidadeResponse>> index(){
        return ResponseEntity.ok(unidadeService.obterTodasUnidades());
    }
}
