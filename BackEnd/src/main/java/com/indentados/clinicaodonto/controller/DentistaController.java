package com.indentados.clinicaodonto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indentados.clinicaodonto.DTO.DentistaDTO;
import com.indentados.clinicaodonto.exception.ResourceNotFoundException;
import com.indentados.clinicaodonto.model.Dentista;
import com.indentados.clinicaodonto.service.DentistaService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dentista")
@CrossOrigin( "*" )
public class DentistaController {

    @Autowired
    DentistaService dentistaService;

    final static Logger logger = Logger.getLogger(DentistaController.class);

    @PostMapping
    public ResponseEntity salvarDentista(@RequestBody Dentista dentista){
        logger.info(" Salvando dados do dentista. ");
        return new ResponseEntity(dentistaService.salvar(dentista), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity buscarDentistas(){
        logger.info(" Buscando dentistas. ");
        dentistaService.buscarTodos();
        return new ResponseEntity(dentistaService.buscarTodos(), HttpStatus.OK);
    }

    @RequestMapping(value = "/filtroDentista", method = RequestMethod.GET)
    public List<DentistaDTO> buscarTodosDentistasDTO() {
        logger.info(" Buscando dentistas. ");
        return dentistaService.buscarTodosDentistasDTO();
    }

    @RequestMapping(value = "/buscaDentistaPorId", method = RequestMethod.GET)
    public ResponseEntity buscarDentistaPorId(@RequestParam("id") Long id){
        ObjectMapper mapper = new ObjectMapper();

        Optional<Dentista> dentistaOptional = dentistaService.buscarPorId(id);
        if (dentistaOptional.isEmpty()){
            return new ResponseEntity("Dentista não encontrado", HttpStatus.NOT_FOUND);
        }

        logger.info(" Buscando dentista por ID. ");
        Dentista dentista = dentistaOptional.get();
        DentistaDTO dentistaDTO = mapper.convertValue(dentista, DentistaDTO.class);

        return new ResponseEntity(dentistaDTO,HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity atualizarDadosDentista(@RequestBody Dentista dentista){
        if(dentista.getId() != null)
        {
            logger.info(" Atualizando dados do dentista. ");
            return new ResponseEntity(dentistaService.atualizar(dentista), HttpStatus.OK);
        }
        return new ResponseEntity("Dentista não encontrado", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public void excluir(@RequestParam("id")Long id) throws ResourceNotFoundException{
        logger.info(" Deletando dentista. ");
        dentistaService.excluir(id);
    }
}
