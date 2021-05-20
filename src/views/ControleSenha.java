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
	
	private ArrayList<TrioFonemas> atualValues;
	
	public void callInterfacePassword() {
		InterfaceSenha ip = new InterfaceSenha(6);
		atualValues = new ArrayList<TrioFonemas>();
		addValueAndActionToButton(ip);
		AddActButtonSend(ip);
		addActResetButton(ip);
		ip.setVisibleScreen();
		
	}
	
	
	 public void addValuetoButton(String fonema1, String fonema2, String fonema3, JButton button) {
	 	button.setText(fonema1 + "-" + fonema2 + "-" + fonema3);
	 	button.setPreferredSize(new Dimension(100,100));
	 }
	 
	 public void addValueAndActionToButton(InterfaceSenha ip) {
	 	String n1, n2, n3;
	 	ArrayList<TrioFonemas> fonemas = Functions.Gerar_Set_Trios();
	 	for(int i = 0; i <  ip.getButtons().size() ; i++) {
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
	 	String f1 = text.substring(0,2);
	 	String f2 = text.substring(2,4);
	 	String f3 = text.substring(4,6);
	 	TrioFonemas trio = new TrioFonemas(f1, f2, f3);
	 	atualValues.add(trio);
	 }
	 
	public void addActResetButton(InterfaceSenha i) { 
		i.getReset().addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				atualValues = new ArrayList<TrioFonemas>();
	        }
			});
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
