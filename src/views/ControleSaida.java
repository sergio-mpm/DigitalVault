package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import models.BDConnect;
import models.Usuario;

public class ControleSaida {

	public void callSaida(JPanel cabecalho , JPanel corpo1) {
		InterfaceSaida is = new InterfaceSaida();
		is.addCabecalho(cabecalho);
		is.addCorpo1(corpo1);
		addActSair(is);
		addActSairMenu(is);
		is.setVisible();
	}
	
	
	public void addActSair(InterfaceSaida is) {
		is.getSair().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BDConnect.Log(9003, Usuario.getInstance().Get_Email());
				BDConnect.Log(1002);
				is.getMenu().dispose();	
				System.exit(0);
			}
		});
	}
	
	public void addActSairMenu(InterfaceSaida is) {
		is.getSairMenu().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BDConnect.Log(9004, Usuario.getInstance().Get_Email());
				is.getMenu().dispose();
				ControleMenu cm = new ControleMenu();
				cm.callMenu();
				
			}
		});
		
	}
}
