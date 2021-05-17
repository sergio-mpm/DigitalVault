package models;

public class Arquivo {
	private String nomecodigo;
	private String nomesecreto;
	private String dono;
	private String grupo;
	private String path;
	
	public Arquivo(String nome_codigo, String nome_secreto, String dono, String grupo, String path)
	{
		this.nomecodigo = nome_codigo;
		this.nomesecreto = nome_secreto;
		this.dono = dono;
		this.grupo = grupo;
		this.path = path;
	}
	
	public String GetNomeCodigo()
	{
		return nomecodigo;
	}
	
	public String GetNomeSecreto()
	{
		return nomesecreto;
	}
	
	public String GetDono()
	{
		return dono;
	}
	
	public String GetGrupo()
	{
		return grupo;
	}
	
	public String GetPath()
	{
		return path;
	}
}
