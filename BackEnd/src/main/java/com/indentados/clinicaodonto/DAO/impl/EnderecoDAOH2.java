package com.indentados.clinicaodonto.DAO.impl;

import com.indentados.clinicaodonto.DAO.ConfiguracaoJDBC;
import com.indentados.clinicaodonto.DAO.IDao;
import com.indentados.clinicaodonto.model.Endereco;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Configuration
public class EnderecoDAOH2 implements IDao<Endereco> {

    private ConfiguracaoJDBC configuracaoJDBC;

    final static Logger log = Logger.getLogger(EnderecoDAOH2.class);
    private final String jdbcDriver = "org.h2.Driver";
    private final String dbUrl = "jdbc:h2:~/db/clinicaOdonto;INIT=RUNSCRIPT FROM 'create.sql'";
    private final String user = "sa";
    private final String password = "sa";


    @Override
    public Endereco salvar(Endereco endereco) throws SQLException {
        log.info("Abrindo conexão");

        String SQLInsert = String.format("INSERT INTO endereco (rua, numero, complemento, bairro, cidade, estado)" +
                "values ('%s','%s','%s','%s','%s','%s')", endereco.getRua(),endereco.getNumero(), endereco.getComplemento(),
        endereco.getBairro(),endereco.getCidade(),endereco.getEstado());

        Connection connection = null;

        try{

        }catch (Exception e){
            e.printStackTrace();
            log.error("Erro ao inserir o endereço: " + e.getMessage());
        }finally {
            log.info("Fechando conexão");
            connection.close();
        }

        return null;
    }

    @Override
    public List<Endereco> buscarTodos() throws SQLException {
        return null;
    }

    @Override
    public boolean excluir(Integer id) throws SQLException {
        return false;
    }


    @Override
    public Endereco buscarId(Integer id) throws SQLException {
        return null;
    }

    @Override
    public Endereco atualizar(Endereco endereco) throws SQLException {
        return null;
    }
}
