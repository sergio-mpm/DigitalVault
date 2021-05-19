package views;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import models.LoginNameAuthenticator;
import models.BDConnect;

public class InterfacePrivateKey {

	private JFrame menu;
	private JButton buttonAbrir;
	private JLabel labelSenha;
	private JPasswordField textFieldSenha;
	private JButton buttonConfirmar;	
	
	public JTextField getSenhaSecreta() {
		return textFieldSenha;
	}

	public void setSenhaSecreta(JPasswordField senhaSecreta) {
		this.textFieldSenha = senhaSecreta;
	}

	public InterfacePrivateKey() {
		BDConnect.Log(4001, LoginNameAuthenticator.getInstance().Get_LoginName());
	
		createMenu();
		
		menu.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	BDConnect.Log(1002);
                System.out.println("Sistema sendo fechado pelo usuario");
                System.exit(0);
            }
        });
	}
	
	private void createMenu() {
		
		menu = new JFrame("Autenticação de Chave Privada");
		menu.setSize(400,200);
		menu.setLayout(null);
		menu.setLocationRelativeTo(null);

		buttonAbrir = new JButton();
		buttonAbrir.setBounds(20, 20, 350, 30);
		buttonAbrir.setFont(new Font("Dialog", Font.BOLD, 18));
		buttonAbrir.setText("Abrir arquivo");
		
		labelSenha = new JLabel("Senha da Chave Privada");	
		labelSenha.setFont(new Font("Dialog", Font.BOLD, 15));
		labelSenha.setBounds(20, 65, 200, 30); 
		
		textFieldSenha = new JPasswordField(2500);
		textFieldSenha.setBounds(200, 65, 170, 30);
		
		buttonConfirmar = new JButton("Confirmar");
		buttonConfirmar.setFont(new Font("Dialog", Font.BOLD, 18));
		buttonConfirmar.setBounds(20, 105, 350, 30);		
		
		menu.add(buttonAbrir);
		menu.add(labelSenha);
		menu.add(textFieldSenha);
		menu.add(buttonConfirmar);		
	}
	
	public JFrame getScreen() {
		return menu;
	}

	public JButton getSend() {
		return buttonConfirmar;
	}
	
	public void setVisible() {
		menu.setVisible(true);
	}

	public JButton getBtAbrir() {
		return buttonAbrir;
	}	
}
