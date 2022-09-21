package com.indentados.clinicaodonto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column
    protected String nome;
    @Column
    protected String sobrenome;

    @Column
    protected String email;
    @Column(name = "data_de_cadastro")
    protected Timestamp dataCadastro = Timestamp.valueOf(LocalDateTime.now());

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    protected Endereco endereco;
}
