package com.indentados.clinicaodonto.service;

import com.indentados.clinicaodonto.model.Consulta;
import com.indentados.clinicaodonto.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    ConsultaRepository repository;

    public Consulta salvar(Consulta consulta) {
        return repository.save(consulta);
    }

     public List<Consulta> buscarTodas() {
        return repository.findAll();
     }

     public Consulta atualizar (Consulta consulta) {
        return repository.save(consulta);
     }

     public void excluir(Long id) {
        repository.deleteById(id);
     }

     public Optional<Consulta> buscarPorId(Long id) {
        return repository.findById(id);
     }
}
