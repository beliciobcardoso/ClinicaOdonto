package com.indentados.clinicaodonto.DTO;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.indentados.clinicaodonto.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoDTO {

    private String rua;
    private String numero;
    private String cidade;
    private String estado;


    public EnderecoDTO(Endereco endereco) {
        this.rua = endereco.getRua();
        this.numero = endereco.getNumero();
        this.cidade = endereco.getCidade();
        this.estado = endereco.getEstado();
    }
}
