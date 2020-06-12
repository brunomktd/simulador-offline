package br.com.embracon.controller.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PlanosRequest {
    private Integer codigoUnidade;
    private String nomeUnidade;
    private String bem;
    private Integer grupo;
    private Integer creditoMin;
    private Integer creditoMax;
    private Integer prazo;
    private Integer prazoComercial;
    private Integer qtdParticipantes;
    private BigDecimal taxa;
    private Boolean status;
    private LocalDate vencimento;
}

