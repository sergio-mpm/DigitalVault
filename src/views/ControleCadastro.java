package views;

import java.awt.color.CMMException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import model.Autentificador;
import model.BD;
import model.Usuario;

public class ControleCadastro {

	
	public void callCadastro(JPanel cabecalho) {
		InterfaceCadastro ic = new InterfaceCadastro();
		ic.addCabecalho(cabecalho);
		ic.addCorpo1();
		addtotalAcesso(ic);
		addActVoltarMenu(ic);
		addActCadastro(ic);
		ic.setVisible();
	}
	
	
	public void addActVoltarMenu(InterfaceCadastro ic) {
		ic.getSair().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BD.Log(6007, Usuario.getInstance().Get_Email());
				ic.getMenu().dispose();
				ControleMenu cm = new ControleMenu();
				cm.callMenu();
				
			}
		});
		
	}
	
	public void addActCadastro(InterfaceCadastro ic) {
		ic.getConfirmarCadastro().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String certificadoPath = ic.getField1().getText();
				String grupo = String.valueOf(ic.getField2().getSelectedItem());
				String senha = ic.getField3().getText();
				String senhaConfirma = ic.getField4().getText();
				
				BD.Log(6002, Autentificador.getInstance().Get_LoginName());
				int retorno = Autentificador.getInstance().Validar_Dados_Cadastro(certificadoPath, grupo, senha, senhaConfirma);
				
				if ( retorno == 1) {
					System.out.println("Usuario Cadastrado com sucesso!");
					ic.getMenu().dispose();
					ControleMenu cm = new ControleMenu();
					cm.callMenu();
				}
				
			}
		});
	}
	
	public void addtotalAcesso(InterfaceCadastro ic) {
		// To do here
		ic.setLabeltotalUsuario(ic.getLabeltotalUsuario().getText() + BD.Total_Usuarios_Sistema());
		
	}

}
