package com.indentados.clinicaodonto.service;

import com.indentados.clinicaodonto.exception.ResourceNotFoundException;
import com.indentados.clinicaodonto.model.Dentista;
import com.indentados.clinicaodonto.model.Endereco;
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
class DentistaServiceTest
{
	@Autowired
    DentistaService dentistaService;
	static Dentista d;

	@BeforeEach
	void fazAntes()
	{
		//Antes de cada teste, cria um novo dentista.
		d = new Dentista("Nome", "Sobrenome", "email@email.com",  new Endereco(null, "rua", "num", "complemento", "bairro", "cidade", "estado", "cep"), "1");
	}

	@Test
	void testarSalvar()
	{
		//tenta usar a service para salvar o dentista criado previamente e verifica se ele possui ID após o salvamento.
		d = dentistaService.salvar(d);
		assertTrue(d.getId()>0);
	}

	@Test
	void testarBuscaId()
	{
		//salva um novo dentista e em seguita tenta buscá-lo. Verifica se um dentista é retornado ou não na busca.
		d = dentistaService.salvar(d);
		Optional<Dentista> d2 = dentistaService.buscarPorId(d.getId());

		assertEquals(d, d2.isPresent() ? d2.get() : null);
	}
	
	@Test
	void testarBuscarTodos()
	{
		//salva 2 novos dentistas e em seguita tenta buscá-los. Passará no teste, caso retorne mais de um dentista.
		d = dentistaService.salvar(d);
		Dentista d2 = new Dentista("Nome2", "Sobrenome2","email@email.com",new Endereco(null, "rua", "num", "complemento", "bairro", "cidade", "estado", "cep"),  "1");
		
		dentistaService.salvar(d2);
		
		List<Dentista> resultado = dentistaService.buscarTodos();
		assertTrue(resultado.size()>0);
	}

	@Test
	void testarAtualizacao()
	{
		//salva um novo dentista. Altera o nome e sobrenome e então atualiza passando o dentista com nome alterado.
		d = dentistaService.salvar(d);
		String nomeAlterado = "Nome Alterado", sobrenomeAlterado = "Sobrenome Alterado";
		d.setNome(nomeAlterado);
		d.setSobrenome(sobrenomeAlterado);
		dentistaService.atualizar(d);
		
		//após atualizar, busca pelo ID do denstista antes de ser salvo e verifica se o nome e sobrenome são iguais a
		//alteração. Se forem, o dentista foi alterado corretamente, senão, falha no teste.
		Optional<Dentista> d2 = dentistaService.buscarPorId(d.getId());
		if(d2.isPresent())
		{
			d = d2.get();
			assertEquals(d.getNome(), nomeAlterado);
			assertEquals(d.getSobrenome(), sobrenomeAlterado);
		}
		else
		{
			fail("não encontrou o dentista atualizado");
		}
	}

	@Test
	void testarDelete() throws ResourceNotFoundException
	{
		//salva um novo dentista para tentar excluí-lo.
		d = dentistaService.salvar(d);
		dentistaService.excluir(d.getId());
		//Após a exclusão, tenta buscar no banco pelo id do dentista deletado e passa no teste caso não encontre.
		assertFalse(dentistaService.buscarPorId(d.getId()).isPresent());
	}
}