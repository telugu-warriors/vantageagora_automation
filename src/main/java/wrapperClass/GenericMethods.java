package wrapperClass;

import java.awt.RenderingHints.Key;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.gargoylesoftware.htmlunit.javascript.host.event.Event;

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
	protected Robot robo;
		
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
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
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
	public boolean clickByID(String id) {
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
	 * @param name attribute value of the element
	 * @return boolean true if the click performed without an issue
	 */
	public boolean clickByName(String name){
		boolean status = false;
		try{
			driver.findElement(By.name(name)).click();
			LogGenerator.reportStep("PASS", "Element with property: "+name+" clicked successfully");
			status = true;
		}catch(Exception e){
			LogGenerator.reportStep("Fail", "Element with property: "+name+" is not clicked successfully");
			e.printStackTrace();
		}
		return status;
	}
	
	/**
	 * Clicks a WebElement having the given element property
	 * @param LinkText The exact text to match against
	 * @return boolean true if the click performed without an issue
	 */
	public boolean clickByLinktext(String LinkText) {
		boolean status = false;
		try{
			driver.findElement(By.linkText(LinkText)).click();
			LogGenerator.reportStep("PASS", "Element with property: "+LinkText+" clicked successfully");
			status = true;
		}catch(Exception e){
			LogGenerator.reportStep("Fail", "Element with property: "+LinkText+" is not clicked successfully");
			e.printStackTrace();
		}
		return status;
	}	
	
	/**
	 * Clicks a WebElement having the given element property
	 * @param ClassName The value of the "class" attribute to search for
	 * @return boolean true if the click performed without an issue
	 */
	public boolean clickByClassName(String ClassName) {
		boolean status = false;
		try{
			driver.findElement(By.className(ClassName)).click();
			LogGenerator.reportStep("PASS", "Element with property: "+ClassName+" clicked successfully");
			status = true;
		}catch(Exception e){
			LogGenerator.reportStep("Fail", "Element with property: "+ClassName+" is not clicked successfully");
			e.printStackTrace();
		}
		return status;
	}
	
	/**
	 * Clicks a WebElement having the given element property
	 * @param xpath Xml Path of the web element.
	 * @return boolean true if the click performed without an issue
	 */
	public boolean clickByxpath(String xpath){
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
	
	/**
	 * Clicks a WebElement having the given element property
	 * @param TagName The element's tagName
	 * @return boolean true if the click performed without an issue
	 */
	public boolean clickByTagname(String TagName) {
		boolean status = false;
		try{
			driver.findElement(By.tagName(TagName)).click();
			LogGenerator.reportStep("PASS", "Element with property: "+TagName+" clicked successfully");
			status = true;
		}catch(Exception e){
			LogGenerator.reportStep("Fail", "Element with property: "+TagName+" is not clicked successfully");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean uploadFile(String filePath) {
		boolean status = false;
		StringSelection path = new StringSelection(filePath);
		try{
			    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(path, null);
				robo.keyPress(KeyEvent.VK_CONTROL);
				robo.keyPress(KeyEvent.VK_V);
				robo.keyRelease(KeyEvent.VK_CONTROL);
				robo.keyRelease(KeyEvent.VK_CONTROL);
				robo.keyPress(KeyEvent.VK_ENTER);
				robo.keyRelease(KeyEvent.VK_ENTER);
			LogGenerator.reportStep("PASS", "Element with property: "+filePath+" clicked successfully");
			status = true;
		}catch(Exception e){
			LogGenerator.reportStep("Fail", "Element with property: "+filePath+" is not clicked successfully");
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
