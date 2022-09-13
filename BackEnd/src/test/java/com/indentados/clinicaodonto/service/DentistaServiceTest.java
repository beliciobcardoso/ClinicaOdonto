package com.indentados.clinicaodonto.service;

import com.indentados.clinicaodonto.model.Dentista;
import com.indentados.clinicaodonto.model.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class DentistaServiceTest
{
	@Autowired
	DentistaService dentistaService;
	static Dentista d;
	
	@BeforeEach
	void fazAntes()
	{
		d = new Dentista("Nome", "Sobrenome", new Endereco(null, "rua", "num", "complemento", "bairro", "cidade", "estado", "cep"), "email@email.com", "1");
	}
	
	@Test
	void testarSalvar()
	{
		d = dentistaService.salvar(d);
		assertTrue(d.getId()>0);
	}
	
	@Test
	void testarBuscaId()
	{
		d = dentistaService.salvar(d);
		Optional<Dentista> d2 = dentistaService.buscarPorId(d.getId());
		
		assertEquals(d, d2.isPresent() ? d2.get() : null);
	}
	
	@Test
	void testarAtualizacao()
	{
		d = dentistaService.salvar(d);
		d.setNome("Nome Alterado");
		d.setSobrenome("Sobrenome Alterado");
		Dentista depoisAtualizacao = dentistaService.atualizar(d);
		d.setNome("Arroz");
		
		assertEquals(d, depoisAtualizacao);
	}
	
	@Test
	void testarDelete()
	{
		d = dentistaService.salvar(d);
		dentistaService.excluir(d.getId());
		assertFalse(dentistaService.buscarPorId(d.getId()).isPresent());
	}
}