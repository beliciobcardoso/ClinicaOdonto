package com.indentados.clinicaodonto.DAO.impl;

import com.indentados.clinicaodonto.DAO.ConfiguracaoJDBC;
import org.apache.log4j.Logger;

public class UsuarioDAOH2<T> {
    final static Logger logger = Logger.getLogger(UsuarioDAOH2.class);
    private final String jdbcDriver = "org.h2.Driver";
    private final String dbUrl = "jdbc:h2:d:\\h2\\db\\db_dados;INIT=RUNSCRIPT FROM 'create.sql'";
    private final String user = "sa";
    private final String password = "sa";
    private ConfiguracaoJDBC configuracaoJDBC;
}
