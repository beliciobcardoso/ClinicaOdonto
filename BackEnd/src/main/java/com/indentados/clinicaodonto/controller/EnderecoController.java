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
@CrossOrigin( "*" )
public class EnderecoController {

    @Autowired
    EnderecoService service;

    @PostMapping
    public ResponseEntity salvarEndereco(@RequestBody Endereco endereco){

        Endereco enderecoSalvo = service.salvar(endereco);
        return new ResponseEntity(enderecoSalvo, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity buscarTodos(){

        List<EnderecoDTO> enderecoDTOList = service.buscarTodos();

        if(enderecoDTOList.isEmpty()){
            return new ResponseEntity("Nenhum endereço encontrado!",HttpStatus.OK);
        }
        return new ResponseEntity(enderecoDTOList, HttpStatus.OK);
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
    public ResponseEntity atualizar(@RequestBody Endereco endereco){

        Endereco enderecoAtualizado = service.atualizar(endereco);
        return new ResponseEntity(enderecoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity excluir(@RequestParam("id") Long id){

        service.excluir(id);

        return new ResponseEntity("O endereço foi excluído!",HttpStatus.OK);

    }




}
