package com.indentados.clinicaodonto.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.indentados.clinicaodonto.model.Paciente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class PacienteDTO {

    private String nome;
    private String sobrenome;
    private String email;
    private String rg;

    public PacienteDTO(Paciente paciente){
        this.nome = paciente.getNome();
        this.sobrenome = paciente.getSobrenome();
        this.email = paciente.getEmail();
        this.rg = paciente.getRg();
    }

}
