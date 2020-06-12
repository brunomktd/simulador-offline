package br.com.embracon.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String bem;

}
