package models;
import java.io.FileInputStream;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main {
	
	public static void main(String[] args) {
		ArrayList<ParDigitos> par_digitos = new ArrayList<ParDigitos>();
		BDConnect.Estabelecer_Conexao();		

		par_digitos.add(new ParDigitos(3, 1));
		par_digitos.add(new ParDigitos(9, 4));
		par_digitos.add(new ParDigitos(1, 6));
		par_digitos.add(new ParDigitos(6, 8));
		par_digitos.add(new ParDigitos(9, 8));
		par_digitos.add(new ParDigitos(4, 8));

		String path = "C:\\Users\\Maucio\\Desktop\\Puc\\Segys\\admin-x509.crt";
		
		path = "C:\\Users\\Maucio\\Desktop\\Puc\\Segurpem";
		
		LoginNameAuthenticator.getInstance().Iniciar_Validacao();
		LoginNameAuthenticator.getInstance().Validar_Email("user01@inf1416.puc-rio.br");
		LoginNameAuthenticator.getInstance().Validar_Senha(par_digitos);
		LoginNameAuthenticator.getInstance().Validar_ChavePrivada(path, "user01");
		
		Usuario user = Usuario.getInstance();
		if(user != null)
		{
			path = "C:\\Users\\Mauio\\Desktop\\Puc\\Segur\\T3\\Pacote-T3\\Files";
			ArrayList<Arquivo> arquivos = user.Parse_Index(path);
			user.Decriptar_Arquivo(arquivos.get(0));
			user.Decriptar_Arquivo(arquivos.get(1));
		}
	}
}
