package com.indentados.clinicaodonto.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.indentados.clinicaodonto.DTO.EnderecoDTO;
import com.indentados.clinicaodonto.model.Endereco;
import com.indentados.clinicaodonto.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    EnderecoService service;

    @PostMapping
    public Endereco salvarEndereco(@RequestBody Endereco endereco){
        return service.salvar(endereco);
    }


    @GetMapping
    public List<EnderecoDTO> buscarTodos(){
        return service.buscarTodos();
    }


    @RequestMapping(value = "/buscaId", method = RequestMethod.GET)
    public ResponseEntity buscarPorId(@RequestParam("id") Long id){
        ObjectMapper mapper = new ObjectMapper();

        Optional<Endereco> enderecoOptional = service.buscarPorId(id);

        if(enderecoOptional.isEmpty()){
            return new ResponseEntity("Endereço não encontrado", HttpStatus.NOT_FOUND);
        }

        Endereco endereco = enderecoOptional.get();
        EnderecoDTO enderecoDTO = mapper.convertValue(endereco, EnderecoDTO.class);

        return new ResponseEntity(enderecoDTO, HttpStatus.OK);
    }


    @PatchMapping
    public Endereco atualizar(@RequestBody Endereco endereco){
        return service.atualizar(endereco);
    }

    @DeleteMapping
    public void excluir(@RequestParam("id") Long id){
        service.excluir(id);
    }




}
