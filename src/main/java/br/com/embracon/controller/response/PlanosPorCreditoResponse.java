package br.com.embracon.controller.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PlanosPorCreditoResponse {
    private Integer id;
    private String bem;
    private Integer grupo;
    private Integer credito;
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
