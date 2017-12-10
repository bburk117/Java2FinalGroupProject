package quote;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Servlet implementation class QuoteRequest
 */
@WebServlet("/quote")
public class QuoteRequest extends HttpServlet {
	
	//Variable Declaration
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
		
		private static final Logger log = Logger.getLogger("org.quartz.ee.servlet.QuartzInitializerListener");
		
		StdSchedulerFactory sF;
    
    public void init(){

		//Reads Smtp server settings from web.xml
		ServletContext sContext = getServletContext();
		host = sContext.getInitParameter("host");
		port = sContext.getInitParameter("port");
		adminUserName = sContext.getInitParameter("user");
		adminPass = sContext.getInitParameter("adminPass");
		
		//Initializing quartz
		sF= (StdSchedulerFactory) sContext.getAttribute(QuartzInitializerListener.QUARTZ_FACTORY_KEY);
	
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//Associating with html elements
        name = request.getParameter("name");
		emailAddress = request.getParameter("emailAddress");
		subject = request.getParameter("subject");
		messageText = request.getParameter("message");
		cronExpression = request.getParameter("cronExpression");
		PrintWriter writer = response.getWriter();
		writer.print("Hello");
	}	

}
