package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import models.LoginNameAuthenticator;
import models.BDConnect;

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
				int retorno = LoginNameAuthenticator.getInstance().Validar_ChavePrivada(pathPrivateKeySelected, ic.getSenhaSecreta().getText());
				
				if (retorno == -2) {
					BDConnect.Log(4002, LoginNameAuthenticator.getInstance().Get_LoginName());
					ic.getScreen().dispose();
					ControleEmail ce = new ControleEmail();
					ce.callInterfaceEmail();
				}			
				if (retorno == 1) {
					LoginNameAuthenticator.getInstance().Efetuar_Login();				
					BDConnect.Log(4002, LoginNameAuthenticator.getInstance().Get_LoginName());
					ic.getScreen().dispose();
					ControleMenu cm = new ControleMenu();
					cm.callMenu();
				}

				
			}
		});
		
		
	}
}
