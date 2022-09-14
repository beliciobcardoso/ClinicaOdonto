package com.indentados.clinicaodonto.controller;


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
    public ResponseEntity salvarEndereco(@RequestBody Endereco endereco){

        Endereco enderecoSalvo = service.salvar(endereco);
        return new ResponseEntity(enderecoSalvo, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity buscarTodos(){

        List<Endereco> enderecoDTOList = service.buscarTodos();

        if(enderecoDTOList.isEmpty()){
            return new ResponseEntity("Nenhum endereço encontrado!",HttpStatus.OK);
        }
        
        return new ResponseEntity(enderecoDTOList, HttpStatus.OK);
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
    public ResponseEntity atualizar(@RequestBody Endereco endereco)
    {
        Endereco enderecoAtualizado = service.atualizar(endereco);
        return new ResponseEntity(enderecoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity excluir(@RequestParam("id") Long id)
    {
        service.excluir(id);
        return new ResponseEntity("O endereço foi excluído!",HttpStatus.OK);
    }
}
