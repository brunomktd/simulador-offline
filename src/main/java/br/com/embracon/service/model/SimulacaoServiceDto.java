package br.com.embracon.service.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SimulacaoServiceDto {
    private Integer id;
    private String bem;
    private Integer grupo;
    private Integer creditoMin;
    private Integer creditoMax;
    private Integer prazo;
    private Integer prazoComercial;
    private Integer qtdParticipantes;
    private Double taxa;
    private Boolean status;
    private LocalDate vencimento;
    private Integer codigoUnidade;
    private String parcelaAntes;
    private String parcelaDepois;
}
