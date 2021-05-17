package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

import models.LoginNameAuthenticator;
import models.BDConnect;
import models.Usuario;

public class ControleCadastro {
	
	public void cadastrar(JPanel cabecalho) {
		InterfaceCadastro ic = new InterfaceCadastro();
		ic.addCabecalho(cabecalho);
		ic.addCorpo1();
		acessoTotal(ic);
		voltarMenu(ic);
		aceitarCadastro(ic);
		ic.setVisible();
	}
		
	public void voltarMenu(InterfaceCadastro ic) {
		ic.getSair().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BDConnect.Log(6007, Usuario.getInstance().Get_Email());
				ic.getMenu().dispose();
				ControleMenu cm = new ControleMenu();
				cm.callMenu();
				
			}
		});
		
	}
	
	public void aceitarCadastro(InterfaceCadastro ic) {
		ic.getConfirmarCadastro().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String certificadoPath = ic.getField1().getText();
				String grupo = String.valueOf(ic.getField2().getSelectedItem());
				String senha = ic.getField3().getText();
				String senhaConfirma = ic.getField4().getText();
				
				BDConnect.Log(6002, LoginNameAuthenticator.getInstance().Get_LoginName());
				int retorno = LoginNameAuthenticator.getInstance().Validar_Dados_Cadastro(certificadoPath, grupo, senha, senhaConfirma);
				
				if ( retorno == 1) {
					System.out.println("Usuario Cadastrado com sucesso!");
					ic.getMenu().dispose();
					ControleMenu cm = new ControleMenu();
					cm.callMenu();
				}
				
			}
		});
	}
	
	public void acessoTotal(InterfaceCadastro ic) {
		// To do here
		ic.setLabeltotalUsuario(ic.getLabeltotalUsuario().getText() + BDConnect.Total_Usuarios_Sistema());
		
	}

}
