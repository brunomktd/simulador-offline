package br.com.embracon.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Grupo {
    @Id
    @SequenceGenerator(name = "grupos_gen", sequenceName = "grupos_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grupos_gen")
    private Integer id;
    private Integer grupo;
    private Integer creditoMin;
    private Integer creditoMax;
    private Integer prazo;
    private Integer prazoComercial;
    private Integer qtdParticipantes;
    private Double taxa;
    private Boolean status;
    private LocalDate vencimento;

    @ManyToOne
    @JoinColumn(name = "unidade_id")
    private Unidade unidade;

    @ManyToOne
    @JoinColumn(name = "bem_id")
    private Produto bem;

}
