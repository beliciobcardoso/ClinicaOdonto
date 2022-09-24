package com.indentados.clinicaodonto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indentados.clinicaodonto.DTO.DentistaDTO;
import com.indentados.clinicaodonto.exception.ResourceNotFoundException;
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
@CrossOrigin( "*" )
public class DentistaController {

    @Autowired
    DentistaService dentistaService;

    @PostMapping
    public ResponseEntity salvarDentista(@RequestBody Dentista dentista){
        return new ResponseEntity(dentistaService.salvar(dentista), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity buscarDentistas(){
        dentistaService.buscarTodos();
        return new ResponseEntity(dentistaService.buscarTodos(), HttpStatus.OK);
    }

    @RequestMapping(value = "/filtroDentista", method = RequestMethod.GET)
    public List<DentistaDTO> buscarTodosDentistasDTO() {

        return dentistaService.buscarTodosDentistasDTO();
    }

    @RequestMapping(value = "/buscaDentistaPorId", method = RequestMethod.GET)
    public ResponseEntity buscarDentistaPorId(@RequestParam("id") Long id){
        ObjectMapper mapper = new ObjectMapper();

        Optional<Dentista> dentistaOptional = dentistaService.buscarPorId(id);
        if (dentistaOptional.isEmpty()){
            return new ResponseEntity("Dentista não encontrado", HttpStatus.NOT_FOUND);
        }
        
        Dentista dentista = dentistaOptional.get();
        DentistaDTO dentistaDTO = mapper.convertValue(dentista, DentistaDTO.class);
        
        return new ResponseEntity(dentistaDTO,HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity atualizarDadosDentista(@RequestBody Dentista dentista){
        if(dentista.getId() != null)
        {
            return new ResponseEntity(dentistaService.atualizar(dentista), HttpStatus.OK);
        }
        return new ResponseEntity("Dentista não encontrado", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public void excluir(@RequestParam("id")Long id) throws ResourceNotFoundException{
        dentistaService.excluir(id);
    }
}
