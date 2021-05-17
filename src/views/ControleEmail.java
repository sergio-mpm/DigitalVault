package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Autentificador;
import model.BD;

public class ControleEmail {

	
	public void callInterfaceEmail() {
		InterfaceEmail ie = new InterfaceEmail();
		Autentificador.getInstance().Iniciar_Validacao();
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
	            int validate = Autentificador.getInstance().Validar_Email(text);
	            
	            if (validate == 1) {
	            	BD.Log(2002);
	            	ie.getArea().dispose();
	            	ControllerPassword cp = new ControllerPassword();
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
