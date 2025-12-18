package eal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import eal.utilities.CommonMethods;
import eal.utilities.LogColor;

import org.apache.logging.log4j.*;

public class NewCustomerPage_POM extends CommonMethods {

	public static final Logger logger = LogManager.getLogger(HomePage_POM.class);

	public NewCustomerPage_POM() {
		PageFactory.initElements(driver, this);
	}

	// ================================
	// 🔹 Dynamic Locator Templates
	// ================================

//	private static final String FIELD_INPUT_XPATH = "//td[text()='%s']/following-sibling::td/input   |  //td[text()='%s']/following-sibling::td/textarea";
	private static final String FIELD_INPUT_XPATH = "//td[text()='%s']/following-sibling::td/*[self::input or self::textarea]";
	private static final String Gender_Radio_Button = "//td[text()='Gender']/following-sibling::td/input[@value='%s']";
	// ================================
	// 🔹 Action Methods
	// ================================

	public boolean enterTypableFieldValue(String fieldName, String value) {
		try {

			String FormattedXpath = String.format(FIELD_INPUT_XPATH, fieldName);
			logger.info(LogColor.DarkGreen + FormattedXpath + LogColor.RESET);

			WebElement input = driver.findElement(By.xpath(FormattedXpath));

			safeSendKeys(input, value);

			String actualValue = input.getAttribute("value");
			boolean success = actualValue.equals(value);

			if (success) {
				logger.info("✅ Entered value for field: " + fieldName + " -> " + value);
			} else {
				logger.warn("❌ Value mismatch for field: " + fieldName + ". Expected: " + value + " but found: "
						+ actualValue);
			}

			return success;
		} catch (Exception e) {
			logger.error(LogColor.RED + "Error entering value for field: " + fieldName + " -> " + e + LogColor.RESET);
			return false;
		}

	}

	public boolean enterDateFieldValue(String fieldName, String value) {
		try {
			String FormattedXpath = String.format(FIELD_INPUT_XPATH, fieldName);

			logger.info(LogColor.DarkGreen + FormattedXpath + LogColor.RESET);

			WebElement input = driver.findElement(By.xpath(FormattedXpath));

			boolean isDateInserted = safeSetDateValue(input, value);
			return isDateInserted;

		} catch (Exception e) {
			logger.error(LogColor.RED + "Error entering value for field: " + fieldName + " -> " + e + LogColor.RESET);
			return false;
		}
	}

	public boolean SelectGender(String fieldName, String value) {

		WebElement input = null;
		if (value.equalsIgnoreCase("male")) {
			input = driver.findElement(By.xpath(String.format(Gender_Radio_Button, "m")));
		} else if (value.equalsIgnoreCase("female")) {
			input = driver.findElement(By.xpath(String.format(Gender_Radio_Button, "f")));
		}
		
		if (input == null) {
            logger.error(LogColor.RED + "Invalid gender option passed: " + fieldName + LogColor.RESET);
            return false;
        }
		
		safeSelectRadioButton(input);
		
		 boolean IsRadioButtonselected = input.isSelected();
         if (IsRadioButtonselected) {
             logger.info("✅ Radio button selected successfully for: " + fieldName + " -> " + value);
         } else {
             logger.warn("❌ Radio button NOT selected for: " + fieldName + " -> " + value);
         }

         return IsRadioButtonselected;
		

	}

}
