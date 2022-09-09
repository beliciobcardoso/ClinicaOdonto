package com.indentados.clinicaodonto.repository;

import com.indentados.clinicaodonto.model.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DentistaRepository extends JpaRepository<Dentista, Long> {
}
