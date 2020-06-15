package br.com.embracon.controller;

import br.com.embracon.controller.response.ProdutoResponse;
import br.com.embracon.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoResponse>> index(){
        var produtos =  produtoService.obterTodosProdutos();
        return ResponseEntity.ok(produtos);
    }
}
