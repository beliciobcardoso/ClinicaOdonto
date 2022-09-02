package com.indentados.clinicaodonto.DAO;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {
    public T salvar(T t) throws SQLException;

    public List<T> buscarTodos() throws SQLException;

    public boolean excluir(Integer id) throws SQLException;

    public T buscarId(Integer id) throws SQLException;

    public T atualizar(T t) throws SQLException;
}
