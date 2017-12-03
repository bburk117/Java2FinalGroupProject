package EmailScheduler;
/**
 * A Servlet that implements javamail and quartz to schedule the delivery of emails.
 * @author Dylan Kennedy
 * @version 1.0 Build 5 11/24/17
 */
//Imports
import java.io.IOException;
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
	
	private static final long serialVersionUID = 1L;
	
	public void QuartzBuild(String cronExpression) throws SchedulerException 
	{
		
		
		//Define a job and tie to QuartzJob class mainly how and when we send our email
		JobDetail job = JobBuilder.newJob(QuartzJob.class).build();
	
		
		
		  
		/* 
		 * Creating a trigger for our job a Which is a date and time that we get from our email servlet.
		 * You can find more information on cron expressions from <ttps://docs.oracle.com/cd/E12058_01/doc/doc.1014/e12030/cron_expressions.htm>. 
		 */Trigger t1 = (Trigger) TriggerBuilder.newTrigger().withIdentity("CronTrigger").withSchedule(CronScheduleBuilder.cronSchedule(cronExpression));
		
		//Creating our scheduler
		Scheduler sc1 = StdSchedulerFactory.getDefaultScheduler();
		
		//Start Scheduler
		sc1.start();
	}
    
	//Default Constructor
	public EmailServlet() 
	{
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
