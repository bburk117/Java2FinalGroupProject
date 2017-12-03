package autoMailer;


import java.util.Date;
import java.util.Properties;

//Java mail api libs
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//Quartz libs
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class AutoMailer implements Job 
{
	public AutoMailer() {
		
	}
	public void execute(JobExecutionContext context) throws JobExecutionException
	{
		System.out.println("Hello World");
			//Getting params from context
			JobDataMap jdMap = context.getJobDetail().getJobDataMap();
			
		
			//Pulling params from our JobDataMap
			String recipentEmail = jdMap.getString("emailAddress");//Email Were Sending to
			String subject = jdMap.getString("subject");//Subject
			String messageText = jdMap.getString("message");//Message to be Sent
			String host = jdMap.getString("host");//Smtp Server pulled from web.xml
			String port = jdMap.getString("port");//Port pulled from web.xml
			String from= jdMap.getString("adminUserName");//Account info pulled from web.xml
			String adminPassword = jdMap.getString("adminPassword");

			
			//Configuring smtp server
			Properties properties = new Properties();
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.port", port);
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	 
	        //Creates a new session with an authenticator
	        Authenticator auth = new Authenticator() 
	        {
	            public PasswordAuthentication getPasswordAuthentication() 
	            {
	               System.out.println("in PAss word authenticator");
	            	return new PasswordAuthentication(from, adminPassword);
	               
	            }
	        };
	        
	        Session emailSession = Session.getInstance(properties,auth);
	        //Compose Message
	        try {
	        	MimeMessage msg = new MimeMessage(emailSession);
	        	msg.setFrom(new InternetAddress(from));
	        	msg.addRecipient(Message.RecipientType.TO, new InternetAddress(recipentEmail));
	        	msg.setSubject(subject);
	        	msg.setSentDate(new Date());
	        	msg.setText(messageText);
	        	Transport.send(msg);
	        	System.out.println("Sucess!");
	        	
	        }catch(MessagingException e){
	        	e.printStackTrace();
	        	
	        }
	       
	        
	        
	    
	     
		}


}
	


