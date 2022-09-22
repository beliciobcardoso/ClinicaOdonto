package com.indentados.clinicaodonto.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indentados.clinicaodonto.DTO.ConsultaDTO;
import com.indentados.clinicaodonto.exception.ResourceNotFoundException;
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
        //System.out.println(consulta.getDataConsulta());
        return repository.save(consulta);
    }

    public List<Consulta> buscarTodas() {
        return repository.findAll();
    }

    public List<ConsultaDTO> buscarTodasDTO() {
        List<Consulta> listConsulta = repository.findAll();

        List<ConsultaDTO> listConsultaDTO = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        for (Consulta c : listConsulta){
            listConsultaDTO.add(mapper.convertValue(c, ConsultaDTO.class));
        }
        return listConsultaDTO;
    }

    public Consulta atualizar (Consulta consulta) {
        return repository.save(consulta);
    }

    public void excluir(Long id) throws ResourceNotFoundException {
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Erro ao excluir consulta. Id informado não existe"));
        repository.deleteById(id);
    }

    public Optional<Consulta> buscarPorId(Long id) {
        return repository.findById(id);
    }
}