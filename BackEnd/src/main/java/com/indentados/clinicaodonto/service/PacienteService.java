package com.indentados.clinicaodonto.service;

import com.indentados.clinicaodonto.DTO.PacienteDTO;
import com.indentados.clinicaodonto.exception.ResourceNotFoundException;
import com.indentados.clinicaodonto.model.Paciente;
import com.indentados.clinicaodonto.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class PacienteService {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    PacienteRepository repository;

    public Paciente salvar(Paciente paciente){

        paciente.getUsuario().setPassword(encoder.encode(paciente.getUsuario().getPassword()));

        return repository.save(paciente);
    }

    public List<Paciente> buscarTodos(){
        return repository.findAll();
    }

    public List<PacienteDTO> buscarTodosDTO(){

        List<Paciente> listPaciente = repository.findAll();

        List<PacienteDTO> listPacienteDTO = new ArrayList<>();

        for(Paciente p : listPaciente){
            listPacienteDTO.add(new PacienteDTO(p));
        }

        return listPacienteDTO;
    }

    public Optional<Paciente> buscarPorId(Long id){
        return repository.findById(id);
    }


    public Paciente atualizar(Paciente paciente){
        return repository.save(paciente);
    }

    public void excluir(Long id) throws ResourceNotFoundException {
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Erro ao excluir paciente. Id informado não existe"));
        repository.deleteById(id);
    }




}
