package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.LoginNameAuthenticator;
import models.BDConnect;

public class ControleEmail {

	
	public void callInterfaceEmail() {
		InterfaceEmail ie = new InterfaceEmail();
		LoginNameAuthenticator.getInstance().Iniciar_Validacao();
		addActs(ie);
		ie.setVisible();
	}
	
	
	public void addActs(InterfaceEmail ie) {
		addActSend(ie);
		addActReset(ie);		
	}
	
	
	public void addActSend(InterfaceEmail ie) {
		ie.getSend().addActionListener( new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	String text = ie.getTf().getText();
	            int validate = LoginNameAuthenticator.getInstance().Validar_Email(text);
	            
	            if (validate == 1) {
	            	BDConnect.Log(2002);
	            	ie.getArea().dispose();
	            	ControleSenha cp = new ControleSenha();
	            	cp.callInterfacePassword();	            	
	            }
	        }
	});
	}
	
	public void addActReset(InterfaceEmail ie) {
		ie.getReset().addActionListener( new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	                ie.getTf().setText("");
	        }
	});
	}
}
