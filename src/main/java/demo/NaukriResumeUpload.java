package demo;

import java.awt.AWTException;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import wrapperClass.GenericMethods;

public class NaukriResumeUpload{

	public static void main(String[] args) throws AWTException, InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
	        driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);	
			driver.get("https://www.freepdfconvert.com/");
		    driver.findElement(By.id("clientUpload")).click();
		    Robot robo = new Robot();
		    Thread.sleep(2000);
		    StringSelection path = new StringSelection("C:\\Users\\user\\Downloads\\sandeep Photo.jpg");
		    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(path, null);
				robo.keyPress(KeyEvent.VK_CONTROL);
				robo.keyPress(KeyEvent.VK_V);
				
				robo.keyRelease(KeyEvent.VK_CONTROL);
				robo.keyRelease(KeyEvent.VK_CONTROL);
				robo.keyPress(KeyEvent.VK_ENTER);
				robo.keyRelease(KeyEvent.VK_ENTER);
			
	}
}
