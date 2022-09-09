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
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column
    public String nome;
    @Column
    public String sobrenome;

    @OneToOne
    @JoinColumn(name = "id_endereco")
    public Endereco endereco;

    @Column
    public String email;
    @Column(name = "data_de_cadastro")
    private Timestamp dataCadastro = Timestamp.valueOf(LocalDateTime.now());
}
