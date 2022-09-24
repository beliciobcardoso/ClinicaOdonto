package com.indentados.clinicaodonto.service;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.indentados.clinicaodonto.model.Usuario;
import com.indentados.clinicaodonto.repository.UsuarioRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {
    final static Logger logger = Logger.getLogger(AutenticacaoService.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("Recebendo usuario: " + username);
        try {
            Usuario usuario = usuarioRepository.findByUsername(username);
            logger.info("Resultado da busca do usuario do BD: " + usuario);
            logger.info("Usuario do banco: " + usuario.getUsername());

            return usuario;
        }catch (UsernameNotFoundException exception){
            throw new UsernameNotFoundException("Usuario não existe");
        }
    }
}
