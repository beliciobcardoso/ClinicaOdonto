package com.indentados.clinicaodonto.repository;

import com.indentados.clinicaodonto.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

}
