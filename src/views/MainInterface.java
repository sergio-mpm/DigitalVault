package views;

import java.util.ArrayList;

import javax.swing.JFrame;

import models.LoginNameAuthenticator;
import models.BDConnect;
import models.ParDigitos;

public class MainInterface {

	public static void main(String[] args) {
		
		BDConnect.Estabelecer_Conexao();
		BDConnect.Log(1001);
		
		ControleEmail ce = new ControleEmail();
		ce.callInterfaceEmail();
		
	}

}
