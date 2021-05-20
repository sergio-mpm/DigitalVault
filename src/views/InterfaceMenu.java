package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.LoginNameAuthenticator;
import models.BDConnect;

public class InterfaceMenu {

	private JFrame menu;
	private JPanel cabecalho;
	private JPanel corpo1;
	private JPanel corpo2;
	private JLabel login;
	
	public JLabel getLogin() {
		return login;
	}

	public void setLogin(JLabel login) {
		this.login = login;
	}

	public JLabel getGrupo() {
		return grupo;
	}

	public void setGrupo(JLabel grupo) {
		this.grupo = grupo;
	}

	public JLabel getNome() {
		return nome;
	}

	public void setNome(JLabel nome) {
		this.nome = nome;
	}

	public JLabel getTotalAcessos() {
		return totalAcessos;
	}

	public void setTotalAcessos(JLabel totalAcessos) {
		this.totalAcessos = totalAcessos;
	}

	private JLabel grupo;
	private JLabel nome;
	private JLabel totalAcessos;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	
	public JPanel getCabecalho() {
		return cabecalho;
	}

	public void setCabecalho(JPanel cabecalho) {
		this.cabecalho = cabecalho;
	}

	public JPanel getCorpo1() {
		return corpo1;
	}

	public JFrame getMenu() {
		return menu;
	}

	public void setMenu(JFrame menu) {
		this.menu = menu;
	}

	public void setCorpo1(JPanel corpo1) {
		this.corpo1 = corpo1;
	}

	
	public InterfaceMenu(String grupo) {
		BDConnect.Log(5001, LoginNameAuthenticator.getInstance().Get_LoginName());		
		menu = new JFrame("Menu");
		menu.setLayout(new BorderLayout());
		menu.setSize(450,390);
		menu.setLocationRelativeTo(null);
		setConfigurationsOfPanels();
		fillCabecalho();
		fillAcessos(); 
		if ( grupo.equals("administrador")) {
			fillButtonsAdm();
		} else {
			fillButtonsUser();
		}
		
		corpo2.setBorder(BorderFactory.createEmptyBorder(0, 50, 5, 0));
		
		menu.add(cabecalho , "North");
		menu.add(corpo1 , "Center");
		menu.add(corpo2 , "South");
		
		menu.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	BDConnect.Log(1002);
                System.out.println("Sistema sendo fechado pelo usuario"); 
                System.exit(0);
            }
        });
	}
	
	public void setConfigurationsOfPanels() {
		cabecalho = new JPanel();  
		cabecalho.setLayout(new BorderLayout());
		cabecalho.setPreferredSize(new Dimension(100,100));
	    corpo1 = new JPanel();
	    corpo1.setLayout(new BorderLayout());
	    corpo1.setPreferredSize(new Dimension(200,200));
	    corpo2 = new JPanel();
	    corpo2.setLayout(new BoxLayout(corpo2, BoxLayout.Y_AXIS)); 
	    corpo2.setPreferredSize(new Dimension(400,200));
	}
	
	
	
	public void fillCabecalho() {
		login = new JLabel("Login : ");
		grupo = new JLabel("Grupo : ");
		nome  = new JLabel("Nome : ");
		login.setFont(new Font("Dialog", Font.BOLD, 15));
		grupo.setFont(new Font("Dialog", Font.BOLD, 15));
		nome.setFont(new Font("Dialog", Font.BOLD, 15));
		cabecalho.add(login , "North");
		cabecalho.add(grupo , "Center");
		cabecalho.add(nome , "South");
		cabecalho.setBorder(BorderFactory.createEmptyBorder(20, 50, 0, 20));
	} 
	
	public void fillAcessos() {
		totalAcessos = new JLabel("Total de acessos do usuário: ");
		totalAcessos.setFont(new Font("Dialog", Font.BOLD, 15));
		corpo1.add(totalAcessos, "Center");
		corpo1.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 20));
	}
	
	public void fillButtonsAdm() {
		button1 = new JButton("Cadastrar um novo usuário");
		button2 = new JButton("Alterar senha pessoal e certificado digital do usuário");
		button3 = new JButton("Consultar pasta de arquivos secretos do usuário");
		button4 = new JButton("Sair do Sistema");
		corpo2.add(button1);
		corpo2.add(button2);
		corpo2.add(button3);
		corpo2.add(button4);
	}
	
	public void fillButtonsUser() {
		button2 = new JButton("Alterar senha pessoal e certificado digital do usuário");
		button3 = new JButton("Consultar pasta de arquivos secretos do usuário");
		button4 = new JButton("Sair do Sistema");
		corpo2.add(button2);
		corpo2.add(button3);
		corpo2.add(button4);
	}
	
	public JButton getButton1() {
		return button1;
	}

	public void setButton1(JButton button1) {
		this.button1 = button1;
	}

	public JButton getButton2() {
		return button2;
	}

	public void setButton2(JButton button2) {
		this.button2 = button2;
	}

	public JButton getButton3() {
		return button3;
	}

	public void setButton3(JButton button3) {
		this.button3 = button3;
	}

	public JButton getButton4() {
		return button4;
	}

	public void setButton4(JButton button4) {
		this.button4 = button4;
	}

	public void addCabecalho() {
		menu.getContentPane().add(BorderLayout.NORTH , cabecalho);
	}
	
	public void setVisible() {
		menu.setVisible(true);
	}
	
}
