package autoMailer;

public class EmailActivity
{
	//Variable Declaration 
	private String name;
	private String emailAddress;
	private String subject;
	private String messageText;
	private String host;
	private String port;
	private String userName;
	private String pass;
	public EmailActivity()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public EmailActivity(String name, String emailAddress, String subject, String messageText)
	{
		super();
		this.name = name;
		this.emailAddress = emailAddress;
		this.subject = subject;
		this.messageText = messageText;
	}

	@Override
	public String toString()
	{
		return "EmailActivity [name=" + name + ", emailAddress=" + emailAddress + ", subject=" + subject + ", messageText=" + messageText + "]";
	}

	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getEmailAddress()
	{
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress;
	}
	
	public String getSubject()
	{
		return subject;
	}
	
	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	
	public String getMessageText()
	{
		return messageText;
	}
	
	public void setMessageText(String messageText)
	{
		this.messageText = messageText;
	}

	public String getHost()
	{
		return host;
	}

	public void setHost(String host)
	{
		this.host = host;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPort()
	{
		return port;
	}

	public void setPort(String port)
	{
		this.port = port;
	}

	public String getPass()
	{
		return pass;
	}

	public void setPass(String pass)
	{
		this.pass = pass;
	}

}