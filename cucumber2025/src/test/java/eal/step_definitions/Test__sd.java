package eal.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eal.utilities.CommonMethods;

public class Test__sd extends CommonMethods {
	public static final Logger logger=LogManager.getLogger(Test__sd.class);
	
	
	
	@Given("User is on homepage")
	public void user_is_on_homepage() {
		logger.info("sd- 1 -- User is on homepage");
		waitFor(15);
        
	}
	
	@Then("it will click on loginButton")
	public void it_will_click_on_login_button() {
		logger.info("sd- 2 -- it will click on loginButton");
	}


}
