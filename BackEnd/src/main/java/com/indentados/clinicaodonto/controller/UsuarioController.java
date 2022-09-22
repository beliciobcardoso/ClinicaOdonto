package com.indentados.clinicaodonto.controller;

import com.indentados.clinicaodonto.model.Usuario;
import com.indentados.clinicaodonto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuario")
@CrossOrigin( "*" )
public class UsuarioController {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    UsuarioService service;

    @PostMapping
    public ResponseEntity salvar(@RequestBody Usuario usuario){
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        return new ResponseEntity(service.salvar(usuario), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity buscarTodoso(){
        return  new ResponseEntity(service.buscarTodos(), HttpStatus.OK);
    }

}
