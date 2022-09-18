package com.indentados.clinicaodonto.service;

import com.indentados.clinicaodonto.exception.ResourceNotFoundException;
import com.indentados.clinicaodonto.model.Endereco;
import com.indentados.clinicaodonto.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class EnderecoService {

    @Autowired
    EnderecoRepository repository;

    public Endereco salvar(Endereco endereco){
        return repository.save(endereco);
    }


    public List<Endereco> buscarTodos(){
        List<Endereco> listEndereco = repository.findAll();
        return listEndereco;
    }


    public Optional<Endereco> buscarPorId(Long id){
        return repository.findById(id);
    }

    public Endereco atualizar(Endereco endereco){
        return repository.save(endereco);
    }

    public void excluir(Long id) throws ResourceNotFoundException {
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Erro ao excluir endereço, id informado não existe."));
        repository.deleteById(id);
    }










}
