package com.indentados.clinicaodonto;

import com.indentados.clinicaodonto.model.Perfil;
import com.indentados.clinicaodonto.model.Usuario;
import com.indentados.clinicaodonto.repository.UsuarioRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CreateUserAndRolesRun implements ApplicationRunner {

    static Logger logger = Logger.getLogger(CreateUserAndRolesRun.class);

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Perfil perfilAdmin = new Perfil();
        Perfil perfilUser = new Perfil();

        perfilAdmin.setRole("ADMIN");
        perfilUser.setRole("USER");

        List<Perfil> perfilListAdmin = new ArrayList<>();
        List<Perfil> perfilListUser = new ArrayList<>();

        perfilListAdmin.add(perfilAdmin);
        perfilListUser.add(perfilUser);

        Usuario admin = new Usuario();
        Usuario user = new Usuario();

        admin.setUsername("admin");
        admin.setPassword(encoder.encode("123456"));
        admin.setRoles(perfilListAdmin);

        user.setUsername("user");
        user.setPassword(encoder.encode("123456"));
        user.setRoles(perfilListUser);

        logger.info("Pegando a lista de usuarios");
        List<Usuario> usuarios = usuarioRepository.findAll();

        if (usuarios.isEmpty()){
            logger.info("Criando os usuarios " + admin.getUsername() + " e " + user.getUsername());
            usuarioRepository.save(admin);
            usuarioRepository.save(user);
        }
    }
}
