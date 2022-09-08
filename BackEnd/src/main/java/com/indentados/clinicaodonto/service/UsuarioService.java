package com.indentados.clinicaodonto.service;

import com.indentados.clinicaodonto.model.Usuario;
import com.indentados.clinicaodonto.repository.UsuarioRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    final static Logger logger = Logger.getLogger(UsuarioService.class);
    @Autowired
    UsuarioRepository repository;

    public Usuario salvar(Usuario usuario){
        return repository.save(usuario);
    }
    public List<Usuario> buscarTodos(){
        return repository.findAll();
    }

    public Usuario alterar(Usuario usuario){
        return repository.save(usuario);
    }
}
