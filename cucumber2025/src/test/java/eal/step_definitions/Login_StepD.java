package eal.step_definitions;

import eal.utilities.CommonMethods;
import eal.utilities.LogColor;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.*;

public class Login_StepD extends CommonMethods {
	public static final Logger logger = LogManager.getLogger(Login_StepD.class);

	@Given("Validate User landed on homepage")
	public void validate_user_landed_on_homepage() {
		logger.info("Verify Title");

		boolean titleMatched = hmpage_pom.verify_homepage_title();

		logger.info("Performing Assertion ");
		softAssert.softAssertTrue(titleMatched, "Title Matched Successfully", "Title didn't match");

	}

	@Then("Verify {string} is visible")
	public void verify_homePagetxtElement_is_visible(String string_value_from_feature) {

		logger.info("Verifying presense of " + string_value_from_feature + " text");
		boolean validationStatus = hmpage_pom.verify_HomePageTextelement_isvisible(string_value_from_feature);

		logger.info("Assertion");
		softAssert.softAssertTrue(validationStatus, string_value_from_feature + " Text is visible in the screen",
				string_value_from_feature + " text is not visible in the Screen");

	}

	@Given("Pass {string} on {string} Field")
	public void pass_on_field(String fieldValue, String fieldName) {

		String actualValue = hmpage_pom.passFieldValue(fieldValue, fieldName);

		softAssert.softAssertEquals(actualValue, fieldValue, "Field is filled up");
	}

	@Then("click on Login button")
	public void click_on_button() {

		String actualAlertText = hmpage_pom.clickOnLoginWith_Invalid_Credential();

		String expectedAlertText = "User is not valid";

		softAssert.softAssertEquals(actualAlertText, expectedAlertText, "Alert generated and Text is as expected");

	}
	
	
	@Then("click on Login button with Valid Credentials")
	public void click_on_Login_button_WithValidCreds() {

		boolean TitleMatched = hmpage_pom.clickOnLoginWith_valid_Credential();

		String expectedAlertText = "User is not valid";

		softAssert.softAssertTrue(TitleMatched, "DashboardTitleMatched", "Alert generated and Text is as expected");

	}


	@Then("Click on {string} button from the alert")
	public void click_on_button_from_the_alert(String string) {
		acceptAlert();

		boolean isUserOnHomePage = hmpage_pom.verify_homepage_title();

		softAssert.softAssertTrue(isUserOnHomePage, "Alert accepted and user came back to homepage",
				"Alert not accepted");

	}

	@Given("Pass {int} digit Numeric userID {int} on userID Field and immidiately Clear it")
	public void pass_digit_numeric_user_id_on_user_id_field_and_immidiately_clear_it(Integer count, Integer userID) {

		String fieldName = "UserID";

		String updatedUserID = String.valueOf(userID);
		String ActualValue_fromTheUI = hmpage_pom.passFieldValue(updatedUserID, fieldName);

		if (ActualValue_fromTheUI.equals(userID)) {
			logger.info(LogColor.Blue + "" + LogColor.RESET);
		} else {
			logger.error(LogColor.RED + "Different value inserted" + LogColor.RESET);
		}

		String actualValueAfterClear = hmpage_pom.clearHomePageField(fieldName);

		softAssert.softAssertEquals(actualValueAfterClear, "", "UserField Cleared after typing");
	}

	@Given("I enter and immediately clear the following UserIDs:")
	public void enterandClearIds(DataTable dataTable) {

		String fieldName = "UserID";

		List<String> userIDs = dataTable.asList();

		for (String userID : userIDs) {

			String ActualValue_fromTheUI = hmpage_pom.passFieldValue(userID, fieldName);

			if (ActualValue_fromTheUI.equals(userID)) {
				logger.info(LogColor.Blue + userID+ ": User ID Inserted" + LogColor.RESET);
			} else {
				logger.error(LogColor.RED + userID+ " Different value inserted" + LogColor.RESET);
			}

			String actualValueAfterClear = hmpage_pom.clearHomePageField(fieldName);

			softAssert.softAssertEquals(actualValueAfterClear, "", "UserField Cleared after typing");
		}

	}
	

@Given("I try the following credentials:")
public void i_try_the_following_credentials(DataTable dataTable) {
	    
	List<Map<String,String>> credentials=dataTable.asMaps(String.class,String.class);
	
	for(Map<String,String> row:credentials) {
		String userID=row.get("UserID");
		String password=row.get("Password");
		
		String ActualUserValue_fromTheUI = hmpage_pom.passFieldValue(userID, "UserID");
		
		String ActualPasswordValue_fromTheUI = hmpage_pom.passFieldValue(userID, "Password");
		
		// Step 3: Clear both fields after typing
        String clearedUserId = hmpage_pom.clearHomePageField("UserID");
        softAssert.softAssertEquals(clearedUserId, "", "UserID field cleared after typing");
	}
	

    
}



	@Then("click on Reset Button")
	public void click_on_reset_button() {

	}
	


}
