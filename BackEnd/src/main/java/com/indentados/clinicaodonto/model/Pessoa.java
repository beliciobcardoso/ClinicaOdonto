package com.indentados.clinicaodonto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {
    //faltou o recebimento do usuário como atributo da pessoa ou ao menos uma pk de user
    public Long id; //não vejo necessidade de um tipo tão grande
    public String nome;
    public String sobrenome;
    public Endereco endereco;
    public String email;
    public Timestamp dataCadastro;
}
