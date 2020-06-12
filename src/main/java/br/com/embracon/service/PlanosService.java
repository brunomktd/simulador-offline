package br.com.embracon.service;

import br.com.embracon.controller.request.PlanosRequest;
import br.com.embracon.model.Grupo;

public interface PlanosService {
    Grupo execute(PlanosRequest planosRequest);
}
