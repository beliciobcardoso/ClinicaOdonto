package com.indentados.clinicaodonto.service;

import com.indentados.clinicaodonto.DAO.IDao;
import com.indentados.clinicaodonto.model.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service

public class EnderecoService {

    @Autowired
    IDao<Endereco> enderecoDaoH2;

    public Endereco salvar(Endereco endereco) throws SQLException{
        return enderecoDaoH2.salvar(endereco);
    }

    public List<Endereco> buscarTodos() throws SQLException{
        return  enderecoDaoH2.buscarTodos();
    }

    public Endereco buscarId(Integer id) throws SQLException{
        return enderecoDaoH2.buscarId(id);
    }

    public void atualizar(Endereco endereco) throws SQLException{
        enderecoDaoH2.atualizar(endereco);
    }

    public void excluir(Integer id) throws SQLException{
        enderecoDaoH2.excluir(id);
    }










}
