package br.com.embracon.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Unidade {
    @Id
    @Column(unique = true)
    private Integer codigoUnidade;
    private String nomeUnidade;
}
