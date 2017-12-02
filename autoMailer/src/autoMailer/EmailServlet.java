package autoMailer;
/**
 * A Servlet that implements javamail and quartz to schedule the delivery of emails.
 * @author Dylan Kennedy
 * @version 1.0 Build 5 11/24/17
 */
//Imports
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Quartz libs
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;



/**
 * 
 * Servlet implementation class EmailServlet
 */
@WebServlet("/EmailServlet")
public class EmailServlet extends HttpServlet {
	
	//Variables
	private static final long serialVersionUID = 1L;
	private String host;
	private String port;
	private String userName;
	private String pass;
	private String cronExpression;
	
    
	
    // reads SMTP server setting from web.xml file
 
	public void init() {
	       
		
	    }
	 

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Creating our email activity
		EmailActivity emailActivity = new EmailActivity();
		PrintWriter writer = response.getWriter();
		
		//Associating with html elements
		String name = request.getParameter("name");
		String emailAddress = request.getParameter("emailAddress");
		String subject = request.getParameter("subject");
		String messageText = request.getParameter("message");
		//Reads from web XML
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        userName = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
        cronExpression = context.getInitParameter(cronExpression);
		
		//Creating Object
		emailActivity.setEmailAddress(emailAddress);
		emailActivity.setName(name);
		emailActivity.setSubject(subject);
		emailActivity.setMessageText(messageText);
		emailActivity.setHost(host);
		emailActivity.setUserName(userName);
		emailActivity.setPass(pass);
		
		
		
		//Define a job and tie to QuartzJob class mainly how and when we send our email
		JobBuilder jobBuilder = JobBuilder.newJob(QuartzJob.class);
		JobDetail job = jobBuilder.withIdentity("myJob","group1").build();		
		
		
		  
		
		 /* Creating a trigger for our job a Which is a date and time that we get from our email servlet.
		 * You can find more information on cron expressions from <ttps://docs.oracle.com/cd/E12058_01/doc/doc.1014/e12030/cron_expressions.htm>. 
		 */Trigger t1 = (Trigger) TriggerBuilder.newTrigger().withIdentity("CronTrigger","group1").withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
		
		//Creating our scheduler
		Scheduler sc1 = null;
		try
		{
			sc1 = StdSchedulerFactory.getDefaultScheduler();
		} catch (SchedulerException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			sc1.getContext().put("pass", emailActivity);
		} catch (SchedulerException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Start Scheduler
		try
		{
			sc1.start();
		} catch (SchedulerException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
	
	}
	


}
