package com.indentados.clinicaodonto.model;

public class Usuario {
	private Pessoa pessoa;
	private String user;
	private String passwd;
	private boolean admin;
	
	public Usuario(Pessoa pessoa, String user, String passwd, boolean admin)
	{
		this.pessoa = pessoa;
		this.user = user;
		this.passwd = passwd;
		this.admin = admin;
	}
	
	public Pessoa getPessoa()
	{
		return pessoa;
	}
	
	public void setPessoa(Pessoa pessoa)
	{
		this.pessoa = pessoa;
	}
	
	public String getUser()
	{
		return user;
	}
	
	public void setUser(String user)
	{
		this.user = user;
	}
	
	public String getPasswd()
	{
		return passwd;
	}
	
	public void setPasswd(String passwd)
	{
		this.passwd = passwd;
	}
	
	public boolean isAdmin()
	{
		return admin;
	}
	
	public void setAdmin(boolean admin)
	{
		this.admin = admin;
	}
}
