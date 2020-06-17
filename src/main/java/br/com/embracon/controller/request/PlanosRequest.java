package br.com.embracon.controller.request;

import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PlanosRequest {
    @NotNull
    private Integer codigoUnidade;
    @NotNull @NotEmpty
    private String nomeUnidade;
    @NotNull @NotEmpty
    private String bem;
    @NotNull @Min(1)
    private Integer grupo;
    @NotNull @Min(1)
    private Integer creditoMin;
    @NotNull @Min(1)
    private Integer creditoMax;
    @NotNull @Min(1)
    private Integer prazo;
    @NotNull @Min(1)
    private Integer prazoComercial;
    @NotNull @Min(1)
    private Integer qtdParticipantes;
    @NotNull @DecimalMin("0.00")
    private BigDecimal taxa;
    @NotNull
    private Boolean status;
    @NotNull
    private LocalDate vencimento;
}

