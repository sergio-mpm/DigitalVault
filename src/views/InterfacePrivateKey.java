package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import javax.swing.undo.UndoableEdit;

import models.LoginNameAuthenticator;
import models.BDConnect;

public class InterfacePrivateKey {


	JFrame menu;
	JPanel panel;
	JButton btAbrir;
	JLabel labelSenha;
	JTextField senhaSecreta;
	JPanel fieldSenha;
	JButton send;
	
	
	public JTextField getSenhaSecreta() {
		return senhaSecreta;
	}


	public void setSenhaSecreta(JTextField senhaSecreta) {
		this.senhaSecreta = senhaSecreta;
	}


	public InterfacePrivateKey() {
		BDConnect.Log(4001, LoginNameAuthenticator.getInstance().Get_LoginName());
	
		menu = new JFrame("Autenticação de Chave Privada");
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		menu.setSize(400,400);
		menu.setLocationRelativeTo(null);
		addPanel();
		setAbrirConfig();
		addLabel();
		addText();
		addSendButtom();
		
		menu.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	BDConnect.Log(1002);
                System.out.println("Sistema sendo fechado pelo usuario");
                System.exit(0);
            }
        });
	}
	
	
	public JFrame getScreen() {

		return menu;
	}


	public void setScreen(JFrame screen) {

		this.menu = screen;
	}


	public JButton getSend() {
		return send;
	}


	public void setSend(JButton send) {
		this.send = send;
	}


	public void setAbrirConfig() {
		btAbrir = new JButton();
		btAbrir.setBounds(new Rectangle(60, 49, 166, 69));
		btAbrir.setFont(new Font("Dialog", Font.BOLD, 24));
		btAbrir.setText("Abrir arquivo");
		addButtom();
	}
	
	public void addLabel() {
		labelSenha = new JLabel("Senha da Chave Privada");	
		labelSenha.setFont(new Font("Dialog", Font.BOLD, 15));
	}
	
	public void addText() {
		fieldSenha = new JPanel();
		fieldSenha.setLayout(new BoxLayout(fieldSenha, BoxLayout.Y_AXIS));
		senhaSecreta = new JTextField(255);
		senhaSecreta.setDocument(new TamanhoFixoJText(255));
		fieldSenha.add(labelSenha);
		fieldSenha.add(senhaSecreta);
		panel.add(fieldSenha , BorderLayout.CENTER);
	}
	
	public void addButtom() {
		panel.add(btAbrir , BorderLayout.NORTH);
	}
	public void addSendButtom() {
		send = new JButton("Confirmar");
		send.setFont(new Font("Dialog", Font.BOLD, 15));
		send.setPreferredSize(new Dimension(100,100));
		panel.add(send , BorderLayout.SOUTH);
	}
	
	public void addPanel() {

		menu.getContentPane().add(panel);
	}
	
	public void setVisible() {

		menu.setVisible(true);
	}


	public JButton getBtAbrir() {
		return btAbrir;
	}


	public void setBtAbrir(JButton btAbrir) {
		this.btAbrir = btAbrir;
	}
	
	
}
