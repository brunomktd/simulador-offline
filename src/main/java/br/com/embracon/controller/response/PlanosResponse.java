package br.com.embracon.controller.response;

import br.com.embracon.model.Produto;
import br.com.embracon.model.Unidade;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PlanosResponse {
    private Integer id;
    private Produto bem;
    private Integer grupo;
    private Integer creditoMin;
    private Integer creditoMax;
    private Integer prazo;
    private Integer prazoComercial;
    private Integer qtdParticipantes;
    private BigDecimal taxa;
    private Boolean status;
    private LocalDate vencimento;
    private Unidade unidade;
}
