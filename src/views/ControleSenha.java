package views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import models.LoginNameAuthenticator;
import models.BDConnect;
import models.Functions;
import models.ParDigitos;
import models.TrioFonemas;

public class ControleSenha {
	
	//private ArrayList<ParDigitos> atualValues;
	private ArrayList<TrioFonemas> atualValues;
	
	public void callInterfacePassword() {
		InterfaceSenha ip = new InterfaceSenha(6);
		//atualValues = new ArrayList<ParDigitos>();
		atualValues = new ArrayList<TrioFonemas>();
		addValueAndActionToButton(ip);
		AddActButtonSend(ip);
		addActResetButton(ip);
		ip.setVisibleScreen();
		
	}
	
	/*
	public void addValuetoButton(int number1 , int number2 , JButton button) {
		String number1String = Integer.toString(number1);
		String number2String = Integer.toString(number2);
		button.setText(number1String + "|" + number2String);
		button.setPreferredSize(new Dimension(80,80));
	}
	*/
	
	 public void addValuetoButton(String fonema1, String fonema2, String fonema3, JButton button) {
	 	button.setText(fonema1 + "-" + fonema2 + "-" + fonema3);
	 	button.setPreferredSize(new Dimension(100,100));
	 }
	 
	 public void addValueAndActionToButton(InterfaceSenha ip) {
	 	String n1, n2, n3;
	 	ArrayList<TrioFonemas> fonemas = Functions.Gerar_Set_Trios();
	 	for(int i = 0; i < ip.getButtons().size() ; i++) {
	 		n1 = fonemas.get(i).fonema1;
	 		n2 = fonemas.get(i).fonema2;
	 		n3 = fonemas.get(i).fonema3;
	 		JButton button = ip.getButtons().get(i);
	 		addValuetoButton(n1, n2, n3, button);
	 		if(button.getActionListeners().length == 0) {
	 			addActButtonNumbers(button, ip);
	 		}
	 	}
	 }
	 
	 public void getValueBottom(JButton button) {
	 	String text = button.getText().replace("-", "");
	 	String f1 = text.substring(0,1);
	 	String f2 = text.substring(2,3);
	 	String f3 = text.substring(4,5);
	 	TrioFonemas trio = new TrioFonemas(f1, f2, f3);
	 	atualValues.add(trio);
	 }
	  
	  
	
	/*
	public void addValueAndActionToButton(InterfaceSenha ip) {
		int n1 , n2 = 0;
		ArrayList<ParDigitos> digitos = Functions.Gerar_Set_Pares();
		for (int i = 0 ; i < ip.getButtons().size() ; i++) {          
			n1 = digitos.get(i).n1;
			n2 = digitos.get(i).n2;
			JButton button = ip.getButtons().get(i);
			addValuetoButton(n1, n2, button);
			if ( button.getActionListeners().length == 0) {
				addActButtonNumbers(button, ip);
			}
		}		
	}
	*/
	public void addActResetButton(InterfaceSenha i) { 
		i.getReset().addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e)
	        {	     
				//atualValues = new ArrayList<ParDigitos>();
				atualValues = new ArrayList<TrioFonemas>();
	        }
			});
	}
	/*
	public void getValueBottom(JButton button) {
		String text = button.getText().replace("|", "");
		int n1 = Character.getNumericValue(text.charAt(0));
		int n2 = Character.getNumericValue(text.charAt(1));
		ParDigitos par = new ParDigitos(n1, n2);
		atualValues.add(par);
		
	}*/
	
	public void addActButtonNumbers(JButton button , InterfaceSenha i) {

		button.addActionListener( new ActionListener() {
		public void actionPerformed(ActionEvent e)
        {	     
			getValueBottom(button);
            addValueAndActionToButton(i);
            i.getScreen().revalidate();
        }
		});
	}	
	
	public void AddActButtonSend(InterfaceSenha i) {
		i.getSend().addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				int validarSenha = LoginNameAuthenticator.getInstance().Validar_Senha(atualValues);
				
				if ( validarSenha == -1)
				{
					//atualValues = new ArrayList<ParDigitos>();
					atualValues = new ArrayList<TrioFonemas>();
				}
				if ( validarSenha == -2) {
					BDConnect.Log(3002, LoginNameAuthenticator.getInstance().Get_LoginName());
					i.getScreen().dispose();
					ControleEmail ce =  new ControleEmail();
					ce.callInterfaceEmail();				
				}
				if (validarSenha == 1) {
					BDConnect.Log(3002, LoginNameAuthenticator.getInstance().Get_LoginName());
					i.getScreen().dispose();
					ControlePrivateKey cc = new ControlePrivateKey();
					cc.callControllerCertificado();
				}
				
			}
		});
	}
}
