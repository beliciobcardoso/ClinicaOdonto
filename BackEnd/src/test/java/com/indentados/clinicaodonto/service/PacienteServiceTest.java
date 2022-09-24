package com.indentados.clinicaodonto.service;


import com.indentados.clinicaodonto.exception.ResourceNotFoundException;
import com.indentados.clinicaodonto.model.Endereco;
import com.indentados.clinicaodonto.model.Paciente;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional

class PacienteServiceTest
{
//TRUNCATE TABLE "nome"
	final static Logger logger = Logger.getLogger(PacienteServiceTest.class);

	@Autowired
    PacienteService service;
	static Paciente paciente = new Paciente();

	@BeforeEach
	void doBefore(){
		//paciente.setId(null);
		paciente.setNome("Lucas");
		paciente.setSobrenome("Bernardo");
		paciente.setEndereco(new Endereco(null,"Rua 1",
				"2022", "Casa A", "Aldeota",
				"Fortaleza", "Ceará", "60000-500"));
		paciente.setEmail("lucas@email.com");
		paciente.setRg("500333565656555");
		//paciente.setUsuario(new Usuario(null,"teste1","123456","USER");
	}

	@Test
	void testeSalvar(){
		logger.info("Salvando um paciente");
		Paciente pacienteSalvo = service.salvar(paciente);
		assertTrue(pacienteSalvo.getId()>0);

	}

	@Test
	void testeBuscarId(){

		logger.info("Salvando um paciente");
		Paciente pacienteSalvo = service.salvar(paciente);

		logger.info("Buscando um paciente com o id: " + pacienteSalvo.getId());
		Optional<Paciente> paciente1 = service.buscarPorId(pacienteSalvo.getId());

		assertEquals(pacienteSalvo.getId(),paciente1.isPresent() ? paciente1.get().getId() : null);

	}

	@Test
	void testeBuscarTodos(){
		logger.info("Salvando o paciente " + paciente.getNome());
		Paciente pacienteSalvo = service.salvar(paciente);

		logger.info("Criando paciente 2");

		Paciente paciente2 = new Paciente();
		paciente2.setNome("Luke");
		paciente2.setSobrenome("Skywalker");
		paciente2.setEmail("jedi@email.com");
		paciente2.setEndereco(new Endereco());
		paciente2.setRg("5777775757576666");
		//paciente2.setUsuario(new Usuario(null,"jedi","123456","USER");

		logger.info("Salvando o paciente " + paciente2.getNome());
		Paciente pacienteSalvo2 = service.salvar(paciente2);

		List<Paciente> resultado = service.buscarTodos();

		assertTrue(resultado.size()>0);

	}


	@Test
	void testeAtualizar(){

		logger.info("Salvando um paciente");
		Paciente pacienteSalvo = service.salvar(paciente);

		logger.info("Atualizando o registro do paciente com id: " + pacienteSalvo.getId());

		pacienteSalvo.setNome("João");
		pacienteSalvo.setSobrenome("Martins");
		pacienteSalvo.setRg("50006565686");

		Paciente pacienteAtualizado = service.atualizar(pacienteSalvo);

		assertTrue(pacienteAtualizado.getId()>0);

	}

	@Test
	void testeExcluir() throws ResourceNotFoundException {

		logger.info("Salvando um paciente");
		Paciente pacienteSalvo = service.salvar(paciente);


		logger.info("Excluindo o registro do paciente " + pacienteSalvo.getId());


		try{
			service.excluir(pacienteSalvo.getId());
		}catch (ResourceNotFoundException exception){
			throw new ResourceNotFoundException("Paciente não encontrado");
		}

		assertFalse(service.buscarPorId(pacienteSalvo.getId()).isPresent());

	}













}