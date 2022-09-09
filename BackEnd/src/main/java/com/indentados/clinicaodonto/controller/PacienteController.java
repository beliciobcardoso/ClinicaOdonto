package com.indentados.clinicaodonto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indentados.clinicaodonto.DTO.PacienteDTO;
import com.indentados.clinicaodonto.model.Paciente;
import com.indentados.clinicaodonto.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    PacienteService service;

    @PostMapping
    public ResponseEntity salvarPaciente(@RequestBody Paciente paciente){
        Paciente pacienteSalvo = service.salvar(paciente);
        return new ResponseEntity(pacienteSalvo, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity buscarTodos(){
        List<PacienteDTO> pacienteDTOList = service.buscarTodos();

        if(pacienteDTOList.isEmpty()){
            return new ResponseEntity("Nenhum paciente encontrado!",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pacienteDTOList,HttpStatus.OK);
    }

    @RequestMapping(value = "buscaId", method = RequestMethod.GET)
    public ResponseEntity buscarPorId(@RequestParam("id") Long id){
        ObjectMapper mapper = new ObjectMapper();

        Optional<Paciente> pacienteOptional = service.buscarPorId(id);

        if(pacienteOptional.isEmpty()){
            return new ResponseEntity("Paciente não encontrado", HttpStatus.NOT_FOUND);
        }

        Paciente paciente = pacienteOptional.get();
        PacienteDTO pacienteDTO = mapper.convertValue(paciente, PacienteDTO.class);

        return new ResponseEntity(pacienteDTO, HttpStatus.OK);
    }



    @PatchMapping
    public ResponseEntity atualizar(@RequestBody Paciente paciente){
        Paciente pacienteAtualizado = service.atualizar(paciente);
        return new ResponseEntity(pacienteAtualizado,HttpStatus.OK);

    }



    @DeleteMapping
    public ResponseEntity excluir(@RequestParam("id") Long id){

        service.excluir(id);

        return new ResponseEntity("O paciente foi excluído!",HttpStatus.OK);

    }

}