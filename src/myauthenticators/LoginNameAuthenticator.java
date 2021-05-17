package myauthenticators;

import java.security.*;


public class LoginNameAuthenticator {
	private String name;
	private String email;
	private Signature signature;
	
	private void confirmSignature(String algorithm) {
		try {
			signature = Signature.getInstance(algorithm);
		}
		catch (Exception e) {
			System.out.println("Error in signature verification.\n");
		}
	}
	
	public boolean emailValidation(String email) {
		
		
		
		return true;
	}
}
