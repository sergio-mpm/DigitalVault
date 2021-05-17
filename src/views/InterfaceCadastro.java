package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import models.BDConnect;
import models.Usuario;

public class InterfaceCadastro {

	JPanel cabecalho;
	JPanel corpo1;
	JPanel corpo2;
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	JLabel labeltotalUsuarios;
	JTextField field1;
	JComboBox<String> field2;
	JTextField field3;
	JTextField field4;
	JPanel panelbuttons;
	JButton confirmarCadastro;
	JButton sair;
	JFrame menu;
	
	public InterfaceCadastro() {
		BDConnect.Log(6001, Usuario.getInstance().Get_Email());
		
		menu = new JFrame("Menu Cadastro");
		menu.setSize(600,600);
		menu.setLocationRelativeTo(null);
		corpo2 = new JPanel();
		menu.add(corpo2 , BorderLayout.SOUTH);
		corpo2.setLayout(new BoxLayout(corpo2, BoxLayout.Y_AXIS));
		panelbuttons = new JPanel();
	    //corpo2.setPreferredSize(new Dimension(250,250));
	    setTexts();
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
	
	
	public void addCabecalho(JPanel cabecalho) {
		this.cabecalho = cabecalho;
		cabecalho.setLayout(new BorderLayout());
		//cabecalho.setPreferredSize(new Dimension(100,100));
		menu.add(cabecalho , BorderLayout.NORTH);
	}
	
	public JTextField getField3() {
		return field3;
	}


	public void setField3(JTextField field3) {
		this.field3 = field3;
	}


	public JTextField getField4() {
		return field4;
	}


	public void setField4(JTextField field4) {
		this.field4 = field4;
	}


	public void addCorpo1() {
		corpo1 = new JPanel();
		corpo1.setLayout(new BorderLayout());
	    corpo1.setPreferredSize(new Dimension(200,200));
		labeltotalUsuarios = new JLabel("Total de usuários no sistema:");
		labeltotalUsuarios.setFont(new Font("Dialog", Font.BOLD, 15));
		corpo1.add(labeltotalUsuarios , BorderLayout.CENTER);
		menu.add(corpo1 , BorderLayout.CENTER);
	}
	
	public JLabel getLabeltotalUsuario() {
		return labeltotalUsuarios;
	}


	public void setLabeltotalUsuario(String texto) {
		this.labeltotalUsuarios.setText(texto);
	}


	public void setVisible() {
		menu.setVisible(true);
	}
	
	public void setTexts() {
		String[] messages = {"Administrador" , "Usuário"};
		label1 = new JLabel("Certificate Path");
		label2 = new JLabel("Group");
		label3  =new JLabel("Password");
		label4 = new JLabel("Repeat Password");
		field1 = new JTextField(255);
		field2 = new JComboBox<String>(messages);
		field3 = new JPasswordField (8);
		field4 = new JPasswordField(8);
		corpo2.add(label1);
		corpo2.add(field1);
		corpo2.add(label2);
		corpo2.add(field2);
		corpo2.add(label3);
		corpo2.add(field3);
		corpo2.add(label4);
		corpo2.add(field4);
		corpo2.add(panelbuttons);
		
	}
	
	public void setButtons() {
		confirmarCadastro = new JButton("Cadastrar");
		sair = new JButton("Voltar para o Menu Inicial");
		panelbuttons.add(confirmarCadastro);
		panelbuttons.add(sair);
	}


	public JButton getConfirmarCadastro() {
		return confirmarCadastro;
	}


	public JTextField getField1() {
		return field1;
	}


	public void setField1(JTextField field1) {
		this.field1 = field1;
	}


	public JComboBox<String> getField2() {
		return field2;
	}


	public void setField2(JComboBox<String> field2) {
		this.field2 = field2;
	}


	public void setConfirmarCadastro(JButton confirmarCadastro) {
		this.confirmarCadastro = confirmarCadastro;
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
	
}
