package application;

import java.util.Properties;


public class Email {
	
	public static void main(String[] args) {
		String usuario = "topicos20211";
		String senha = "123topicos";

	Properties prop = new Properties();
	prop.put("mail.smtp.host", "smtp.gmail.com");
	prop.put("mail.smtp.port", "587");
	prop.put("mail.smtp.auth", "true");
	prop.put("mail.smtp.starttls.enable", "true");
	
//	Session session = Session.getInstance(prop, new jakarta.mail.Authenticator() {
//		
//	});
		
	}

	
}
