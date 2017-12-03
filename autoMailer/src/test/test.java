package test;


import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import autoMailer.AutoMailer;


public class test
{
	public static void main (String args[]) throws SchedulerException 
	{
		System.out.println("Running");
		String name="Dylan Kennedy";
		String emailAddress = "epicslurpee@gmail.com";
		String host = "smtp.gmail.com";
		String port = "587";
		String subject = "Test";
		String adminUserName= "epicslurpee@gmail.com";
		String adminPass = "fido7890";
		String messageText = "Hello World";
		
		
		JobDetail jd =JobBuilder.newJob(AutoMailer.class).withIdentity("AutoMailer","test").build();
		System.out.println(jd.toString());
		//Passing parameters
		jd.getJobDataMap().put("name", name);
		jd.getJobDataMap().put("emailAddress",emailAddress);
		jd.getJobDataMap().put("subject", subject);
		jd.getJobDataMap().put("messageText",messageText);
		jd.getJobDataMap().put("host", host);
		jd.getJobDataMap().put("port", port);
		jd.getJobDataMap().put("adminUserName", adminUserName);
		jd.getJobDataMap().put("adminPassword",adminPass);
		
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("Foo", "Bar").withSchedule(SimpleScheduleBuilder.simpleSchedule()
		.withIntervalInSeconds(5).repeatForever()).build();
		 
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		scheduler.scheduleJob(jd, trigger);
		System.out.println(scheduler.toString());
	}

}
