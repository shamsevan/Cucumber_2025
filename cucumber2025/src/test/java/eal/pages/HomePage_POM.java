package eal.pages;

import eal.utilities.CommonMethods;
import eal.utilities.ConfigurationReader;
import eal.utilities.LogColor;

import org.apache.logging.log4j.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage_POM extends CommonMethods {
	public static final Logger logger = LogManager.getLogger(HomePage_POM.class);

	public HomePage_POM() {
		PageFactory.initElements(driver, this);
	}

	// @findBy
	@FindBy(xpath = "//td[contains(text(),'UserID')]")
	public WebElement userid_text;

	@FindBy(xpath = "//td[contains(text(),'Password')]")
	public WebElement password_text;

	@FindBy(xpath = "//input[@type='submit']")
	public WebElement login;

	// By Locator:
	By userid_text_By = By.xpath("//td[contains(text(),'UserID')]");
	By password_text_By = By.xpath("//td[contains(text(),'Password')]");

	// String controllable xpaths:

	String homepage_uitextContained_elements = "//td[contains(text(),'%s')]";
	String homepage_fields = "//td[contains(text(),'%s')]/following-sibling::td/input";

	public boolean verify_homepage_title() {

		try {
			logger.info("Getting the Actual title");
			String actualTile = driver.getTitle();
			logger.info("Got the Title");

			String expectedTitle = "Guru99 Bank Home Page";

			if (actualTile.equals(expectedTitle)) {

				return true;

			}

			else {
				return false;
			}

		} catch (Exception e) {
			logger.error(LogColor.RED + e + LogColor.RESET);
			return false;
		}

	}

	public boolean verify_HomePageTextelement_isvisible(String elementText) {
		try {
//			boolean presensce=isElementDisplayed(userid_text);

			String formattedXpathof_Ui_Element = String.format(homepage_uitextContained_elements, elementText);

			logger.info(formattedXpathof_Ui_Element);
			boolean presensce = isElementPresent(By.xpath(formattedXpathof_Ui_Element));

			if (presensce) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			logger.error(LogColor.RED + e + LogColor.RESET);
			return false;
		}
	}

	public String passFieldValue(String fieldValue, String fieldName) {

		try {

			String Formatted_Field = String.format(homepage_fields, fieldName);
			logger.info(Formatted_Field);
			
			dashboard_pom.iFLOgOutPresent_clickOnLogout();
			
			WebElement field = driver.findElement(By.xpath(Formatted_Field));

			logger.info("Clicking on Fields");
			clickAndDraw(field);

			logger.info("passing value: " + fieldValue);
			safeSendKeys(field, fieldValue);

			logger.info("get the field value to make sure it is filled up properly");
			String actualValue = field.getAttribute("value");
			return actualValue;
		}

		catch (Exception e) {
			logger.error(LogColor.RED + e + LogColor.RESET);
			return null;
		}

	}

	public String clickOnLoginWith_Invalid_Credential() {
		try {
			logger.info("clicking on login button");
			clickAndDraw(login);

			String actualAlertText = getAlertText();
			return actualAlertText;

		}

		catch (Exception e) {
			logger.error(LogColor.RED + e + LogColor.RESET);
			return null;
		}

	}

	public boolean clickOnLoginWith_valid_Credential() {
		try {
			logger.info("clicking on login button with validCredentials");
			clickAndDraw(login);

			String expectedTitle = ConfigurationReader.getProperty("titleAfterLogin");

			boolean isTheTitleCorrect = verifyPageTitle(expectedTitle);

			return isTheTitleCorrect;

		}

		catch (Exception e) {
			logger.error(LogColor.RED + e + LogColor.RESET);
			return false;
		}

	}

	public String clearHomePageField(String fieldName) {

		String Formatted_Field = String.format(homepage_fields, fieldName);
		logger.info(Formatted_Field);

		WebElement field = driver.findElement(By.xpath(Formatted_Field));
		field.clear();

		String ActualFieldValueAfterClear = getAttributeValue(field, "value");
		return ActualFieldValueAfterClear;
	}

}
