package configfile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader 
{
	private Properties properties;
	private final String propertyFilePath= "ConfigurationFile//config.properties";
	
	public ConfigFileReader()
	{
		BufferedReader reader;
		try 
		{
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try 
			{
				properties.load(reader);
				reader.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}  
	}


	public String getchromeDriverPath()
	{
		String chromeDriverPath = properties.getProperty("chromeDriverPath");
		if(chromeDriverPath!= null) return chromeDriverPath;
		else throw new RuntimeException("chrome Driver Path not specified in the Configuration.properties file.");  
	}

	/*public long getImplicitlyWait() {  
	String implicitlyWait = properties.getProperty("implicitlyWait");
	if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
	else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");  
	}*/

	public String getDemoURL() 
	{
		String DemoURL = properties.getProperty("DemoURL");
		if(DemoURL != null) return DemoURL;
		else throw new RuntimeException("Demo URL not specified in the Configuration.properties file.");
	}

	public String getUsername() 
	{
		String Username = properties.getProperty("Username");
		if(Username != null) return Username;
		else throw new RuntimeException("Username not specified in the Configuration.properties file.");
	}
	
	public String getPassword() 
	{
		String Password = properties.getProperty("Password");
		if(Password != null) return Password;
		else throw new RuntimeException("Password not specified in the Configuration.properties file.");
	}
	
	public String getOldPassword() 
	{
		String OldPassword = properties.getProperty("OldPassword");
		if(OldPassword != null) return OldPassword;
		else throw new RuntimeException("Old Password not specified in the Configuration.properties file.");
	}
	
	public String getNewPassword() 
	{
		String NewPassword = properties.getProperty("NewPassword");
		if(NewPassword != null) return NewPassword;
		else throw new RuntimeException("New Password not specified in the Configuration.properties file.");
	}
	
	public String getTabname() 
	{
		String Tabname = properties.getProperty("Tabname");
		if(Tabname != null) return Tabname;
		else throw new RuntimeException("Tabname not specified in the Configuration.properties file.");
	}
	
	public String getFilepath() 
	{
		String Filepath = properties.getProperty("Filepath");
		if(Filepath != null) return Filepath;
		else throw new RuntimeException("Filepath not specified in the Configuration.properties file.");
	}
	
	public String getReportpath() 
	{
		String Reportpath = properties.getProperty("Reportpath");
		if(Reportpath != null) return Reportpath;
		else throw new RuntimeException("Reportpath not specified in the Configuration.properties file.");
	}
	
	public String getProxy() 
	{
		String Proxy = properties.getProperty("Proxy");
		if(Proxy != null) return Proxy;
		else throw new RuntimeException("Proxy not specified in the Configuration.properties file.");
	}

}	
