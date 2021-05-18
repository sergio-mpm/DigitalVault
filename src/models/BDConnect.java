package models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BDConnect {
	
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	
	static String url = "jdbc:mysql://localhost:3306/sectest?useTimezone=true&serverTimezone=UTC";
	static String user = "root";
	static String password  = "root";
	
	static String query = "select * from teste";
	 
	private static Connection connection;
	
	public static void Estabelecer_Conexao()
	{
		try {
			
			connection = DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to Connect");
		}
		
		System.out.println("Connection Successfull");
	}

	public static int Get_Id_by_Email(String email)
	{
		String query = "SELECT id FROM usuarios WHERE login_name ='" + email + "'";
		ResultSet rs = Run_Query(query);
		
		try {
		    if(rs.first())
		    {
		    	int value = rs.getInt(1);
		    	rs.close();
		    	return value;
		    }
		    else
		    	return -1;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to Connect");
		}
		
		return -1;
	}
	
	public static String Get_Senha_by_Id(int id)
	{
		String query = "SELECT senha FROM usuarios WHERE id =" + id;
		ResultSet rs = Run_Query(query);
		
		try {
			if(rs.first())
			{
				String value = rs.getString(1);
		    	rs.close();
		    	return value;
			}
			else
				return null;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to Connect");
		}
		
		return null;
	}
	
	public static String Get_Salt_by_Id(int id)
	{
		String query = "SELECT SALT FROM usuarios WHERE id =" + id;
		ResultSet rs = Run_Query(query);
		
		try {
			if(rs.first())
			{
				String value = rs.getString(1);
				rs.close();
				return value;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to Connect");
		}
		
		return null;
	}

	public static boolean Usuario_Existe(String login_name)
	{
		String query = "SELECT id FROM usuarios WHERE login_name ='" + login_name + "'";
		ResultSet rs = Run_Query(query);
		
		try {
			if(rs.first())
			{
				rs.close();
				return true;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to Connect");
		}
		
		return false;
	}

	public static void Cadastrar_Usuario(String email, String nome, String certificado_digital, String SALT, int grupo, String senha)
	{
		String query = "INSERT INTO usuarios (login_name, nome, certificado_digital, SALT, senha, id_grupo) VALUES ('" +
				email + "','" + nome + "','" + certificado_digital + "','" + SALT + "','" + senha + "'," + grupo +  ")";
		try {
			PreparedStatement pstmt = connection.prepareStatement(query);
		    pstmt.executeUpdate();
		    pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String Get_Grupo_Usuario(int id)
	{
		String query = "SELECT grupos.nome FROM grupos INNER JOIN usuarios ON usuarios.id_grupo = grupos.GID where usuarios.id =" + id;
		ResultSet rs = Run_Query(query);
		try {
			if(rs.next())
			{
				String value = rs.getString(1);
				rs.close();
				return value;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to Connect");
		}
		
		return null;
	}
	
	public static String Get_Certificado_Digital_Usuario(int id)
	{
		String query = "SELECT certificado_digital FROM usuarios where id=" + id;
		ResultSet rs = Run_Query(query);
		
		try {
			if(rs.next())
			{
				String value = rs.getString(1);
				rs.close();
				return value;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void Atualizar_Certificado_Usuario(int id, String certificado_digital)
	{
		String query = "UPDATE usuarios SET certificado_digital = '" + certificado_digital + "'  WHERE id =?";
		try {
		    PreparedStatement pstmt = connection.prepareStatement(query);
		    pstmt.setInt(1, id);
		    pstmt.executeUpdate();
		    pstmt.close();
		
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to Connect");
		}
	}
	
	public static void Atualizar_Senha_Usuario(int id, String senha, String SALT)
	{
		String query = "UPDATE usuarios SET senha = '" + senha + "', SALT ='" + SALT + "'  WHERE id =" + id;
		try {
		    PreparedStatement pstmt = connection.prepareStatement(query);
		    pstmt.executeUpdate();
		    pstmt.close();
		
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to Connect");
		}
	}
	
	public static int Numero_Acessos_Usuario(String login_name)
	{
		String query = "SELECT count(id) FROM registros WHERE codigo = 4003 AND login_name ='"+login_name + "'";
		ResultSet rs = Run_Query(query);
		try {
		    if(rs.next())
		    {
		    	int value = rs.getInt(1);
		    	rs.close();
		    	return value;
		    }
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to Connect");
		}
		
		return 0;
	}
	
	public static int Numero_Consultas_Usuario(String login_name)
	{
		String query = "SELECT count(id) FROM registros WHERE codigo = 8003 AND login_name ='"+login_name + "'";
		ResultSet rs = Run_Query(query);
		try {
		    if(rs.next())
		    {
		    	int value = rs.getInt(1);
		    	rs.close();
		    	return value;
		    }
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to Connect");
		}
		
		return 0;
	}
	
	public static int Total_Usuarios_Sistema()
	{
		String query = "SELECT count(id) FROM usuarios";
		ResultSet rs = Run_Query(query);
		try {
		    if(rs.next())
		    {
		    	int value = rs.getInt(1);
		    	rs.close();
		    	return value;
		    }
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to Connect");
		}
		
		return 0;
	}
	
	public static void Bloquear_Usuario(int id)
	{
		String query = "UPDATE usuarios SET data_bloqueio = adddate(CURRENT_TIMESTAMP(), INTERVAL 2 MINUTE) WHERE id =?";
		try {
		    PreparedStatement pstmt = connection.prepareStatement(query);
		    pstmt.setInt(1, id);
		    pstmt.executeUpdate();
		    pstmt.close();
		
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to Connect");
		}
	}
	
	public static boolean Usuario_Bloqueado(int id)
	{
		String query = "SELECT id FROM usuarios WHERE data_bloqueio <= CURRENT_TIMESTAMP() AND id =" + id;
		ResultSet rs = Run_Query(query);
		try {
			if(rs.next())
			{
				rs.close();
				return false;
			}
					
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to Connect");
		}

		return true;
	}
	
	private static ResultSet Run_Query(String query)
	{
		try {
		    Statement st = connection.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    return rs;
		
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to Connect");
		}
		
		return null;
	}
	
	public static void Log(int codigo)
	{
		try {
			String query = "INSERT INTO registros (codigo) VALUES (" + codigo + ")";
			PreparedStatement pstmt = connection.prepareStatement(query);
		    pstmt.executeUpdate();
		    pstmt.close();
		
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to Connect");
		}
	}
	
	public static void Log(int codigo, String login_name)
	{
		try {
		    String query = "INSERT INTO registros (codigo, login_name) VALUES (" + codigo + ",'" + login_name + "')";
			PreparedStatement pstmt = connection.prepareStatement(query);
		    pstmt.executeUpdate();
		    pstmt.close();
		    
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to Connect");
		}
	}
	
	public static void Log(int codigo, String login_name, String fileName)
	{
		try {
		    String query = "INSERT INTO registros (codigo, login_name, file_name) VALUES (" + codigo + ",'" + login_name + "','" + fileName + "')";
			PreparedStatement pstmt = connection.prepareStatement(query);
		    pstmt.executeUpdate();
		    pstmt.close();
		    
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to Connect");
		}
	}
	
	public static void Print_Logs()
	{
		String query = "SELECT registros.data_ocorrencia, REPLACE(REPLACE(mensagens.mensagem, '<login_name>', registros.login_name), '<arq_name>', registros.file_name) " + 
				"FROM registros " + 
				"INNER JOIN mensagens " + 
				"ON registros.codigo = mensagens.codigo order by data_ocorrencia;";
		ResultSet rs = Run_Query(query);
		try {
			int i = 1;
			while(rs.next())
			{
				System.out.println(i + "\t" + rs.getString(1) + "\t" + rs.getString(2));
				i++;
			}
			rs.close();
					
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to Connect");
		}
	}
	
	public static void Print_Logs2()
	{
		String query = "select data_ocorrencia, login_name, file_name, mensagem from registros join mensagens where mensagens.codigo = registros.codigo";
		ResultSet rs = Run_Query(query);
		try {
			int i = 1;
			while(rs.next())
			{
				String mensagem = rs.getString(4);
				mensagem = mensagem.replaceAll("<login_name>", rs.getString(2));
				mensagem = mensagem.replaceAll("<arq_name>", rs.getString(3));
				System.out.println(i + "\t" + rs.getString(1) + "\t" + mensagem);
				i++;
			}
			rs.close();
					
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to Connect");
		}
	}
	
}