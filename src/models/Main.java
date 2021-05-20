package models;
import java.io.FileInputStream;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main {
	
	public static void main(String[] args) {
		ArrayList<TrioFonemas> trio_fonemas = new ArrayList<TrioFonemas>();
		BDConnect.Estabelecer_Conexao();		

		trio_fonemas.add(new TrioFonemas("DA", "BE", "CO"));
		trio_fonemas.add(new TrioFonemas("GA", "HO", "FE"));
		trio_fonemas.add(new TrioFonemas("BO", "DE", "FA"));
		trio_fonemas.add(new TrioFonemas("HA", "CE", "GO"));
		trio_fonemas.add(new TrioFonemas("GE", "BA", "DO"));
		trio_fonemas.add(new TrioFonemas("HE", "FO", "CA"));

		String path = "D:\\Trabalho\\Eclipse\\JavaWorkspace\\DIgitalVault\\Pacote-T3\\Keys\\admin-x509.crt";
		
		path = "D:\\Trabalho\\Eclipse\\JavaWorkspace\\DIgitalVault\\Pacote-T3\\Keys\\user01-pkcs8-des.pem";
		
		LoginNameAuthenticator.getInstance().Iniciar_Validacao();
		LoginNameAuthenticator.getInstance().Validar_Email("user01@inf1416.puc-rio.br");
		LoginNameAuthenticator.getInstance().Validar_Senha(trio_fonemas);
		LoginNameAuthenticator.getInstance().Validar_ChavePrivada(path, "user01");
		
		Usuario user = Usuario.getInstance();
		if(user != null)
		{
			path = "D:\\Trabalho\\Eclipse\\JavaWorkspace\\DigitalVault\\Pacote-T3\\Files";
			ArrayList<Arquivo> arquivos = user.Parse_Index(path);
			user.Decriptar_Arquivo(arquivos.get(0));
			user.Decriptar_Arquivo(arquivos.get(1));
		}
	}
}
