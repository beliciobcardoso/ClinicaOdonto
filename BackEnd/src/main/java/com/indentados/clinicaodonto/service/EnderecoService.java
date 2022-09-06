package com.indentados.clinicaodonto.service;

import com.indentados.clinicaodonto.DAO.IDao;
import com.indentados.clinicaodonto.model.DTO.EnderecoDTO;
import com.indentados.clinicaodonto.model.Endereco;
import com.indentados.clinicaodonto.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class EnderecoService {

    @Autowired
    EnderecoRepository repository;

    public Endereco salvar(Endereco endereco){
        return repository.save(endereco);
    }


    public List<EnderecoDTO> buscarTodos(){

        List<Endereco> listEndereco = repository.findAll();

        List<EnderecoDTO> listEnderecoDTO = new ArrayList<>();

        for(Endereco e : listEndereco){
            listEnderecoDTO.add(new EnderecoDTO(e));
        }

        return  listEnderecoDTO;
    }


    public Optional<Endereco> buscarPorId(Long id){
        return repository.findById(id);
    }

    public Endereco atualizar(Endereco endereco){
        return repository.save(endereco);
    }

    public void excluir(Long id){
        repository.deleteById(id);
    }










}
