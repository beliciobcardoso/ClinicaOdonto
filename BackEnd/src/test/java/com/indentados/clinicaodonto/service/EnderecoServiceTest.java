package com.indentados.clinicaodonto.service;

import com.indentados.clinicaodonto.exception.ResourceNotFoundException;
import com.indentados.clinicaodonto.model.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class EnderecoServiceTest
{
	@Autowired
    EnderecoService enderecoService;
	static Endereco e;

	@BeforeEach
	void fazAntes()
	{
		e = new Endereco(null, "logradouro", "numero", "complemento", "bairro", "cidade", "estado", "cep");
	}
	@Test
	void testSalvar()
	{
		//pra reduzir duplicação de código, poderia salvar no dobefore
		e = enderecoService.salvar(e);
		assertTrue(e.getId()>0);
	}
	
	@Test
	void buscarTodos()
	{
		Endereco e2 = new Endereco(null, "logradouro2", "numero2", "complemento2", "bairro", "cidade2", "estado2", "cep2");
		e = enderecoService.salvar(e);
		e2 = enderecoService.salvar(e2);
		
		List <Endereco> resultado = enderecoService.buscarTodos();
		
		assertTrue(resultado.size()>0);
		
	}
	
	@Test
	void buscarPorId()
	{
		e = enderecoService.salvar(e);
		Optional <Endereco> endereco = enderecoService.buscarPorId(e.getId());
		
		assertTrue(endereco.isPresent());
	}
	
	@Test
	void atualizar()
	{
		e = enderecoService.salvar(e);
		
		e.setCidade("Cidade alterada");
		e = enderecoService.atualizar(e);
		Optional <Endereco> endereco = enderecoService.buscarPorId(e.getId());
		
		if(endereco.isPresent())
		{
			assertEquals("Cidade alterada", endereco.get().getCidade());
		}
		else
		{
			fail("Não encontrou o objeto atualizado");
		}
		
	}
	
	@Test
	void excluir() throws ResourceNotFoundException
	{
		e = enderecoService.salvar(e);
		try
		{
			enderecoService.excluir(999L);
		}
		catch(ResourceNotFoundException e)
		{
			System.out.println("Endereco buscado não foi encontrado.");
		}
		
		assertFalse(enderecoService.buscarPorId(e.getId()).isPresent());
		
	}
}