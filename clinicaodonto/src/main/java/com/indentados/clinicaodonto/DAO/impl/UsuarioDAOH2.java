package com.indentados.clinicaodonto.DAO.impl;

import com.indentados.clinicaodonto.DAO.ConfiguracaoJDBC;
import com.indentados.clinicaodonto.DAO.IDao;
import com.indentados.clinicaodonto.model.Usuario;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UsuarioDAOH2 implements IDao<Usuario> {
    final static Logger logger = Logger.getLogger(UsuarioDAOH2.class);
    private final String jdbcDriver = "org.h2.Driver";
    private final String dbUrl = "jdbc:h2:~/db/clinicaOdonto;INIT=RUNSCRIPT FROM 'create.sql'";
    private final String user = "sa";
    private final String password = "sa";
    private ConfiguracaoJDBC configuracaoJDBC;

    @Override
    public Usuario salvar(Usuario usuario) throws SQLException {
        logger.info("Cadastrando o Usuario: " + usuario.getNomeDeUsuario());
        configuracaoJDBC = new ConfiguracaoJDBC(jdbcDriver, dbUrl,user,password);
        Connection connection = configuracaoJDBC.getConnection();

        String query = String.format("INSERT INTO usuario (nomeDeUsuario, senha)" +
                " VALUES ('%S', '%S')", usuario.getNomeDeUsuario(), usuario.getSenha());
        try{
            Statement statement = connection.createStatement();
            statement.execute(query,Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()){
                usuario.setId(resultSet.getInt(1));
                logger.info("Capturando o id do banco: " + resultSet.getInt(1));
            }
        }catch (Exception e){
            logger.info("Ocorreu um erro na aplicação!!!!!");
            e.printStackTrace();
        }finally {
            logger.info("Logout do banco efetuado com sucesso!!!!");
            connection.close();
        }
        return usuario;
    }

    @Override
    public List<Usuario> buscarTodos() throws SQLException {
        return null;
    }

    @Override
    public boolean excluir(Integer id) throws SQLException {
        return false;
    }

    @Override
    public Usuario buscarId(Integer id) throws SQLException {
        return null;
    }

    @Override
    public Usuario atualizar(Usuario usuario) throws SQLException {
        logger.info("Alterar o Usuario: " + usuario.getNomeDeUsuario());
        configuracaoJDBC = new ConfiguracaoJDBC(jdbcDriver, dbUrl,user,password);
        Connection connection = configuracaoJDBC.getConnection();

        String queryUpdate = String.format("UPDATE usuario SET senha = '%S' WHERE id = '%S'",
                usuario.getSenha(),usuario.getId());

        int resultquery = 0;
        try {
            Statement statement = connection.createStatement();
            resultquery = statement.executeUpdate(queryUpdate);
            logger.info(resultquery);
            if (resultquery == 1){
                usuario = buscarId(usuario.getId());
            }else {
                logger.info("Falhar ao altera o produto");
            }
        }catch (Exception e){
            logger.info("Ocorreu um erro na aplicação!!!!!");
            e.printStackTrace();
        }finally {
            logger.info("Logout do banco efetuado com sucesso!!!!");
            connection.close();
        }
        return usuario;
    }
}
