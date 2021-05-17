package views;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import models.Arquivo;
import models.BDConnect;
import models.Usuario;

public class ControleConsulta {
	
	public void callConsulta(JPanel cabecalho) {
		InterfaceConsulta ic = new InterfaceConsulta();
		ic.addCabecalho(cabecalho);
		ic.addCorpo1();
		addTotalConsulta(ic);
		ic.setVisible();
		addActLista(ic);
		addActSair(ic);		
	}
	
	
	public void addActLista(InterfaceConsulta ic) {
		ic.getListar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BDConnect.Log(8003, Usuario.getInstance().Get_Email());
				ArrayList<Arquivo> arquivos = Usuario.getInstance().Parse_Index(ic.getCampoPasta().getText());
				if(arquivos != null)
				{
					BDConnect.Log(8009, Usuario.getInstance().Get_Email());
					ic.addArchivestoTable(arquivos);
					addActClick(ic.getTable(), arquivos,ic);
					ic.getMenuList().setVisible(true);
				}
				else
				{
					
				}
			}
		});
	}
	
	public void addActSair(InterfaceConsulta ic) {
		ic.getSair().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BDConnect.Log(8002, Usuario.getInstance().Get_Email());
				
				ic.getMenu().dispose();
				if (ic.getMenuList() != null )
					ic.getMenuList().dispose();
				ControleMenu cm = new ControleMenu();
				cm.callMenu();
				
			}
		});
	}
	
	public void addActClick(JTable table , ArrayList<Arquivo> arquivos, InterfaceConsulta ic) {	
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) 
			{
				int position = table.getSelectedRow();
				Arquivo a = arquivos.get(position);
				BDConnect.Log(8010, Usuario.getInstance().Get_Email(), a.Get_NomeCodigo());
				Usuario.getInstance().Decriptar_Arquivo(a);
			}
				
		});
	}
	
	public void addTotalConsulta(InterfaceConsulta ic) {
		// to do here
		ic.setTotalConsultas(Integer.toString(Usuario.getInstance().Get_Total_Consultas()));	
	}
	
}
