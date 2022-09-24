package com.indentados.clinicaodonto.service;

import com.indentados.clinicaodonto.exception.ResourceNotFoundException;
import com.indentados.clinicaodonto.model.Usuario;
import com.indentados.clinicaodonto.repository.UsuarioRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    final static Logger logger = Logger.getLogger(UsuarioService.class);
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario){
        logger.info("Salvador usuario: " + usuario.getUsername());

        usuario.setPassword(encoder.encode(usuario.getPassword()));

        return usuarioRepository.save(usuario);
    }
    public List<Usuario> buscarTodos(){

        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarById(Long id){
        return usuarioRepository.findById(id);
    }

    public Usuario alterar(Usuario usuario){
        usuario.setPassword(encoder.encode(usuario.getPassword()));

        return usuarioRepository.save(usuario);
    }

    public void delete(Long id) throws ResourceNotFoundException {

        try {
            usuarioRepository.deleteById(id);
       }catch (Exception exception){
            logger.info("Erro exception: " + new ResourceNotFoundException("Erro ao excluir usuario, o id informado não existe.", exception));
           throw new ResourceNotFoundException("Erro ao excluir usuario, o id informado não existe.", exception);
       }

     }
}
