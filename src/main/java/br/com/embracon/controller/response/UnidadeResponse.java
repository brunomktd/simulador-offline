package br.com.embracon.controller.response;

import br.com.embracon.model.Grupo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class UnidadeResponse {
    private Integer id;
    private Integer codigoUnidade;
    private String nomeUnidade;
    private List<Grupo> grupos = new ArrayList<>();
}
