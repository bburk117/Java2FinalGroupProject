package autoMailer;
/**
 * A Servlet that implements javamail and quartz to schedule the delivery of emails.
 * @author Dylan Kennedy
 * @version 1.0  11/24/17
 */
//Imports
import java.io.IOException;
import java.io.PrintWriter;

//Servlet libs
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
//Quartz libs
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;



@WebServlet("/autoMailer")
public class EmailServlet extends HttpServlet {

	//Variables
	private static final long serialVersionUID = 1L;
	private String host;
	private String port;
	private String adminUserName;
	private String adminPass;
	private String name;
	private String emailAddress;
	private String subject;
	private String messageText;
	private String cronExpression;

	private static final Logger log = Logger.getLogger("AutoMailer.class");


	StdSchedulerFactory sF;

	public void init()
	{
		//Reads Smtp server settings from web.xml
		ServletContext sContext = getServletContext();
		host = sContext.getInitParameter("host");
		port = sContext.getInitParameter("port");
		adminUserName = sContext.getInitParameter("user");
		adminPass = sContext.getInitParameter("adminPass");

		//Initializing quartz
		sF= (StdSchedulerFactory) sContext.getAttribute(QuartzInitializerListener.QUARTZ_FACTORY_KEY);

	}
	//doPost
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		log.info("info");
		//Associating with html elements
        name = request.getParameter("name");
		emailAddress = request.getParameter("emailAddress");
		subject = request.getParameter("subject");
		messageText = request.getParameter("message");
		cronExpression = request.getParameter("cronExpression");
		PrintWriter writer = response.getWriter();

		//Define a job and tie to QuartzJob class mainly how and when we send our email
		JobDetail jd = JobBuilder.newJob(AutoMailer.class).withIdentity("New Email Activity", "AutoMailer").build();

		//Passing parameters to job data map
		jd.getJobDataMap().put("name", name);
		jd.getJobDataMap().put("emailAddress",emailAddress);
		jd.getJobDataMap().put("subject", subject);
		jd.getJobDataMap().put("messageText",messageText);
		jd.getJobDataMap().put("host", host);
		jd.getJobDataMap().put("port", port);
		jd.getJobDataMap().put("adminUserName", adminUserName);
		jd.getJobDataMap().put("adminPassword",adminPass);
		System.out.println("Debug: "+jd.toString());

		/* Creating a trigger for our job a Which is a date and time that we get from our email Servlet.
		 * You can find more information on cron expressions from <ttps://docs.oracle.com/cd/E12058_01/doc/doc.1014/e12030/cron_expressions.htm>.
		 */Trigger t1 = TriggerBuilder.newTrigger().withIdentity("CronTrigger","AutoMailer").withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
		 t1.getStartTime();

		 try
		{
			 Scheduler scheduler = sF.getScheduler();

			scheduler.start();
			scheduler.scheduleJob(jd,t1);
		} catch (SchedulerException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.print(t1.getDescription());
		writer.println(cronExpression);
		writer.println(log);


	}


}
