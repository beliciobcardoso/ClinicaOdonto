package com.indentados.clinicaodonto.controller;

import com.indentados.clinicaodonto.DTO.TokenDTO;
import com.indentados.clinicaodonto.DTO.UsuarioDTO;
import com.indentados.clinicaodonto.config.security.TokenService;
import com.indentados.clinicaodonto.exception.ResourceNotFoundException;
import com.indentados.clinicaodonto.service.AutenticacaoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    final static Logger logger = Logger.getLogger(AuthController.class);
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticar(@RequestBody @Valid UsuarioDTO usuarioDTO) throws ResourceNotFoundException {

        try {
            UsernamePasswordAuthenticationToken loginUsuario = usuarioDTO.converter();

            Authentication authentication = authManager.authenticate(loginUsuario);

            String token = tokenService.gerarToken(authentication);

            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setToken(token);
            tokenDTO.setTipo("Bearer");

            return new ResponseEntity(tokenDTO, HttpStatus.OK);
        }catch (AuthenticationException exception){
            logger.info("Erro ao autenticar");
            return new ResponseEntity("Erro ao autenticar",HttpStatus.BAD_REQUEST);

        }

    }
}
