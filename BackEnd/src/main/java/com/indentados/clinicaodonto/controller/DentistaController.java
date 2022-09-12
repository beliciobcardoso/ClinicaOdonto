package com.indentados.clinicaodonto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indentados.clinicaodonto.DTO.DentistaDTO;
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
            //remover esse corpo, já que nada foi encontrado
            return new ResponseEntity("Dentista não encontrado", HttpStatus.NOT_FOUND);
        }

        Dentista dentista = dentistaOptional.get();
        
        DentistaDTO dentistaDTO = mapper.convertValue(dentista, DentistaDTO.class);
        
        //System.out.println(dentistaDTO);
        return new ResponseEntity(dentistaDTO,HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity atualizarDadosDentista(@RequestBody Dentista dentista){
        //antes de atualizar, verifica se o alvo existe, para impedir que outro seja criado. Verificação fraca.
        if(dentista.getId() != null)
        {
            return new ResponseEntity(dentistaService.atualizar(dentista), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    //teoricamente, não precisa. Mesmo após a demissão, é preferível manter os dados para futuras consultas da empresa.
    @DeleteMapping
    public ResponseEntity excluirDentista(@RequestParam("id") Long id){
        //Assim será validado e respondido ao mesmo tempo, mas dá uma resposta ruim. lógica, mas ruim.
        //return buscarDentistaPorId(id);
        ResponseEntity rs = buscarDentistaPorId(id);
        if(rs.getStatusCodeValue() == 200)//se localizar, pode excluir
        {
            dentistaService.excluir(id);
            return rs = new ResponseEntity(HttpStatus.OK);
        }
        return rs = new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
