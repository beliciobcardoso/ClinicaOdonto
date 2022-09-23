package com.indentados.clinicaodonto.controller;

import com.indentados.clinicaodonto.DTO.ConsultaDTO;
import com.indentados.clinicaodonto.exception.ResourceNotFoundException;
import com.indentados.clinicaodonto.model.Consulta;
import com.indentados.clinicaodonto.service.ConsultaService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consulta")
@CrossOrigin( "*" )
public class ConsultaController {

    @Autowired
    ConsultaService service;

    final static Logger logger = Logger.getLogger(ConsultaController.class);


    @PostMapping
    public ResponseEntity salvarConsulta(@RequestBody Consulta consulta) {
        logger.info(" Salvando consulta. ");
        return new ResponseEntity(service.salvar(consulta), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity buscarTodasConsultas() {
        logger.info(" Buscando consultas. ");
        List<Consulta> consultaList = service.buscarTodas();

        if(consultaList.isEmpty()){
            return new ResponseEntity("Nenhuma consulta encontrada!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(consultaList, HttpStatus.OK);
    }

    @RequestMapping(value = "/agendaDentista", method = RequestMethod.GET)
    public ResponseEntity buscarTodasConsultasDTO() {
        logger.info(" Buscando consultas. ");
        List<ConsultaDTO> consultaDTOList = service.buscarTodasDTO();

        if(consultaDTOList.isEmpty()){
            return new ResponseEntity("Nenhuma consulta encontrada", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(consultaDTOList, HttpStatus.OK);
    }

    @RequestMapping(value = "/buscaId", method = RequestMethod.GET)
    public ResponseEntity buscarConsultaPorId(@RequestParam("id") Long id) {
        //ObjectMapper mapper = new ObjectMapper();
        logger.info(" Buscando consulta por id. ");
        Optional<Consulta> consultaOptional = service.buscarPorId(id);
        if(consultaOptional.isEmpty()){
            return new ResponseEntity("Consulta não encontrada", HttpStatus.NOT_FOUND);
        }

        Consulta consulta = consultaOptional.get();

        return new ResponseEntity(consulta, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity atualizarConsulta(@RequestBody Consulta consulta) {
        if(consulta.getId() == null)
        {
            return new ResponseEntity("Consulta não encontrada", HttpStatus.NOT_FOUND);
        }
        logger.info(" Atualizando dados da consulta. ");
        return new ResponseEntity(service.atualizar(consulta), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity excluirConsulta(@RequestParam("id") Long id) throws ResourceNotFoundException {
        service.excluir(id);
        logger.info(" Excluindo consulta. ");
        //daria pra fazer uma validação ou um catch naquela exception aqui, pra retornar not found, caso não achasse o id
        return new ResponseEntity("Paciente excluído com sucesso", HttpStatus.OK);
    
    }

}
