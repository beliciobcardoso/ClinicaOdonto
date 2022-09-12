package com.indentados.clinicaodonto.model;

import lombok.*;

import javax.management.ConstructorParameters;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor // não necessário pois pode zoar a regra de negócio
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "dentista")

public class Dentista extends Pessoa{
    @Column
    private String matricula;
    
    public Dentista(String nome, String sobre, Endereco endereco, String email, String matricula)
    {
        //Apenas para fins de teste. ignore --Teteu
        super(null, nome, sobre, endereco, email, Timestamp.valueOf(LocalDateTime.now()));
        this.matricula = matricula;
    }
}
