package com.indentados.clinicaodonto.config.security;

import com.indentados.clinicaodonto.model.Usuario;
import com.indentados.clinicaodonto.repository.UsuarioRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {
    Logger logger = Logger.getLogger(AutenticacaoViaTokenFilter.class);

    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(request);

        boolean valido = tokenService.verficaToken(token);


        if(valido == true){
            autenticarUsuario(token);
        }

        filterChain.doFilter(request,response);
    }

    private void autenticarUsuario(String token) {
        String username = tokenService.getUsernameUsuario(token);
        logger.info("Usuario " + username+" logado!");
        Usuario usuario = usuarioRepository.findByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private String recuperarToken(HttpServletRequest request) {
        String getToken = request.getHeader("Authorization");
        if(getToken == null || getToken.isEmpty() || !getToken.startsWith("Bearer ")){
            return null;
        }
        return getToken.substring(7,getToken.length());
    }
}
