package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import models.Arquivo;
import models.BDConnect;
import models.Usuario;

public class InterfaceConsulta {

	private JFrame menu;
	private JFrame menuList;
	private JPanel cabecalho , corpo1 , corpo2;
	private JTextField campoPasta;
	private JButton listar , sair;
	private JTable table;
	private JLabel totalConsultas;
	private JScrollPane sPane ;
	
	
	public InterfaceConsulta() {
		BDConnect.Log(8001, Usuario.getInstance().Get_Email());
		
		menu = new JFrame();
		menu.setLayout(new BorderLayout());
		menu.setSize(450,300);
		menu.setLocationRelativeTo(null);
		setConfigurationsOfPanels();
		menu.add(corpo2 , BorderLayout.SOUTH);
		corpo2.setLayout(new BoxLayout(corpo2, BoxLayout.Y_AXIS));
		addText();
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
	
	public void setConfigurationsOfPanels() {
		cabecalho = new JPanel();  
		cabecalho.setLayout(new BorderLayout());
		cabecalho.setPreferredSize(new Dimension(100,100));
	    corpo1 = new JPanel();
	    corpo1.setLayout(new BorderLayout());
	    corpo1.setPreferredSize(new Dimension(200,80));
	    corpo2 = new JPanel();
	    corpo2.setLayout(new BorderLayout());
	    corpo2.setPreferredSize(new Dimension(200,80));
	    
	}
	
	public void addTable() {
		corpo2.add(table);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JTextField getCampoPasta() {
		return campoPasta;
	}

	public void setCampoPasta(JTextField campoPasta) {
		this.campoPasta = campoPasta;
	}

	public void addCabecalho(JPanel cabecalho) {
		this.cabecalho = cabecalho;
		menu.add(cabecalho, BorderLayout.NORTH);
	}
	 
	public void addCorpo1() {
		corpo1 = new JPanel();
		corpo1.setLayout(new BorderLayout());
		totalConsultas = new JLabel("Total de consultas do usuario:"); 
		totalConsultas.setFont(new Font("Dialog", Font.BOLD, 15));
		corpo1.add(totalConsultas , "Center");
		corpo1.setBorder(BorderFactory.createEmptyBorder(0, 50, 5, 0));
		menu.add(corpo1 , "Center");
	}
	
	public JLabel getTotalConsultas() {
		return totalConsultas;
	}

	public void setTotalConsultas(String text) {
		this.totalConsultas.setText(this.totalConsultas.getText() + text);
	}

	public void addText() {
		JPanel subCorpo = new JPanel (new BorderLayout());
		listar = new JButton("Listar");
		subCorpo.add(listar, "East");
		
		campoPasta = new JTextField(100);
		subCorpo.add(campoPasta , "Center");
		
		corpo2.add(subCorpo, "Center");
	}
	

	public void setButtons() {
		sair = new JButton("Voltar para o Menu");
		
		corpo2.add(sair, "South");
		corpo2.setBorder(BorderFactory.createEmptyBorder(5, 50, 20, 50));
		
	}
	
	public JFrame getMenu() {
		return menu;
	}
	public void setMenu(JFrame menu) {
		this.menu = menu;
	}
	public JButton getListar() {
		return listar;
	}
	public void setListar(JButton listar) {
		this.listar = listar;
	}
	public JButton getSair() {
		return sair;
	}
	public void setSair(JButton sair) {
		this.sair = sair;
	}
	public void setVisible() {
		menu.setVisible(true);
	}
	
	public void addArchivestoTable(ArrayList<Arquivo> arquivos) {	
		menuList = new JFrame();
		menuList.setLayout(new BorderLayout());
		menuList.setSize(600,600);
		menuList.setLocationRelativeTo(null);
		JPanel corpoMenu = new JPanel();
		menuList.add( corpoMenu  , BorderLayout.SOUTH);
		corpoMenu .setLayout(new BorderLayout());
		menuList.add( corpoMenu );
		sPane = new JScrollPane();
		DefaultTableModel model = new DefaultTableModel(); 
		table = new JTable(model); 
		String[] listRow = new String[4];
		model.addColumn("Nome_Codigo"); 
		model.addColumn("Nome_Secreto");
		model.addColumn("Dono"); 
		model.addColumn("Grupo"); 

		for (Arquivo arquivo : arquivos) {			
			listRow[0] = arquivo.GetNomeCodigo();
			listRow[1] = arquivo.GetNomeSecreto();
			listRow[2] = arquivo.GetDono();
			listRow[3] = arquivo.GetGrupo();
			model.addRow(listRow);
			listRow = new String[4];
		}
		
		sPane.getViewport().add(table);
		corpoMenu.add(sPane);
	}

	public JFrame getMenuList() {
		return menuList;
	}

	public void setMenuList(JFrame menuList) {
		this.menuList = menuList;
	}
}
