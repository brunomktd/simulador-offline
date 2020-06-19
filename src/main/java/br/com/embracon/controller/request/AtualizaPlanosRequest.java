package br.com.embracon.controller.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AtualizaPlanosRequest {
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

