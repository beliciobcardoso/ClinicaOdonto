package com.indentados.clinicaodonto.controller;

import com.indentados.clinicaodonto.exception.ResourceNotFoundException;
import com.indentados.clinicaodonto.model.Usuario;
import com.indentados.clinicaodonto.service.UsuarioService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuario")
@CrossOrigin( "*" )
public class UsuarioController {
final static Logger logger = Logger.getLogger(UsuarioController.class);

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity salvar(@RequestBody Usuario usuario) {

        return new ResponseEntity(usuarioService.salvar(usuario), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity buscarTodos(){

        return  new ResponseEntity(usuarioService.buscarTodos(), HttpStatus.OK);
    }

    @RequestMapping(value = "/buscaId", method = RequestMethod.GET)
    public ResponseEntity buscarById(@RequestParam("id") Long id){
        Optional<Usuario> usuarioOptional = usuarioService.buscarById(id);

        if (usuarioOptional.isEmpty()){
            return new ResponseEntity("Usuario não encontrado", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(usuarioOptional.get(), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity alterar(@RequestBody Usuario usuario){
        logger.info("Recebendo dp Body " + usuario.getUsername() + " " + usuario.getPassword());
        usuarioService.alterar(usuario);
        return  new ResponseEntity("O Usuario foi alterado com Sucesso!!!!!!", HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity delete(@RequestParam("id") Long id) throws ResourceNotFoundException {

        try {
        usuarioService.delete(id);
        return new ResponseEntity("O Usuario foi deletado com Sucesso!!!!!!",HttpStatus.OK);

        }catch (ResourceNotFoundException e){
            return new ResponseEntity("Usuario não deletado, Usuario não existe.",HttpStatus.NOT_FOUND);
        }
    }



}
