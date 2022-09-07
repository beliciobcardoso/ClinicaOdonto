package com.indentados.clinicaodonto.service;

import com.indentados.clinicaodonto.DTO.ConsultaDTO;
import com.indentados.clinicaodonto.model.Consulta;
import com.indentados.clinicaodonto.repository.ConsultaRepository;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    //final static Logger logger = Logger.getLogger(ConsultaService.class);

    @Autowired
    ConsultaRepository repository;

    public Consulta salvar(Consulta consulta) {
        System.out.println(consulta.getDataConsulta());
        return repository.save(consulta);
    }

     public List<Consulta> buscarTodas() {
        return repository.findAll();
     }

     public List<ConsultaDTO> buscarTodasDTO() {
        List<Consulta> listConsulta = repository.findAll();

        List<ConsultaDTO> listConsultaDTO = new ArrayList<>();

        for (Consulta c : listConsulta){
            listConsultaDTO.add(new ConsultaDTO(c));
        }
        return listConsultaDTO;
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
