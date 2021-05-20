package views;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import models.LoginNameAuthenticator;
import models.BDConnect;
import models.Usuario;
 
public class ControleAlterarSenha {

	 
	public void trocaSenha(JPanel cabecalho , JPanel panel) {
		InterfaceAlterarSenha ia = new InterfaceAlterarSenha();
		ia.addCabecalho(cabecalho);
		ia.addCorpo1(panel);
		voltar(ia);
		enviar(ia);
		ia.setVisible();
		
	}
	
	public void voltar(InterfaceAlterarSenha ia) {
		ia.getVoltarMenu().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BDConnect.Log(7006, Usuario.getInstance().Get_Email());
				
				ia.getMenu().dispose();
				ControleMenu cm = new ControleMenu();
				cm.callMenu();
				
			}
		});
	}
	
	public void enviar(InterfaceAlterarSenha ia) {
		ia.getSend().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				LoginNameAuthenticator.getInstance().Validar_Troca_Senha(ia.getCampoCertificado().getText(), ia.getPassword().getText(), ia.getRepeatedPassword().getText());				
			}
		});
	}
	
	
	
}
