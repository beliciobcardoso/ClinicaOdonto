package com.indentados.clinicaodonto.service;

import com.indentados.clinicaodonto.DTO.ConsultaDTO;
import com.indentados.clinicaodonto.DTO.DentistaDTO;
import com.indentados.clinicaodonto.model.Consulta;
import com.indentados.clinicaodonto.model.Dentista;
import com.indentados.clinicaodonto.repository.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {

    @Autowired
    DentistaRepository dentistaRepository;

    public Dentista salvar(Dentista dentista){
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
        //corrigir update: colocar verificação antes de "salvar"
        return dentistaRepository.save(dentista);
    }

    public void excluir(Long id){
        dentistaRepository.deleteById(id);
    }
}
