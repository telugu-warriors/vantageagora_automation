package pages;

import java.io.IOException;

import utils.LogGenerator;
import wrapperClass.GenericMethods;

public class HomePage extends GenericMethods{
	
	public HomePage() throws IOException{
		if (!verifyTitle(prop.getProperty("homePage.title"))){
			LogGenerator.reportStep("FAIL", "This is not home page");
		}
	}
	
	public HomePage hoverOnOXizon(){
		hoverElementById(prop.getProperty("OXzion.menu.id"));
		return this;
	}
	
	public LoginPage clicLogIn() throws IOException{
		clickByxpath(prop.getProperty("login.icon.id"));
		return new LoginPage();
	}

}
