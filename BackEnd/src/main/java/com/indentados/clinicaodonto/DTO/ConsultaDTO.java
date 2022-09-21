package com.indentados.clinicaodonto.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.indentados.clinicaodonto.model.Consulta;
import com.indentados.clinicaodonto.model.Dentista;
import com.indentados.clinicaodonto.model.Paciente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class ConsultaDTO {

    private Date dataConsulta;

    private Time horaConsulta;

    private Dentista dentista;

    //e cadê as infos do paciente?
    public ConsultaDTO(Consulta consulta){
        this.dataConsulta = consulta.getDataConsulta();
        this.horaConsulta = consulta.getHoraConsulta();
        this.dentista = consulta.getDentista();
    }
}
