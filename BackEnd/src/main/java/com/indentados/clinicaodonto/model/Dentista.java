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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dentista")

public class Dentista extends Pessoa{

    @Column
    private String matricula;

    public Dentista( String nome, String sobrenome, String email, Endereco endereco, String matricula) {
        super(null, nome, sobrenome, email, Timestamp.valueOf(LocalDateTime.now()), endereco);
        this.matricula = matricula;
    }

    //    public Dentista(String nome, String sobre, Endereco endereco, String email, String matricula)
//    {
//        //Apenas para fins de teste. ignore --Teteu
//        super(null, nome, sobre, endereco, email, Timestamp.valueOf(LocalDateTime.now()));
//        this.matricula = matricula;
//    }
    
}
