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

public class ControleSenha {
	
	private ArrayList<ParDigitos> atualValues;
	
	public void callInterfacePassword() {
		InterfaceSenha ip = new InterfaceSenha(6);
		atualValues = new ArrayList<ParDigitos>();
		addValueAndActionToButton(ip);
		AddActButtonSend(ip);
		addActResetButton(ip);
		ip.setVisibleScreen();
		
	}
	
	
	public void addValuetoButton(int number1 , int number2 , JButton button) {
		String number1String = Integer.toString(number1);
		String number2String = Integer.toString(number2);
		button.setText(number1String + "|" + number2String);
		button.setPreferredSize(new Dimension(80,80));
	}
	
	
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
	
	public void addActResetButton(InterfaceSenha i) {
		i.getReset().addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e)
	        {	     
				atualValues = new ArrayList<ParDigitos>();
	        }
			});
	}
	
	public void getValueBottom(JButton button) {
		String text = button.getText().replace("|", "");
		int n1 = Character.getNumericValue(text.charAt(0));
		int n2 = Character.getNumericValue(text.charAt(1));
		ParDigitos par = new ParDigitos(n1, n2);
		atualValues.add(par);
		
	}
	
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
					atualValues = new ArrayList<ParDigitos>();
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
