package models;

public class LogPrinter {
	public static void main(String[] args) {
		BDConnect.Estabelecer_Conexao();
		
		BDConnect.Print_Logs2();
	}
}
