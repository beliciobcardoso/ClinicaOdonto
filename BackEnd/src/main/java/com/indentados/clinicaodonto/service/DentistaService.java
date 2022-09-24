package com.indentados.clinicaodonto.service;


import com.indentados.clinicaodonto.DTO.DentistaDTO;
import com.indentados.clinicaodonto.exception.ResourceNotFoundException;
import com.indentados.clinicaodonto.model.Dentista;
import com.indentados.clinicaodonto.repository.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    DentistaRepository dentistaRepository;

    public Dentista salvar(Dentista dentista){

        dentista.getUsuario().setPassword(encoder.encode(dentista.getUsuario().getPassword()));

        return dentistaRepository.save(dentista);
    }

    public List<Dentista> buscarTodos(){
        return dentistaRepository.findAll();
    }

    public List<DentistaDTO> buscarTodosDentistasDTO() {
        List<Dentista> listaDentista = dentistaRepository.findAll();

        List<DentistaDTO> listaDentistaDTO = new ArrayList<>();

        for (Dentista d : listaDentista){
            listaDentistaDTO.add(new DentistaDTO(d));
        }
        return listaDentistaDTO;
    }

    public Optional<Dentista> buscarPorId(Long id){
        return dentistaRepository.findById(id);
    }

    public Dentista atualizar(Dentista dentista){
        dentista.getUsuario().setPassword(encoder.encode(dentista.getUsuario().getPassword()));

        return dentistaRepository.save(dentista);
    }

    public void excluir(Long id) throws ResourceNotFoundException {
        dentistaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Erro ao deletar Dentista. Id informado não existe"));
        dentistaRepository.deleteById(id);
    }
}
