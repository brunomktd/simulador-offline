package br.com.embracon.common.utils;

import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.text.DecimalFormat;

@Component("calculos")
public class CalculosUtil {
    private DecimalFormat decimalFormat;

    public CalculosUtil(){
        this.decimalFormat = new DecimalFormat("#,##0.00");
    }

    public String parcelaAntesContemplacao(Integer credito, Integer prazo) {
        return decimalFormat.format(credito / prazo);
    }

    public String parcelaPosContemplacao(Integer prazoComercial, Double taxa, Integer credito) {
        return  decimalFormat.format(((prazoComercial * taxa * credito) / 100 + credito)/ prazoComercial);
    }
}
