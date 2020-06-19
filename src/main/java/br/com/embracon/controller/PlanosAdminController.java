package br.com.embracon.controller;

import br.com.embracon.common.utils.MapperUtil;
import br.com.embracon.controller.request.AtualizaPlanosRequest;
import br.com.embracon.controller.request.PlanosRequest;
import br.com.embracon.controller.response.PlanosResponse;
import br.com.embracon.service.PlanosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/admin/planos")
public class PlanosAdminController {

    @Autowired
    private PlanosService planosService;

    @Autowired
    private MapperUtil mapperUtil;

    @PostMapping
    public ResponseEntity<PlanosResponse> criarNovosPlanos(@RequestBody @Valid PlanosRequest planosRequest, UriComponentsBuilder uriBuilder){
        var plano = mapperUtil.map(planosService.criarPlano(planosRequest), PlanosResponse.class);

        URI uri = uriBuilder.path("/{id}").buildAndExpand(plano.getId()).toUri();
        return ResponseEntity.created(uri).body(plano);
    }

    @GetMapping
    public List<PlanosResponse> buscarTodosPlanos(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable paginacao){
        return planosService.obterTodosPlanos(paginacao);
    }

    @PutMapping("/{codigoGrupo}")
    public ResponseEntity<PlanosResponse> atualizaPlano(
            @PathVariable Integer codigoGrupo,
            @RequestBody @Valid AtualizaPlanosRequest atualizaPlanosRequest, UriComponentsBuilder uriBuilder){

        var plano = mapperUtil.map(planosService.atualizaPlano(codigoGrupo, atualizaPlanosRequest), PlanosResponse.class);

        URI uri = uriBuilder.path("/{id}").buildAndExpand(plano.getId()).toUri();
        return ResponseEntity.created(uri).body(plano);
    }
}
