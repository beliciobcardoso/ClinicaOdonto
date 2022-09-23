package com.indentados.clinicaodonto.service;

import com.indentados.clinicaodonto.model.Usuario;
import com.indentados.clinicaodonto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    UsuarioRepository repository;

    public Usuario salvar(Usuario usuario){

        usuario.setPassword(encoder.encode(usuario.getPassword()));

        return repository.save(usuario);
    }
    public List<Usuario> buscarTodos(){
        return repository.findAll();
    }

    public Usuario alterar(Usuario usuario){
        return repository.save(usuario);
    }
}
