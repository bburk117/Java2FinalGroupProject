package autoMailer;
/*
 * @author Dylan Kennedy
 * Quartz Job to send emails
 */
import java.util.Date;
import java.util.Properties;

//Java mail api libs
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
	
	public void execute(JobExecutionContext context) throws JobExecutionException
	{
		
			
			//Getting params from context
			JobDataMap jdMap = context.getMergedJobDataMap();
			
		
			//Pulling params from our JobDataMap
			String recipentEmail = jdMap.getString("emailAddress");//Email Were Sending to
			String subject = jdMap.getString("subject");//Subject
			String messageText = jdMap.getString("message");//Message to be Sent
			String host = jdMap.getString("host");//Smtp Server pulled from web.xml
			String port = jdMap.getString("port");//Port pulled from web.xml
			String adminUserName = jdMap.getString("adminUserName");//Account info pulled from web.xml
			String adminPassword = jdMap.getString("adminPassword");
			
			
			//Configuring smtp server
			Properties properties = new Properties();
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.port", port);
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	        
	       //Creates a new session with an authenticator
	       Session emailSession = Session.getInstance(properties, new javax.mail.Authenticator()
	       {
	    	   protected PasswordAuthentication getPasswordAuthentication()
	    	   {
	    		   
	    		   return  new PasswordAuthentication (adminUserName,adminPassword);
	    		    
	    	   }
	       });
	        
	        
	        //Compose Message
	        try {
	        	
	        	
	        	MimeMessage msg = new MimeMessage(emailSession);
	        	msg.setFrom(new InternetAddress(adminUserName));
	        	msg.addRecipient(Message.RecipientType.TO, new InternetAddress(recipentEmail));
	        	msg.setSubject(subject);
	        	msg.setSentDate(new Date());
	        	msg.setText(messageText);
	        	Transport.send(msg);
	        	
	        	
	        }catch(MessagingException e){
	        	e.printStackTrace();
	        	
	        }
	        
	        	
	 }
	       
}

	


