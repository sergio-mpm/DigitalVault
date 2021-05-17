package models;

import java.io.FileOutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.JOptionPane;

public class Usuario {

	private static Usuario instance;

	public static Usuario getInstance()
	{
		if(instance == null)
		{
			JOptionPane.showMessageDialog(null, "Usuario não foi passou pela Autentificação!", "Erro", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return instance;
	}
	public static void SetInstance(Usuario user)
	{
		instance = user;
	}

	private int id;
	private String nome;
	private String email;
	private X509Certificate certificado_digital;
	private String grupo;
	private PrivateKey privateKey;
	private PublicKey publicKey;

	public Usuario(int id, String nome, String email, X509Certificate certificado_digital, String grupo, PrivateKey privateKey)
	{
		this.id = id;
		this.certificado_digital = certificado_digital;
		this.nome = nome;
		this.email = email;
		this.privateKey = privateKey;
		this.publicKey = certificado_digital.getPublicKey();
		this.grupo = grupo;
	}

	public byte[] Decriptar_File(String path, String name)
	{
		byte[] index_crypt = LoginNameAuthenticator.getInstance().Ler_File_Bin(path + "\\" + name + ".enc");
		byte[] envelope_digital = LoginNameAuthenticator.getInstance().Ler_File_Bin(path + "\\" + name + ".env");
		byte[] assinatura_digital = LoginNameAuthenticator.getInstance().Ler_File_Bin(path + "\\" + name + ".asd");
		
		if(envelope_digital == null || assinatura_digital == null)
		{
			JOptionPane.showMessageDialog(null, "Arquivos de validação faltando", "Erro", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] seed = cipher.doFinal(envelope_digital);

			KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(seed);
			int keyBitSize = 56;

			keyGenerator.init(keyBitSize, secureRandom);

			SecretKey secretKey = keyGenerator.generateKey();

			cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] index_data_bytes = cipher.doFinal(index_crypt);
			
			if(name == "index")
				BDConnect.Log(8005, email);
			else
			{
				BDConnect.Log(8013, email, name);
			}
		
			Signature sig = LoginNameAuthenticator.getInstance().Gerar_AssinaturaDigital("MD5withRSA");
			sig.initVerify(publicKey);
			sig.update(index_data_bytes);

			if(sig.verify(assinatura_digital))
			{
				if(name == "index")
					BDConnect.Log(8006, email);
				else
				{
					BDConnect.Log(8014, email, name);
				}

				return index_data_bytes;
			}
			else 
			{
				if(name == "index")
					BDConnect.Log(8008, email);
				else
				{
					BDConnect.Log(8016, email, name);
				}
				
				JOptionPane.showMessageDialog(null, "Integridade e Autenticidade Corrompida!", "Erro", JOptionPane.ERROR_MESSAGE);
				System.out.println("Integridade e Autenticidade Corrompida!");
			}
		}
		catch (Exception e) {
			if(name == "index")
			{
				JOptionPane.showMessageDialog(null, "Falha na decriptação do arquivo de índice", "Erro", JOptionPane.ERROR_MESSAGE);
				BDConnect.Log(8007, email);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Falha na decriptação do arquivo " + name, "Erro", JOptionPane.ERROR_MESSAGE);
				BDConnect.Log(8015, email, name);
			}
			e.printStackTrace();
		}

		return null;
	}
	
	public void Decriptar_Arquivo(Arquivo arquivo)
	{
		if(Tem_Acesso(arquivo))
		{
			byte[] file_data = Decriptar_File(arquivo.GetPath(), arquivo.GetNomeCodigo());
			
			try {
				FileOutputStream fos = new FileOutputStream(arquivo.GetPath() + "\\" + arquivo.GetNomeSecreto());
				fos.write(file_data);
				System.out.println("Arquivo Salvo com Sucesso!");
				JOptionPane.showMessageDialog(null, "Arquivo " +  arquivo.GetNomeSecreto() + " Salvo com Sucesso!", "Erro", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Falha na escrita do arquivo!");
				JOptionPane.showMessageDialog(null, "Falha na escrita do arquivo" , "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public ArrayList<Arquivo> Parse_Index(String path)
	{
		byte[] index_crypt = LoginNameAuthenticator.getInstance().Ler_File_Bin(path + "\\index.enc");
		if(index_crypt == null)
		{
			BDConnect.Log(8004, email);
			JOptionPane.showMessageDialog(null, "Caminho invalido", "Erro", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		byte[] index_data_bytes = Decriptar_File(path, "index");
		if(index_data_bytes == null)
		{
			return null;
		}
		
		String index_data = LoginNameAuthenticator.getInstance().Byte_to_String(index_data_bytes);
		
		ArrayList<Arquivo> sistema_files = new ArrayList<Arquivo>();
		String[] arquivos = index_data.split("\n");

		for(String arquivo : arquivos)
		{
			String[] partes = arquivo.split(" ");
			String nome_codigo = partes[0];
			String nome_secreto = partes[1];
			String dono = partes[2];
			String grupo = partes[3];
			sistema_files.add(new Arquivo(nome_codigo, nome_secreto, dono, grupo, path));
		}

		return sistema_files;
	}

	public boolean Tem_Acesso(Arquivo arquivo)
	{
		if(arquivo.GetDono().equals(email) || arquivo.GetGrupo().equals(grupo))
		{
			BDConnect.Log(8011, email, arquivo.GetNomeCodigo());
			return true;
		}
		BDConnect.Log(8012, email, arquivo.GetNomeCodigo());
		JOptionPane.showMessageDialog(null, "Usuario não tem acesso ao Arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
		System.out.println("Usuario não tem acesso ao Arquivo");
		return false;
	}
	
	public String Get_Nome()
	{
		return nome;
	}
	
	public String Get_Email()
	{
		return email;
	}
	
	public String Get_Grupo()
	{
		return grupo;
	}
	
	public int Get_Acessos()
	{
		return BDConnect.Numero_Acessos_Usuario(email);
	}
	
	public int Get_Total_Consultas()
	{
		return BDConnect.Numero_Consultas_Usuario(email);
	}
}
