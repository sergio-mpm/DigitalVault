package views;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;

import model.Autentificador;
import model.BD;

public class ControlePrivateKey {
	
	private String pathPrivateKeySelected = null;

	public void callControllerCertificado() {
		InterfacePrivateKey ic = new InterfacePrivateKey();
		addActs(ic);
		ic.setVisible();
		
	}

	
	public void addActs(InterfacePrivateKey ic) {
		addActbtAbrir(ic);
		addActSend(ic);
	}
	
	public void addActbtAbrir(InterfacePrivateKey ic) {
		ic.getBtAbrir().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pathPrivateKeySelected = abrir();
				
			}
		});
	}
	
	private String abrir() {
		String path = null;
		try {			
			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			int returnValue = jfc.showOpenDialog(null);
			File selectedFile = null;

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				selectedFile = jfc.getSelectedFile();
				path = selectedFile.getAbsolutePath();
				System.out.println(selectedFile.getAbsolutePath());				
			}


		} catch (Exception e1) {			
			e1.printStackTrace();
		}
		return path;
		
	}
	
	public void addActSend(InterfacePrivateKey ic) {
		ic.getSend().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Validar dados
				int retorno = Autentificador.getInstance().Validar_ChavePrivada(pathPrivateKeySelected, ic.getSenhaSecreta().getText());
				
				if (retorno == -2) {
					BD.Log(4002, Autentificador.getInstance().Get_LoginName());
					ic.getScreen().dispose();
					ControllerEmail ce = new ControllerEmail();
					ce.callInterfaceEmail();
				}			
				// Verificar se eh adm ou usuario 
				if (retorno == 1) {
					Autentificador.getInstance().Efetuar_Login();				
					BD.Log(4002, Autentificador.getInstance().Get_LoginName());
					ic.getScreen().dispose();
					ControleMenu cm = new ControleMenu();
					cm.callMenu();
				}

				
			}
		});
		
		
	}
}
