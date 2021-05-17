package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.BD;
import model.Usuario;

public class InterfaceSaida {

	private JFrame menu;
	private JPanel cabecalho , corpo1 , corpo2 ;
	private JButton sair , sairMenu;
	private JLabel saida , msgSaida;
	
	public InterfaceSaida() {
		BD.Log(9001, Usuario.getInstance().Get_Email());
		
		menu = new JFrame("Tela de saída");
		menu.setSize(600,600);
		menu.setLocationRelativeTo(null);
		corpo2 = new JPanel();
		menu.add(corpo2 , BorderLayout.SOUTH);
		corpo2.setLayout(new BoxLayout(corpo2, BoxLayout.Y_AXIS));
		setLabels();
		setButtons();
		
		menu.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	BD.Log(1002);
                System.out.println("Sistema sendo fechado pelo usuario");
                System.exit(0);
            }
        });
	}
	

	public void addCorpo1(JPanel corpo1) {
		this.corpo1 = corpo1;
		corpo1.setLayout(new BorderLayout());
	    //corpo1.setPreferredSize(new Dimension(100,100));
		menu.add(corpo1 , BorderLayout.CENTER);
	}
	
	public void addCabecalho(JPanel cabecalho) {
		this.cabecalho = cabecalho;
		cabecalho.setLayout(new BorderLayout());
		//cabecalho.setPreferredSize(new Dimension(100,100));
		menu.add(cabecalho , BorderLayout.NORTH);
	}
	
	public void setLabels() {
		saida = new JLabel("Saída do sistema: ");
		msgSaida = new JLabel("Pressione o botão Sair para confirmar.");
		corpo2.add(saida);
		corpo2.add(msgSaida);

	}
	
	public void setButtons() {
		sair = new JButton("Sair");
		sairMenu = new JButton("Voltar para o Menu");
		corpo2.add(sair);
		corpo2.add(sairMenu);
		
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
