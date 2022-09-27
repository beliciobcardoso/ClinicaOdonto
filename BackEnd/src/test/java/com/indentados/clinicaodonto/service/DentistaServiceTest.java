package com.indentados.clinicaodonto.service;

import com.indentados.clinicaodonto.exception.ResourceNotFoundException;
import com.indentados.clinicaodonto.model.*;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class DentistaServiceTest {
	final static Logger logger = Logger.getLogger(DentistaServiceTest.class);
	@Autowired
    DentistaService dentistaService;
	static Dentista dentista = new Dentista();

	@BeforeEach
	void fazAntes() {

		Perfil perfilAdmin = new Perfil();
		perfilAdmin.setRole("ADMIN");
		List<Perfil> perfilListAdmin = new ArrayList<>();
		perfilListAdmin.add(perfilAdmin);

		dentista.setNome("Lucas");
		dentista.setSobrenome("Bernardo");
		dentista.setEndereco(new Endereco(null,"Rua 1",
				"2022", "Casa A", "Aldeota",
				"Fortaleza", "Ceará", "60000-500"));
		dentista.setEmail("lucas@email.com");
		dentista.setMatricula("500333565656555");
		dentista.setUsuario(new Usuario(null,"jedi","123456",perfilListAdmin));

	}

	@Test
	void testarSalvar()
	{
		logger.info("Salvando um dentista");
		Dentista dentistaSalvo = dentistaService.salvar(dentista);
		assertTrue(dentistaSalvo.getId()>0);
	}

	@Test
	void testarBuscaId()
	{
		logger.info("Salvando um paciente");
		Dentista dentistaSalvo = dentistaService.salvar(dentista);

		logger.info("Buscando um dentista com o id: " + dentistaSalvo.getId());
		Optional<Dentista> dentista1 = dentistaService.buscarPorId(dentistaSalvo.getId());

		assertEquals(dentistaSalvo.getId(),dentista1.isPresent() ? dentista1.get().getId() : null);
	}
	
	@Test
	void testarBuscarTodos()
	{
		Perfil perfilAdmin = new Perfil();
		perfilAdmin.setRole("ADMIN");
		List<Perfil> perfilListAdmin = new ArrayList<>();
		perfilListAdmin.add(perfilAdmin);

		dentista.setNome("Lucas");
		dentista.setSobrenome("Bernardo");
		dentista.setEndereco(new Endereco(null,"Rua 1",
				"2022", "Casa A", "Aldeota",
				"Fortaleza", "Ceará", "60000-500"));
		dentista.setEmail("lucas@email.com");
		dentista.setMatricula("500333565656555");
		dentista.setUsuario(new Usuario(null,"jedi","123456",perfilListAdmin));
	}

	@Test
	void testarAtualizacao()
	{

		logger.info("Salvando um dentista");
		Dentista dentistaSalvo = dentistaService.salvar(dentista);

		logger.info("Atualizando o registro do dentista com id: " + dentistaSalvo.getId());

		dentistaSalvo.setNome("João");
		dentistaSalvo.setSobrenome("Martins");
		dentistaSalvo.setMatricula("50006565686");

		Dentista dentistaAtualizado = dentistaService.atualizar(dentistaSalvo);

		assertTrue(dentistaAtualizado.getId()>0);

	}


	@Test
	void testarDelete() throws ResourceNotFoundException
	{
		logger.info("Salvando um dentista");
		Dentista dentistaSalvo = dentistaService.salvar(dentista);


		logger.info("Excluindo o registro do dentista " + dentistaSalvo.getId());


		try{
			dentistaService.excluir(dentistaSalvo.getId());
		}catch (ResourceNotFoundException exception){
			throw new ResourceNotFoundException("Dentista não encontrado");
		}

		assertFalse(dentistaService.buscarPorId(dentistaSalvo.getId()).isPresent());
	}
}