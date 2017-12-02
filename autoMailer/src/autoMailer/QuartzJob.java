package autoMailer;

import java.util.Date;
import java.util.Properties;

//Mail Imports
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//Quartz Imports
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzJob implements Job 
	{

		
		public void execute1(JobExecutionContext context) throws JobExecutionException, AddressException, MessagingException{
			
			
			EmailActivity a = (EmailActivity) context;
			Properties properties = new Properties();
	        properties.put("mail.smtp.host", a.getHost());
	        properties.put("mail.smtp.port", a.getPort());
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	 
	        // creates a new session with an authenticator
	        Authenticator auth = new Authenticator() {
	            public PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(a.getUserName(), a.getPass());
	            }
	        };
	 
	        Session session = Session.getInstance(properties, auth);
	 
	        // creates a new e-mail message
	        Message msg = new MimeMessage(session);
	 
	        msg.setFrom(new InternetAddress(a.getUserName()));
	        InternetAddress[] toAddresses = { new InternetAddress(a.getEmailAddress()) };
	        msg.setRecipients(Message.RecipientType.TO, toAddresses);
	        msg.setSubject(a.getSubject());
	        msg.setSentDate(new Date());
	        msg.setText(a.getMessageText());
	 
	        // sends the e-mail
	        Transport.send(msg);
		}

		@Override
		public void execute(JobExecutionContext arg0) throws JobExecutionException
		{
			// TODO Auto-generated method stub
			
		}
	}


