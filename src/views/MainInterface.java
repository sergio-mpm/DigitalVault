package views;

import java.util.ArrayList;

import javax.swing.JFrame;

import models.LoginNameAuthenticator;
import models.BDConnect;
import models.ParDigitos;

public class MainInterface {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BDConnect.Estabelecer_Conexao();
		BDConnect.Log(1001);
		/*
		ArrayList<Par_Digitos> par_digitos = new ArrayList<Par_Digitos>();
		par_digitos.add(new Par_Digitos(3, 1));
		par_digitos.add(new Par_Digitos(3, 4));
		par_digitos.add(new Par_Digitos(5, 6));
		par_digitos.add(new Par_Digitos(7, 8));
		par_digitos.add(new Par_Digitos(9, 8));
		par_digitos.add(new Par_Digitos(1, 8));

		String path = "C:\\Users\\Main-x509.crt";
		
		//Autentificador.getInstance().Validar_Dados_Cadastro(path, 2, 391694, 391694);
		
		path = "C:\\Users\\Mauin-pkcs8-des.pem";

		/*Autentificador.getInstance().Iniciar_Validacao();
		Autentificador.getInstance().Validar_Email("admin@inf1416.puc-rio.br");
		Autentificador.getInstance().Validar_Senha(par_digitos);
		Autentificador.getInstance().Validar_ChavePrivada(path, "admin"); */
		
		/*path = "C:\\Users\\3\\Keys\\user01-pkcs8-des.pem";
		
		/*Autentificador.getInstance().Iniciar_Validacao();
		Autentificador.getInstance().Validar_Email("user01@inf1416.puc-rio.br");
		Autentificador.getInstance().Validar_Senha(par_digitos);
		Autentificador.getInstance().Validar_ChavePrivada(path, "user01");*/
		
		//ControleMenu ce = new ControleMenu();
		
		//ce.callMenu();
		
		ControleEmail e = new ControleEmail();
		e.callInterfaceEmail();
		
		
//		ControleMenu cm = new ControleMenu();
//		cm.callMenu();
		

	}

}
