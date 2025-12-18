package eal.runners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import eal.utilities.LogColor;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(plugin = { 
		"pretty", 
		"html:target/default-cucumber-reports/htmlReport.html",
		"json:target/cucumber.json", 
		"junit:target/cucumber.xml", 
		"rerun:target/cucumber.txt",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", 
		"eal.hooks.StepNameListener" },

		features = "src/test/resources/features/", glue = { "eal.step_definitions",
				"eal.hooks" }, 
		dryRun = false,


		tags = "@TC_015",

		monochrome = false)

public class ealrunner  {
	


	// variables
	public static final Logger logger = LogManager.getLogger(ealrunner.class);

	@BeforeClass
	public static void globalSetup() {
		logger.info(LogColor.ThinnerPurple + " @BeforeClass-ealRunner " + "Running one time" + LogColor.RESET);
	}

	@AfterClass
	public static void teardown() {
		logger.info(
				LogColor.ThinnerPurple + " @AfterClass -ealRunner " + "Running one time" + LogColor.RESET);

	}



	
	

}
