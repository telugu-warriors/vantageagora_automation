package wrapperClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import utils.LogGenerator;

/**
 * This class contains all the reusable methods/ generic methods which will be
 * used across the project.
 */
public class GenericMethods {
	protected static ChromeDriver driver;
	public String url, port, hub, primaryWindowHandle;
	protected static Properties cprop;
	protected static Properties prop;
	
	public GenericMethods(){
		 cprop = new Properties();
		 try {
			cprop.load(new FileInputStream(new File("./config.properties")));
			url = cprop.getProperty("URL");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
/**
 * Invokes the desired application in a browser based on the passed
 * argument values
 * 
 * @param browser Name of the browser (chrome/ie/gecko  )
 * @param url The URL of the test application
 * @throws IOException 
 */
	public void invokeApp(String browser) throws IOException {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setBrowserName(browser);
		dc.setPlatform(Platform.WINDOWS);
		try {
			if (browser.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver(dc);
			}
			else if (browser.equalsIgnoreCase("ie")) {
			}
			driver.manage().window().maximize();
			driver.manage().window().getSize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.get(url);
			primaryWindowHandle = driver.getWindowHandle();
			LogGenerator.reportStep("PASS", "Brower" +browser+" launched successfully");

		} catch (Exception e) {
			LogGenerator.reportStep("FAIL", "Browser " +browser+" not initiated successfully");
			e.printStackTrace();
		}
	}
	
	/**
	 * Verifies the title of the current page with the expected page title
	 * @param expectedTitle name of the expected title
	 * @return boolean if actual and expected are matched
	 * @throws IOException 
	 */
	public boolean verifyTitle(String expectedTitle){
		boolean status = false;
		String actualTitle = driver.getTitle();
		if (actualTitle.equalsIgnoreCase(expectedTitle)){
			LogGenerator.reportStep("PASS", "Title : "+expectedTitle+ " is matching with the actual tilte: " +actualTitle+ "");
			status = true;
		}
		else{
			LogGenerator.reportStep("FAIL", "Title : "+expectedTitle+ " is not matching with the actual tilte: " +actualTitle+ "");
		}
		return status;
	}
	
	/**
	 * Closes the current browser instance
	 */
	public void closeBrowser() throws InterruptedException, Exception{
		Thread.sleep(2000);
		driver.close();
	}
	
	/**
	 * Clicks a WebElement having the given element property
	 * 
	 * @param id ID attribute value of the element
	 * @return boolean true if the click performed without an issue
	 */
	public boolean clickByID(String id) throws IOException{
		boolean status = false;
		try{
			driver.findElement(By.id(id)).click();
			LogGenerator.reportStep("PASS", "Element with property: "+id+" clicked successfully");
			status = true;
		}catch(Exception e){
			LogGenerator.reportStep("Fail", "Element with property: "+id+" is not clicked successfully");
			e.printStackTrace();
		}
		return status;
	}
	
	/**
	 * Clicks a WebElement having the given element property
	 * @param xpath Xml Path of the web element.
	 * @return boolean true if the click performed without an issue
	 * @throws IOException
	 */
	public boolean clickByxpath(String xpath) throws IOException{
		boolean status = false;
		try{
			driver.findElement(By.xpath(xpath)).click();
			LogGenerator.reportStep("PASS", "Element with property: "+xpath+" clicked successfully");
			status = true;
		}catch(Exception e){
			LogGenerator.reportStep("Fail", "Element with property: "+xpath+" is not clicked successfully");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean hoverElementById(String id){
		boolean status = false;
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.id(id))).build().perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LogGenerator.reportStep("PASS", "The element with the property:"+id+ "hohvered successfully");
		status = true;
		return status;
	}
}
