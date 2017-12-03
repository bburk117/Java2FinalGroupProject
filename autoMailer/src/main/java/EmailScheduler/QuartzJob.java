package EmailScheduler;

/*
 * Written By: Dylan Kennedy
 * Date: 11/24/17
 * Goal to implement Quartz Scheduler in our email program
 */

//Imports
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzJob implements Job 
{

	public void execute(JobExecutionContext context) throws JobExecutionException 
	{
		System.out.print("Test");
	}


}
