package br.com.sistema.external;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sistema.usuario.Usuario;

public class Email {
	
	public static void enviarEmail(Usuario professor) {
		        Properties props = new Properties();
	            /** Parâmetros de conexão com servidor Gmail */
	            props.put("mail.smtp.host", "smtp.gmail.com");
	            props.put("mail.smtp.socketFactory.port", "465");
	            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	            props.put("mail.smtp.auth", "true");
	            props.put("mail.smtp.port", "465");

	            Session session = Session.getDefaultInstance(props,
	                        new javax.mail.Authenticator() {
	                             protected PasswordAuthentication getPasswordAuthentication()
	                             {
	                                   return new PasswordAuthentication("erick.bogarin@gmail.com", "45685963");
	                             }
	                        });

	            /** Ativa Debug para sessão */
	            session.setDebug(true);

	            try {

	                  Message message = new MimeMessage(session);
	                  message.setFrom(new InternetAddress("erick.bogarin@gmail.com")); //Remetente

	                  Address[] toUser = InternetAddress //Destinatário(s)
	                             .parse(professor.getEmail());  

	                  message.setRecipients(Message.RecipientType.TO, toUser);
	                  message.setSubject("Sistema de TCCs");//Assunto
	                  message.setText("Bem vindo ao sistema de gerenciamento e controle de TCCs \n"
	                  		+ "Sua senha de acesso é: " + professor.getSenha());
	                  /**Método para enviar a mensagem criada*/
	                  Transport.send(message);
	                  
	                  System.out.println("Feito!!!");
	      
	      			
	             } catch (MessagingException e) {
	                  throw new RuntimeException(e);
	            }
	      }
	

	
}
