package com.indentados.clinicaodonto.service;

import com.indentados.clinicaodonto.exception.ResourceNotFoundException;
import com.indentados.clinicaodonto.model.Consulta;
import com.indentados.clinicaodonto.model.Dentista;
import com.indentados.clinicaodonto.model.Paciente;
import com.indentados.clinicaodonto.repository.ConsultaRepository;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional

class ConsultaServiceTest
{
	final static Logger logger = Logger.getLogger(ConsultaServiceTest.class);

	@Autowired
	ConsultaService consultaService;

	@Autowired
	ConsultaRepository consultaRepository;

	static Consulta consulta;

	@BeforeEach
	void doBefore(){
		this.consulta = new Consulta();
		this.consulta.setPaciente(new Paciente());
		this.consulta.setDentista(new Dentista());
		this.consulta.setDataHoraConsulta(Timestamp.valueOf(LocalDateTime.of(2022,7,12,15,30)));
	}

	@Test
	void salvar(){
		Consulta consultaSalva = new Consulta();
		consultaSalva = consultaService.salvar(consulta);
		Assertions.assertNotNull(consultaSalva.getId());
	}

	@Test
	void buscarTodas(){
		logger.info("Salvando a consulta " + consulta.getId());
		Consulta consultaSalva = consultaService.salvar(consulta);

		logger.info("Criando a consulta 2");

		Consulta consulta2 = new Consulta();
		consulta2.setDataHoraConsulta(Timestamp.valueOf(LocalDateTime.of(2022,9,26,9,0)));
		consulta2.setPaciente(new Paciente());
		consulta2.setDentista(new Dentista());


		logger.info("Salvando a consulta " + consulta2.getId());
		Consulta consultaSalva2 = consultaService.salvar(consulta2);

		List<Consulta> consultaList = consultaService.buscarTodas();

		assertTrue(consultaList.size()==2);

	}

	@Test
	void atualizar() {

		Consulta consultaSalva = consultaService.salvar(consulta);

		logger.info("Atualizando o registro da consulta com id: " + consultaSalva.getId());
		logger.info("A data e o horário atual da consulta é: "+ consultaSalva.getDataHoraConsulta());

        Timestamp dataHoraConsulta = Timestamp.valueOf(LocalDateTime.of(2022,9,26,14,0));

        consultaSalva.setDataHoraConsulta(dataHoraConsulta);
		consultaSalva.setDentista(new Dentista());
		consultaSalva.setPaciente(new Paciente());
        Consulta consultaAlterada = consultaService.atualizar(consultaSalva);

        Assertions.assertEquals(dataHoraConsulta, consultaAlterada.getDataHoraConsulta());
		logger.info("Consulta atualizada para: " + consultaAlterada.getDataHoraConsulta());
    }


	@Test
	void excluir() throws ResourceNotFoundException {

		logger.info("Salvando uma consulta");
		Consulta consultaSalva4 = new Consulta();
		consultaSalva4 = consultaService.salvar(consulta);


		logger.info("Excluindo o registro da consulta " + consultaSalva4.getId());


		try{
			consultaService.excluir(consultaSalva4.getId());
		}catch (ResourceNotFoundException exception){
			throw new ResourceNotFoundException("Consulta não encontrada");
		}

		assertFalse(consultaService.buscarPorId(consultaSalva4.getId()).isPresent());

	}

}