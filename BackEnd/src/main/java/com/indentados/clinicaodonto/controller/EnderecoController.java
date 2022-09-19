package com.indentados.clinicaodonto.controller;


import com.indentados.clinicaodonto.exception.ResourceNotFoundException;
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

        List<Endereco> enderecoList = service.buscarTodos();

        if(enderecoList.isEmpty()){
            return new ResponseEntity("Nenhum endereço encontrado!",HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity(enderecoList, HttpStatus.OK);
    }


    @RequestMapping(value = "/buscaId", method = RequestMethod.GET)
    public ResponseEntity buscarPorId(@RequestParam("id") Long id){

        Optional<Endereco> enderecoOptional = service.buscarPorId(id);

        if(enderecoOptional.isEmpty())
        {
            return new ResponseEntity("Endereço não encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(enderecoOptional.get(), HttpStatus.OK);
    }


    @PatchMapping
    public ResponseEntity atualizar(@RequestBody Endereco endereco) {
        if(endereco.getId() == null || service.buscarPorId(endereco.getId()).isEmpty())
        {
            return new ResponseEntity("Endereço não encontrado", HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity(service.atualizar(endereco), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity excluir(@RequestParam("id") Long id) throws ResourceNotFoundException {
        service.excluir(id);
        return new ResponseEntity("O endereço foi excluído!",HttpStatus.OK);
    }
}
