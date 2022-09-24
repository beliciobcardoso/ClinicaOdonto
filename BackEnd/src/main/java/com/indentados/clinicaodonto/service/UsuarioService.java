package com.indentados.clinicaodonto.service;

import com.indentados.clinicaodonto.model.Usuario;
import com.indentados.clinicaodonto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Usuario> buscarById(Long id){
        return repository.findById(id);
    }

    public Usuario alterar(Usuario usuario){
        usuario.setPassword(encoder.encode(usuario.getPassword()));

        return repository.save(usuario);
    }

    public void delete(Long id){

        repository.deleteById(id);
    }
}
