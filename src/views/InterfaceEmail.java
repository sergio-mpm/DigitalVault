package views;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

import models.BDConnect;

public class InterfaceEmail{

	private JFrame area;
	private JTextField tf;
	private JLabel textLabel;
	private JButton send;
	private JButton reset;	
	
	public InterfaceEmail(){
		
		BDConnect.Log(2001);
		area = new JFrame("Autenticação de Email");
		area.setSize(500,130);
		area.setLayout(null);
		area.setLocationRelativeTo(null);
		createArea();
		
		area.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	BDConnect.Log(1002);
                System.out.println("Sistema fechado pelo usuario");
                System.exit(0);
            }
        });
	}
	
	private void createArea() {
		
		textLabel = new JLabel("Email");
		textLabel.setBounds(30, 30, 100, 25);
		
		tf = new JTextField(60);	
		tf.setBounds(70, 30, 220, 25);
		
		send = new JButton("Send");	
		send.setBounds(300, 30, 80, 25);
		
		reset = new JButton("Reset");
		reset.setBounds(390, 30, 80, 25);
		
		area.add(textLabel);
		area.add(tf);
		area.add(send);
		area.add(reset);
	}
	
	public void setVisible() {
		area.setVisible(true);
	}	

	public JFrame getArea() {
		return area;
	}
	
	public JTextField getTf() {
		return tf;
	}

	public JButton getSend() {
		return send;
	}

	public JButton getReset() {
		return reset;
	}
}

