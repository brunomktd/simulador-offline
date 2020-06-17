package br.com.embracon.service;

import br.com.embracon.controller.request.PlanosRequest;
import br.com.embracon.controller.response.PlanosResponse;
import br.com.embracon.model.Grupo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlanosService {
    Grupo criarPlano(PlanosRequest planosRequest);

    List<PlanosResponse> obterTodosPlanos(Pageable paginacao);
}
