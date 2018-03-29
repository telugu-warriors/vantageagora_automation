/**
 * 
 */
package testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.HomePage;
import wrapperClass.TestBase;

/**
 * @author yhari
 *
 */
public class OpenAndNavigateToLoginPage extends TestBase {
	@Test
	public void HomePageTest() throws InterruptedException, Exception{
		new HomePage()
	    .clicInputForms()
	    .clicSimpleForm()
		//.hoverOnOXizon()
		//.clicLogIn()
		.closeBrowser();
	}
	
	@BeforeClass
	public void defineBasicDetailsOfTest(){
		testCaseID = "TC_VA_01";
		testDescription = "Verification of the Home page title";
		browser = "chrome";
	}
}