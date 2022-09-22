package com.indentados.clinicaodonto.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.indentados.clinicaodonto.model.Consulta;
import com.indentados.clinicaodonto.model.Dentista;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class ConsultaDTO {

    private LocalDateTime dataHoraConsulta;

    private LocalDate dataConsulta;

    private LocalTime horaConsulta;

    private Dentista dentista;

    public void setDataHoraConsulta(Timestamp dataHoraConsulta) {
        this.dataHoraConsulta = Instant.ofEpochMilli(dataHoraConsulta.getTime()).atZone(ZoneId.of("UTC-03:00")).toLocalDateTime();

        this.dataConsulta = this.dataHoraConsulta.toLocalDate();
        this.horaConsulta = this.dataHoraConsulta.toLocalTime();
    }

    //e cadê as infos do paciente?
    public ConsultaDTO(Consulta consulta){
        this.dataConsulta = consulta.getDataHoraConsulta().toLocalDateTime().toLocalDate();
        this.horaConsulta = consulta.getDataHoraConsulta().toLocalDateTime().toLocalTime();
        this.dentista = consulta.getDentista();
    }
}