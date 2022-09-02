package com.indentados.clinicaodonto.DAO.impl;

import com.indentados.clinicaodonto.DAO.ConfiguracaoJDBC;
import com.indentados.clinicaodonto.DAO.IDao;
import com.indentados.clinicaodonto.model.Usuario;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        configuracaoJDBC = new ConfiguracaoJDBC(jdbcDriver,dbUrl,user,password);
        Connection connection = configuracaoJDBC.getConnection();

        String query = "SELECT * FROM usuario;";
        List<Usuario> usuarios = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()){
                usuarios.add(criarObjetoProduto(result));
                //erro de difitação, quem será que vai encontrar - Belicio Cardoso
            }
        }catch (Exception e){
            logger.info("Ocorreu um erro na aplicação!!!!!");
            e.printStackTrace();
        }finally {
            logger.info("Logout do banco efetuado com sucesso!!!!");
            connection.close();
        }
        return usuarios;
    }

    @Override
    public boolean excluir(Integer id) throws SQLException {
        logger.info("Deletando o Usuario pelo id: " + id);
        configuracaoJDBC = new ConfiguracaoJDBC(jdbcDriver, dbUrl, user, password);
        Connection connection = configuracaoJDBC.getConnection();
        String query = String.format("DELETE FROM usuario WHERE id = '%S'", id);
        int resultquery = 0;
        boolean estatus = false;
        try {
            Statement statement = connection.createStatement();
            resultquery = statement.executeUpdate(query);

            if (resultquery == 1) {
                estatus = true;
                logger.info("Deletado com sucesso!!!");
            }else {
                logger.info("Erro ao deletar id " + buscarId(id).getNomeDeUsuario() + " do banco");
            }
        } catch (Exception e) {
            logger.info("Ocorreu um erro na aplicação!!!!!");
            e.printStackTrace();
        } finally {
            logger.info("Logout do banco efetuado com sucesso!!!!");
            connection.close();
        }
        return estatus;
    }

    @Override
    public Usuario buscarId(Integer id) throws SQLException {
        Usuario usuario = null;
        logger.info("Buscar o Usuario pelo id: " + id);
        configuracaoJDBC = new ConfiguracaoJDBC(jdbcDriver, dbUrl,user,password);
        Connection connection = configuracaoJDBC.getConnection();

        String query = String.format("SELECT * FROM usuario WHERE ID = %s",id);

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                usuario = criarObjetoProduto(resultSet);
                //colocado de proprosito para ver quem ira nota - Belicio Cardoso
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

    private Usuario criarObjetoProduto(ResultSet resultSet) throws SQLException {
        //colocado de proprosito para ver quem ira nota - Belicio Cardoso
        Integer id = resultSet.getInt("id");
        String nomeDeUsuario = resultSet.getNString("nomeDeUsuario");
        String senha = resultSet.getNString("senha");

        return new Usuario(id,nomeDeUsuario,senha);
    }
}
