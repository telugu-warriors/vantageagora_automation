package utils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import wrapperClass.TestBase;

public class LogGenerator extends TestBase {
	static ExtentHtmlReporter htmlReporter;
	static ExtentReports extent;
	static ExtentTest test;
	static String filePath;
	
	public static void startReporter(){
		htmlReporter = new ExtentHtmlReporter("extent.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}
	
	public static void startTestLogger(){
		test = extent.createTest(testCaseID, testDescription); 
	}
	
	public static void reportStep(String status, String log_mesage){
		try{
			getScreenShotAndStore();
			if(status.equalsIgnoreCase("PASS")){
				test.log(Status.PASS, log_mesage+test.addScreenCaptureFromPath(filePath));
			}
			else if(status.equalsIgnoreCase("FAIL")){
				test.log(Status.FAIL, log_mesage+test.addScreenCaptureFromPath(filePath));
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void endReporter(){
		extent.flush();
	}
	
	public static void getScreenShotAndStore(){
		try{
			long name = generateRandomFileName();
			File screenshot = driver.getScreenshotAs(OutputType.FILE);
			filePath = "./screenshots/"+name+".png";
			FileUtils.copyFile(screenshot, new File(filePath));
		}catch(IOException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static long generateRandomFileName(){
		long name;
		name = System.currentTimeMillis();
		return name;
	}

}
