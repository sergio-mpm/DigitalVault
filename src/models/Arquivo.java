package models;

public class Arquivo {
	private String nome_codigo;
	private String nome_secreto;
	private String dono;
	private String grupo;
	private String path;
	
	public Arquivo(String nome_codigo, String nome_secreto, String dono, String grupo, String path)
	{
		this.nome_codigo = nome_codigo;
		this.nome_secreto = nome_secreto;
		this.dono = dono;
		this.grupo = grupo;
		this.path = path;
	}
	
	public String GetNomeCodigo()
	{
		return nome_codigo;
	}
	
	public String GetNomeSecreto()
	{
		return nome_secreto;
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
