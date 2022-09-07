package com.indentados.clinicaodonto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indentados.clinicaodonto.model.Consulta;
import com.indentados.clinicaodonto.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consulta")

public class ConsultaController {

    @Autowired
    ConsultaService service;

    @PostMapping
    public Consulta salvarConsulta(@RequestBody Consulta consulta) {
        return service.salvar(consulta);
    }

    @GetMapping
    public List<Consulta> buscarTodasConsultas() {
        return service.buscarTodas();
    }

    @RequestMapping(value = "/buscaId", method = RequestMethod.GET)
    public ResponseEntity buscarConsultaPorId(@RequestParam("id") Long id) {
        ObjectMapper mapper = new ObjectMapper();

        Optional<Consulta> consultaOptional = service.buscarPorId(id);
        if(consultaOptional.isEmpty()){
            return new ResponseEntity("Consulta não encontrada", HttpStatus.NOT_FOUND);
        }

        Consulta consulta = consultaOptional.get();

        return new ResponseEntity(consulta, HttpStatus.OK);
    }

    @PatchMapping
    public Consulta atualizarConsulta(@RequestBody Consulta consulta) {
        return service.atualizar(consulta);
    }

    @DeleteMapping
    public void excluirConsulta(@RequestParam("id") Long id) {
        service.excluir(id);
    }

}
