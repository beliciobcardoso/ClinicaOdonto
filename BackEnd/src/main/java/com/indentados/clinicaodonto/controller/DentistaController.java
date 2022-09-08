package com.indentados.clinicaodonto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indentados.clinicaodonto.model.Dentista;
import com.indentados.clinicaodonto.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dentista")
public class DentistaController {

    @Autowired
    DentistaService dentistaService;

    @PostMapping
    public Dentista salvarDentista(@RequestBody Dentista dentista){
        return dentistaService.salvar(dentista);
    }

    @GetMapping
    public List<Dentista> buscarDentistas(){
        return dentistaService.buscarTodos();
    }

    @RequestMapping(value = "/buscaDentistaPorId", method = RequestMethod.GET)
    public ResponseEntity buscarDentistaPorId(@RequestParam("id") Long id){
        ObjectMapper mapper = new ObjectMapper();

        Optional<Dentista> dentistaOptional = dentistaService.buscarPorId(id);
        if (dentistaOptional.isEmpty()){
            return new ResponseEntity("Dentista não encontrado", HttpStatus.NOT_FOUND);
        }

        Dentista dentista = dentistaOptional.get();

        return new ResponseEntity(dentista,HttpStatus.OK);
    }

    @PatchMapping
    public Dentista atualizarDadosDentista(@RequestBody Dentista dentista){
        return dentistaService.atualizar(dentista);
    }

    @DeleteMapping
    public void excluirDentista(@RequestParam("id") Long id){
        dentistaService.excluir(id);
    }
}
