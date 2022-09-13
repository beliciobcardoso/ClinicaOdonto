package com.indentados.clinicaodonto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "dentista")

public class Dentista extends Pessoa{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String matricula;
    
    public Dentista(String nome, String sobre, Endereco endereco, String email, String matricula)
    {
        //Apenas para fins de teste. ignore --Teteu
        super(null, nome, sobre, endereco, email, Timestamp.valueOf(LocalDateTime.now()));
        this.matricula = matricula;
    }
}
