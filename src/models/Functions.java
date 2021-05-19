package models;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

public class Functions {
	
	public static ArrayList<TrioFonemas> Gerar_Set_Trios()
	{
		ArrayList<TrioFonemas> trio_fonemas = new ArrayList<TrioFonemas>();
		ArrayList<String> fonemas = new ArrayList<String>();
		fonemas.add("BA");
		fonemas.add("CA");
		fonemas.add("DA");
		fonemas.add("FA");
		fonemas.add("GA");
		fonemas.add("HA");
		fonemas.add("BE");
		fonemas.add("CE");
		fonemas.add("DE");
		fonemas.add("FE");
		fonemas.add("GE");
		fonemas.add("HE");
		fonemas.add("BO");
		fonemas.add("CO");
		fonemas.add("DO");
		fonemas.add("FO");
		fonemas.add("GO");
		fonemas.add("HO");
		
		for(int i=0; i<5; i++)
		{
			Random rnd = new Random();
			
			int index = rnd.nextInt(fonemas.size());
			String fonema1 = fonemas.get(index);
			fonemas.remove(index);
			index = rnd.nextInt(fonemas.size());
			String fonema2 = fonemas.get(index);
			fonemas.remove(index);
			index = rnd.nextInt(fonemas.size());
			String fonema3 = fonemas.get(index);
			fonemas.remove(index);
			
			trio_fonemas.add(new TrioFonemas(fonema1, fonema2, fonema3));
		}
		
		return trio_fonemas;
	}
	
	public static ArrayList<ParDigitos> Gerar_Set_Pares()
	{
		ArrayList<ParDigitos> par_digitos = new ArrayList<ParDigitos>();
		ArrayList<Integer> digitos = new ArrayList<Integer>();
		digitos.add(0);
		digitos.add(1);
		digitos.add(2);
		digitos.add(3);
		digitos.add(4);
		digitos.add(5);
		digitos.add(6);
		digitos.add(7);
		digitos.add(8);
		digitos.add(9);
		
		for(int i=0; i < 5; i++)
		{
			Random rnd = new Random();
			
			int index = rnd.nextInt(digitos.size());
			int n1 = digitos.get(index);
			digitos.remove(index);
			index = rnd.nextInt(digitos.size());
			int n2 = digitos.get(index);
			digitos.remove(index);
			
			par_digitos.add(new ParDigitos(n1, n2));
		}
		
		return par_digitos;
	}
	
	public static boolean Validate_Pattern_Password(String senha) {
		char[] senha_array = senha.toCharArray();
		
		if(senha_array.length < 8 || senha_array.length > 12) 
		{
			System.out.println("Tamanho da senha invalido");
			return false;
		}
		
		int substring_last_index = 3;
		int substring_first_index = 2;
		String first_substring;
		String second_substring;
		while(substring_last_index <= senha.length()) 
		{
			first_substring = senha.substring(substring_first_index-2, substring_last_index-2);
			second_substring = senha.substring(substring_first_index, substring_last_index);
			if(first_substring.equals(second_substring))
			{
				System.out.println("Senha fora do Padrão");
				return false;
			}
			substring_last_index +=2;
			substring_first_index +=2;
		}
		
		return true;
	}
	
	public static boolean Validar_Padrao_Senha(int senha)
	{
		char[] senha_array = Integer.toString(senha).toCharArray();
		
		if(senha_array.length < 4 || senha_array.length > 6 )
		{
			System.out.println("Tamanho da senha invalido");
			return false;
		}
		
		
		int last_value = -2;
		int next_value = -2;
		int i = 0;
		for (char c : senha_array) {
			int value = Character.getNumericValue(c);
			if(last_value == value)
			{
				System.out.println("Senha fora do padrão");
				return false;
			}
			
			if(i + 1 >= senha_array.length)
				next_value = -2;
			else
				next_value = Character.getNumericValue(senha_array[i + 1]);
			if(last_value + 1 == value && value + 1 == next_value)
			{
				System.out.println("Senha fora do padrão");
				return false;
			}
			
			last_value = value;
			i++;
		}
		
		
		return true;
	}
	
	public static String Get_Random_SALT()
	{
		String alfabeto = "abcdefghijklmnopqrstuvywxzABCDEFGHIJKLMNOPQRSTUVYWXZ0123456789";
		
		Random rnd = new Random();
		
		String SALT = "";
		
		for(int i = 0; i < 10; i++)
		{
			SALT += alfabeto.charAt(rnd.nextInt(alfabeto.length()));
		}
		
		return SALT;
	}
	
	public static byte[] Random_Byte_Array(int size)
	{
		Random rnd = new Random();
		StringBuffer sb = new StringBuffer();
		byte[] random_bytes = new byte[size];
		
		rnd.nextBytes(random_bytes);
		
		return random_bytes;
	}
	
	public static String Byte_to_Hex(byte[] bytes) 
	{
		StringBuffer buf = new StringBuffer();
		
		for(int i = 0; i < bytes.length; i++) {
	       String hex = Integer.toHexString(0x0100 + (bytes[i] & 0x00FF)).substring(1);
	       buf.append((hex.length() < 2 ? "0" : "") + hex);
	    }
		
		return buf.toString();
	}
	
	public static byte[] Hex_to_Byte(String hex)
	{
		byte[] val = new byte[hex.length() / 2];
		for (int i = 0; i < val.length; i++) {
		   int index = i * 2;
		   int j = Integer.parseInt(hex.substring(index, index + 2), 16);
		   val[i] = (byte) j;
		}
		
		return val;
	}
}
