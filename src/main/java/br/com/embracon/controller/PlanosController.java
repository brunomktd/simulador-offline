package br.com.embracon.controller;

import br.com.embracon.common.utils.MapperUtil;
import br.com.embracon.controller.request.PlanosRequest;
import br.com.embracon.controller.response.PlanosResponse;
import br.com.embracon.service.PlanosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/planos")
public class PlanosController {

    @Autowired
    private PlanosService planosService;

    @Autowired
    private MapperUtil mapperUtil;

    @PostMapping
    public ResponseEntity<PlanosResponse> create(@RequestBody PlanosRequest planosRequest, UriComponentsBuilder uriBuilder){
        var plano = mapperUtil.map(planosService.execute(planosRequest), PlanosResponse.class);

        URI uri = uriBuilder.path("/{id}").buildAndExpand(plano.getId()).toUri();
        return ResponseEntity.created(uri).body(plano);
    }


}