package pages;

import utils.LogGenerator;
import wrapperClass.GenericMethods;

public class LoginPage extends GenericMethods{
	
	public LoginPage(){
		if(!verifyTitle(prop.getProperty("loginPage.title"))){
			LogGenerator.reportStep("Fail", "This is not login page");
			System.out.println("Test Changes");

		}
	}
}
