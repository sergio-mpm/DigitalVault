package models;

public class TrioFonemas {
	public String fonema1;
	public String fonema2;
	public String fonema3;
	
	public TrioFonemas(String fonema1, String fonema2, String fonema3) {
		this.fonema1 = fonema1;
		this.fonema2 = fonema2;
		this.fonema3 = fonema3;
	}
	
	public boolean Match(String f) {
		if(fonema1 == f || fonema2 == f || fonema3 == f)
			return true;
		else
			return false;
	}

}
