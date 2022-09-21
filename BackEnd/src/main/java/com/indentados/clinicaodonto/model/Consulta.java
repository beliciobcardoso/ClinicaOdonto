package com.indentados.clinicaodonto.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "consulta")

public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data")
    private Date dataConsulta;

    @Column(name = "hora")
    private Time horaConsulta;

    @OneToOne
    @JoinColumn(name = "id_paciente", referencedColumnName = "id")
    private Paciente paciente;

    @OneToOne
    @JoinColumn(name = "id_dentista", referencedColumnName = "id")
    private Dentista dentista;

}
