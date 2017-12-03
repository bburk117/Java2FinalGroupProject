package autoMailer;
/**
 * A Servlet that implements javamail and quartz to schedule the delivery of emails.
 * @author Dylan Kennedy
 * @version 1.0 Build 5 11/24/17
 */
//Imports
import java.io.IOException;


//Servlet libs
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
import org.quartz.SchedulerFactory;
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
	private String adminUserName;
	private String adminPass;
	private String cronExpression;
	
	
	public void init() {
		System.out.println("Reading XML");
        // reads SMTP server setting from web.xml file
        ServletContext sContext = getServletContext();
        host = sContext.getInitParameter("host");
        port = sContext.getInitParameter("port");
        adminUserName = sContext.getInitParameter("user");
        adminPass = sContext.getInitParameter("pass");
        
        //Handle this through a date selector in EmailForm
        cronExpression= sContext.getInitParameter("cronExpression");
    }
 
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	System.out.println("Were in DoPost");
    	//Associating with html elements
		String name = request.getParameter("name");
		String emailAddress = request.getParameter("emailAddress");
		String subject = request.getParameter("subject");
		String messageText = request.getParameter("message");
		
		//Define a job and tie to QuartzJob class mainly how and when we send our email
		JobDetail jd = JobBuilder.newJob(AutoMailer.class).withIdentity("New Email Activity", "AutoMailer").build();
		
		//Passing parameters
		jd.getJobDataMap().put("name", name);
		jd.getJobDataMap().put("emailAddress",emailAddress);
		jd.getJobDataMap().put("subject", subject);
		jd.getJobDataMap().put("messageText",messageText);
		jd.getJobDataMap().put("host", host);
		jd.getJobDataMap().put("port", port);
		jd.getJobDataMap().put("adminUserName", adminUserName);
		jd.getJobDataMap().put("adminPassword",adminPass);
	
		/* Creating a trigger for our job a Which is a date and time that we get from our email Servlet.
		 * You can find more information on cron expressions from <ttps://docs.oracle.com/cd/E12058_01/doc/doc.1014/e12030/cron_expressions.htm>. 
		 */Trigger t1 = TriggerBuilder.newTrigger().withIdentity("CronTrigger","AutoMailer").withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
		
		 //Scheduling Job
		 try
		{
			Scheduler sc1 = new StdSchedulerFactory().getScheduler();
			sc1.start();
			sc1.scheduleJob(jd,t1);
			
		} catch (SchedulerException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	

		
    }


}
