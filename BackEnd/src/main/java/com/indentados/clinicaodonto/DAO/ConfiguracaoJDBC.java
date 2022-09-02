package com.indentados.clinicaodonto.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConfiguracaoJDBC {
    private String jdbcDriver;
    private String dbUrl;
    private String usuario;
    private String senha;

    public ConfiguracaoJDBC(String jdbcDriver, String dbUrl, String usuario, String senha) {
        this.jdbcDriver = jdbcDriver;
        this.dbUrl = dbUrl;
        this.usuario = usuario;
        this.senha = senha;
    }

    public ConfiguracaoJDBC(String dbUrl, String usuario, String senha) {
        this.dbUrl = dbUrl;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(this.dbUrl,this.usuario,this.senha);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
