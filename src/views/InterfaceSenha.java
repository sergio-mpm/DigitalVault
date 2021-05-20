package views;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import models.LoginNameAuthenticator;
import models.BDConnect;

public class InterfaceSenha {

	
	private JFrame menu;
	private JPanel panel;
	private ArrayList<JButton> buttons;
	private JButton send;
	private JButton reset;
	
	
	public JButton getReset() {
		return reset;
	}



	public void setReset(JButton reset) {
		this.reset = reset;
	}



	public JFrame getScreen() {
		return menu;
	}



	public void setScreen(JFrame screen) {
		this.menu = screen;
	}



	public JPanel getPanel() {
		return panel;
	}



	public void setPanel(JPanel panel) {
		this.panel = panel;
	}



	public ArrayList<JButton> getButtons() {
		return buttons;
	}

	public void setButtons(ArrayList<JButton> buttons) {
		this.buttons = buttons;
	}


	public InterfaceSenha(int numberButtons) {
		BDConnect.Log(3001, LoginNameAuthenticator.getInstance().Get_LoginName());
		
		menu = new JFrame("Autenticação de Senha");
		panel = new JPanel();
		menu.setSize(500,300);
		menu.setLocationRelativeTo(null);
		buttons = new ArrayList<JButton>(numberButtons);
		send = new JButton("Enviar Senha");
		reset = new JButton("Reset");
		addSendButton();
		createButtons(numberButtons);
		addButtonsToPanel();
		addPanel();
				
		menu.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	BDConnect.Log(1002);
                System.out.println("Sistema sendo fechado pelo usuario");
                System.exit(0);
            }
        });
	}
	


	public void createButtons(int numberButtons) { 
		for (int i = 0 ; i < numberButtons ; i++) {
			JButton button = new JButton();
			buttons.add(button);
		}
	}
	
	public void addSendButton() {
		JPanel panelCommands = new JPanel();
		JPanel passwordArea = new JPanel();
		panelCommands.add(send);
		panelCommands.add(reset);
		menu.getContentPane().add(BorderLayout.SOUTH , panelCommands);
		menu.getContentPane().add(BorderLayout.CENTER,passwordArea);
	}


	public JButton getSend() {
		return send;
	}



	public void setSend(JButton send) {
		this.send = send;
	}



	public void addButtonsToPanel() {
		for (int i = 0 ; i < buttons.size() ; i++) {			
			panel.add(buttons.get(i));
		}
	}
	
	public void reinicialize() {
		panel = new JPanel();
		buttons = new ArrayList<JButton>(6);
	}
	
	public void addPanel() {
		menu.getContentPane().add(BorderLayout.NORTH , panel);
	}
	
	public void setVisibleScreen() {
		menu.setVisible(true);
	}

	
}
