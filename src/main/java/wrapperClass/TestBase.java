package wrapperClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.LogGenerator;

public class TestBase extends GenericMethods {
	protected static String testDescription;
	protected static String testCaseID;
	protected static String browser;
	
	@BeforeSuite
	public void startLogger(){
		LogGenerator.startReporter();
		prop = new Properties();
		try{
			prop.load(new FileInputStream(new File("./object.properties")));
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	@BeforeMethod
	public void startTestLevelLogger() throws IOException{
		LogGenerator.startTestLogger();
		invokeApp(browser);
	}
	
	@AfterMethod
	public void dummyafterMethod(){
		
	}
	@AfterSuite
	public void endReports(){
		LogGenerator.endReporter();
	}

}
