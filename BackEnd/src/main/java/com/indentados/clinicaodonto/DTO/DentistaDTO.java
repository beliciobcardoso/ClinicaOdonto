package com.indentados.clinicaodonto.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.indentados.clinicaodonto.model.Dentista;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DentistaDTO {
    //se a Paciente retornar só isso, é melhor fazer uma pessoaDTO, pra evitar repetição de código
    private String nome;
    private String sobrenome;
    private String email;

    public DentistaDTO(Dentista dentista) {
        this.nome = dentista.getNome();
        this.sobrenome = dentista.getSobrenome();
        this.email = dentista.getEmail();
    }
    
    public String toString()
    {
        return this.nome + "\n" +
               this.sobrenome + "\n" +
               this.email;
    }
}
