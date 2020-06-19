package br.com.embracon.controller.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PlanosRequest {
    @NotNull @ApiModelProperty(value = "46")
    private Integer codigoUnidade;
    @NotNull @NotEmpty @ApiModelProperty(value = "up")
    private String nomeUnidade;
    @NotNull @NotEmpty @ApiModelProperty(value = "serviços")
    private String bem;
    @NotNull @Min(1) @ApiModelProperty(value = "123456")
    private Integer grupo;
    @NotNull @Min(1) @ApiModelProperty(value = "15000")
    private Integer creditoMin;
    @NotNull @Min(1) @ApiModelProperty(value = "30000")
    private Integer creditoMax;
    @NotNull @Min(1) @ApiModelProperty(value = "40")
    private Integer prazo;
    @NotNull @Min(1) @ApiModelProperty(value = "26")
    private Integer prazoComercial;
    @NotNull @Min(1) @ApiModelProperty(value = "400")
    private Integer qtdParticipantes;
    @NotNull @DecimalMin("0.00") @ApiModelProperty(value = "0.45")
    private BigDecimal taxa;
    @NotNull @ApiModelProperty(value = "true ou false")
    private Boolean status;
    @NotNull @ApiModelProperty(value = "2020-05-15")
    private LocalDate vencimento;
}

