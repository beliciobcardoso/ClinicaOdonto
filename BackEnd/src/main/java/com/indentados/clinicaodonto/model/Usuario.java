package com.indentados.clinicaodonto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @NotNull
    @NotBlank
    @Size(min = 4, max = 6)
    @Column(unique = true)
    private String nomeDeUsuario;

    @NotEmpty
    @NotNull
    @NotBlank
    @Size(min = 4, max = 6)
    @Column(unique = true)
    private String senha;


}
