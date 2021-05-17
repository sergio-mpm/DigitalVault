package models;

public class ParDigitos {
	public int n1;
	public int n2;
	
	public ParDigitos(int n1, int n2)
	{
		this.n1 = n1;
		this.n2 = n2;
	}
	
	public boolean Match(int a)
	{
		if(n1 == a || n2 == a)
			return true;
		else
			return false;
	}
}
