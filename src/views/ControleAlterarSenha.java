package views;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import model.Autentificador;
import model.BD;
import model.Usuario;

public class ControleAlterarSenha {

	
	public void callAlterarSenha(JPanel cabecalho , JPanel corpo1) {
		InterfaceAlterarSenha ia = new InterfaceAlterarSenha();
		ia.addCabecalho(cabecalho);
		ia.addCorpo1(corpo1);
		addActVoltarMenu(ia);
		addActSend(ia);
		ia.setVisible();
		
	}
	
	public void addActVoltarMenu(InterfaceAlterarSenha ia) {
		ia.getVoltarMenu().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BD.Log(7006, Usuario.getInstance().Get_Email());
				
				ia.getMenu().dispose();
				ControleMenu cm = new ControleMenu();
				cm.callMenu();
				
			}
		});
	}
	
	public void addActSend(InterfaceAlterarSenha ia) {
		ia.getSend().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Autentificador.getInstance().Validar_Troca_Senha(ia.getCampoCertificado().getText(), ia.getPassword().getText(), ia.getRepeatedPassword().getText());
				
			}
		});
	}
	
	
	
}
