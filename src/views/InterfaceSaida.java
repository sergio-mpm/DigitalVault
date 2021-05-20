package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.BDConnect;
import models.Usuario;

public class InterfaceSaida {

	private JFrame menu;
	private JPanel cabecalho , corpo1 , corpo2 ;
	private JButton sair , sairMenu;
	private JLabel saida , msgSaida;
	
	public InterfaceSaida() {
		BDConnect.Log(9001, Usuario.getInstance().Get_Email());
		
		menu = new JFrame("Tela de saída");
		menu.setSize(450,300);
		menu.setLocationRelativeTo(null);
		corpo2 = new JPanel(new BorderLayout());
		menu.add(corpo2 , "South");
		//corpo2.setLayout(new BoxLayout(corpo2, BoxLayout.Y_AXIS));
		setLabels();
		setButtons();
		
		menu.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	BDConnect.Log(1002);
                System.out.println("Sistema sendo fechado pelo usuario");
                System.exit(0);
            }
        });
	}
	

	public void addCorpo1(JPanel corpo1) {
		this.corpo1 = corpo1;
		corpo1.setLayout(new BorderLayout());
		menu.add(corpo1 , BorderLayout.CENTER);
	}
	
	public void addCabecalho(JPanel cabecalho) {
		this.cabecalho = cabecalho;
		cabecalho.setLayout(new BorderLayout());
		menu.add(cabecalho , BorderLayout.NORTH);
	}
	
	public void setLabels() {
		msgSaida = new JLabel("Saída do sistema: Pressione o botão Sair para confirmar.");
		corpo2.add(msgSaida, "North");
	}
	
	public void setButtons() {
		sair = new JButton("Sair");
		sairMenu = new JButton("Voltar para o Menu");
		corpo2.add(sair, "Center");
		corpo2.add(sairMenu, "South");
		corpo2.setBorder(BorderFactory.createEmptyBorder(0, 50, 15, 50));
		
	}
	
	public void setVisible() {
		menu.setVisible(true);
	}


	public JFrame getMenu() {
		return menu;
	}


	public void setMenu(JFrame menu) {
		this.menu = menu;
	}


	public JButton getSair() {
		return sair;
	}


	public void setSair(JButton sair) {
		this.sair = sair;
	}


	public JButton getSairMenu() {
		return sairMenu;
	}


	public void setSairMenu(JButton sairMenu) {
		this.sairMenu = sairMenu;
	}
}
