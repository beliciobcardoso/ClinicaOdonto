package com.indentados.clinicaodonto.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.indentados.clinicaodonto.model.Endereco;
import com.indentados.clinicaodonto.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.SQLRecoverableException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    EnderecoService service;

    @PostMapping
    public Endereco salvarEndereco(@RequestBody Endereco endereco) throws SQLException{
        return service.salvar(endereco);
    }

    @GetMapping
    public List<Endereco> buscarTodos() throws SQLException{
        return service.buscarTodos();
    }

    //A SER FEITO O BUSCA POR ID

    @RequestMapping(value = "/buscaId")
    public ResponseEntity buscaPorId(@RequestParam("id") Integer  id) throws SQLException{
        //ObjectMapper mapper = new ObjectMapper();

        //Optional<Endereco> enderecoOptional = service.buscarId(id);

        //if(enderecoOptional.isEmpty()){
        //    return new ResponseEntity("Endereço não encontrado", HttpStatus.NOT_FOUND);
        //}

        //Endereco endereco = enderecoOptional.get();

        return null;
    }


    @PatchMapping
    public void atualizar(@RequestBody Endereco endereco) throws SQLException{
        System.out.println();
        service.atualizar(endereco);
    }

    @DeleteMapping
    public void excluir(@RequestParam("id") Integer  id) throws SQLException{
        service.excluir(id);
    }




}
