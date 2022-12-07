package com.indentados.clinicaodonto.service;

import com.indentados.clinicaodonto.model.Perfil;
import com.indentados.clinicaodonto.model.Usuario;
import com.indentados.clinicaodonto.repository.UsuarioRepository;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;

import java.util.*;


@SpringBootTest
@Transactional
class UsuarioServiceTest {
	final static Logger logger = Logger.getLogger(UsuarioServiceTest.class);
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	UsuarioRepository usuarioRepository;

	static Usuario usuario = new Usuario();
	static Usuario usuario2 = new Usuario();

	@BeforeAll
	static void doBefore(){
		Perfil perfilAdmin = new Perfil();
		perfilAdmin.setRole("ADMIN");
		List<Perfil> perfilListAdmin = new ArrayList<>();
		perfilListAdmin.add(perfilAdmin);

		Perfil perfilUser = new Perfil();
		perfilUser.setRole("USER");
		List<Perfil> perfilListUser = new ArrayList<>();
		perfilListUser.add(perfilUser);


		usuario = new Usuario();
		usuario.setUsername("usertestadmin");
		usuario.setPassword("123456");
		usuario.setRoles(perfilListAdmin);

		usuario2 = new Usuario();
		usuario2.setUsername("usertest");
		usuario2.setPassword("123456");
		usuario2.setRoles(perfilListUser);

	}

	@Test
	void salvar(){
		Usuario usuarioTeste;
		usuarioTeste = usuarioService.salvar(usuario);
		Assertions.assertNotNull(usuarioTeste.getId());
	}

	@Test
	void alterar(){

		String password = "987654";
		logger.info("Senha do front: "+password);
		Usuario userReturn = usuarioService.salvar(usuario);
		logger.info("Retorno do ID do Usuario salvo: " + userReturn.getId());

		Usuario userReturnUsername = usuarioRepository.findByUsername(usuario.getUsername());

		logger.info("Resultado do usuario buscando pelo username");
		logger.info("UsuarioID: " + userReturnUsername.getId());
		logger.info("Usuario: " + userReturnUsername.getUsername());
		logger.info("Senha: " + userReturnUsername.getPassword());

		userReturnUsername.setPassword(password);
		logger.info("Passando a senha nova: " + userReturnUsername.getPassword());

		Usuario userSenhaAlterada = usuarioService.alterar(userReturnUsername);
		logger.info("O ID do User alterado: " + userSenhaAlterada.getId());
		logger.info("O username do User alterado: " + userSenhaAlterada.getUsername());
		logger.info("A senha do User alterado: " + userSenhaAlterada.getPassword());

		boolean senhaMatche = encoder.matches(password,userSenhaAlterada.getPassword());

		Assertions.assertTrue(senhaMatche);
	}

	@Test
	void login(){

		String username = "usertest";
		String password = "123456";

		usuarioService.salvar(usuario2);

		Usuario userReturnUsername = usuarioRepository.findByUsername(username);

		boolean senhaMatche = encoder.matches(password,userReturnUsername.getPassword());

		Assertions.assertTrue(senhaMatche);
	}
}