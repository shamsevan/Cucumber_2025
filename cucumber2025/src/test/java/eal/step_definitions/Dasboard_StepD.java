package eal.step_definitions;

import eal.utilities.CommonMethods;
import eal.utilities.LogColor;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import java.util.List;

import org.apache.logging.log4j.*;

public class Dasboard_StepD extends CommonMethods {
	public static final Logger logger = LogManager.getLogger(Dasboard_StepD.class);

	@Given("{string} Button is visible")
	public void click_on_dashboardbutton(String buttonName) {

		logger.info("Checking for " + buttonName + "button");
		boolean ElementPresence = dashboard_pom.verifyLinkButtonExistance(buttonName);

		softAssert.softAssertTrue(ElementPresence, buttonName + ": is visible", buttonName + ": is not visible");
	}

	@Given("I open the {string} page with PageButtontitle {string}")
	public void i_open_the_page_with_title(String PageButton, String ExpectedPageTitle) {
		boolean TitleMatched = dashboard_pom.clikOnLeftPanelButton(PageButton, ExpectedPageTitle);

		softAssert.softAssertTrue(TitleMatched, "Navigated to page: " + PageButton,
				"Title didn't match. couldn't navigate to: " + PageButton);
	}

	@When("I fill the form using file {string} and sheet {string} with fields:")
	public void i_fill_the_form_using_file_and_sheet_with_fields(String fileName, String sheetName,
			DataTable dataTable) {

		logger.info("Opening the Excel File");

		// 1. Initialize the Excel context (opens file, loads sheet, maps headers)
		excelUtil.openExcel(fileName, sheetName);

		// 2. Get the list of fields (Row Keys) from the Cucumber DataTable
		List<String> fields = dataTable.asList();

		for (String fieldKey : fields) {
			String field = fieldKey.trim();

			String value = excelUtil.getCellData(field, "Values").trim();

			if (value.isEmpty()) {
				logger.warn("❌ Data not found in Excel for field/key: " + field);

				// softAssert is inherited from CommonMethods
				softAssert.softAssertFail("Data not found in Excel for field/key: " + field);
				continue;
			}

			boolean result = false;

			// 4. Perform UI Action based on the field type

            // 4. Perform UI Action based on the field type
            switch (field) {
                case "Customer Name":
                case "Address":
                case "City":
                case "State":
                case "PIN":
                case "Mobile Number":
                case "E-mail":
                    // newCustomer_Pom is inherited from CommonMethods
                    result = newCustomer_Pom.enterTypableFieldValue(field, value);
                    break;
                    
                case "Date of Birth":
                	result=newCustomer_Pom.enterDateFieldValue(field, value);
                	break;

                case "Gender":
                    // Simplified call: Assumes POM method only needs the value
                	result = newCustomer_Pom.SelectGender(field,value); 
                    break;


			default:
				logger.warn("⚠️ Field not mapped in step definition switch case: " + field);

			}

			softAssert.softAssertTrue(result, field + ": is filled with value: " + value,
					field + ": failed to fill UI element.");
		}

	}

	@Then("I click Submit")
	public void i_click_submit() {

	}

}
