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
	private JButton buttonCadastrar;
	private JButton buttonAlterar;
	private JButton buttonConsultar;
	private JButton buttonSair;
	
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
		menu.setLayout( new BorderLayout() );
		menu.setSize(400,400);
		menu.setLocationRelativeTo(null);
		
		fillCabecalho();
		fillCorpo1();
		fillCorpo2(grupo);   
		
		menu.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	BDConnect.Log(1002);
                System.out.println("Sistema sendo fechado pelo usuario");
                System.exit(0);
            }
        });
	}
	
	
	
	private void fillCabecalho() {
		cabecalho = new JPanel( new BorderLayout());	
		
		login = new JLabel("Login : ");
		login.setFont(new Font("Dialog", Font.BOLD, 15));
		
		grupo = new JLabel("Grupo : ");
		grupo.setFont(new Font("Dialog", Font.BOLD, 15));
		
		nome  = new JLabel("Nome : ");
		nome.setFont(new Font("Dialog", Font.BOLD, 15));

		cabecalho.add(login, "North");
		cabecalho.add(grupo, "Center");
		cabecalho.add(nome, "South");
		cabecalho.setBorder(BorderFactory.createEmptyBorder(20, 50, 0, 20));
		
		menu.getContentPane().add(cabecalho, "North"); 
	}
	
	private void fillCorpo1() {
	    corpo1 = new JPanel( new BorderLayout() );
		totalAcessos = new JLabel("Total de acessos do usuário: ");
		totalAcessos.setFont(new Font("Dialog", Font.BOLD, 15));
		corpo1.add(totalAcessos, "Center");
		corpo1.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 20));
		menu.getContentPane().add(corpo1, "Center");  
	}
	
	private void fillCorpo2(String grupo) {
		corpo2 = new JPanel( new BorderLayout() );
		JPanel subCorpo = new JPanel (new BorderLayout() );
		
		if ( grupo.equals("administrador")) {
			
			buttonCadastrar = new JButton("Cadastrar um novo usuário");
			subCorpo.add(buttonCadastrar, "West");
		}
		
		buttonAlterar = new JButton("Alterar senha pessoal e certificado digital do usuário");		
		buttonConsultar = new JButton("Consultar pasta de arquivos secretos do usuário");	
		buttonSair = new JButton("Sair do Sistema");		
		
		corpo2.add(buttonAlterar, "North");
		corpo2.add(buttonConsultar, "Center");
		
		subCorpo.add(buttonSair, "Center");	
		
		corpo2.add(subCorpo, "South");
		
		corpo2.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

		menu.getContentPane().add(corpo2, "South"); 
	}
	
	public JButton getButton1() {
		return buttonCadastrar;
	}

	public void setButton1(JButton button1) {
		this.buttonCadastrar = button1;
	}

	public JButton getButton2() {
		return buttonAlterar;
	}

	public void setButton2(JButton button2) {
		this.buttonAlterar = button2;
	}

	public JButton getButton3() {
		return buttonConsultar;
	}

	public void setButton3(JButton button3) {
		this.buttonConsultar = button3;
	}

	public JButton getButton4() {
		return buttonSair;
	}

	public void setButton4(JButton button4) {
		this.buttonSair = button4;
	}

	public void addCabecalho() {
		menu.getContentPane().add(BorderLayout.NORTH , cabecalho);
	}
	
	public void setVisible() {
		menu.setVisible(true);
	}
	
}
