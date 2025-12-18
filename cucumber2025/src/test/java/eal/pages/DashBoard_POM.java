package eal.pages;

import eal.utilities.CommonMethods;
import eal.utilities.LogColor;

import org.apache.logging.log4j.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashBoard_POM extends CommonMethods {
	public static final Logger logger = LogManager.getLogger(DashBoard_POM.class);

	public DashBoard_POM() {
		PageFactory.initElements(driver, this);
	}

	public void verify_Buttons_inDashBoard(String ButtonName) {
		logger.info("Verifying Button");
	}

	// @findBy
	@FindBy(xpath = "//a[text()='Log out']")
	public WebElement logout_Button;

	// String ControllableXpath
	String DashBoard_LinkElements = "//a[text()='%s']";

	By logout_Button_by = By.xpath("//a[text()='Log out']");

	public boolean verifyLinkButtonExistance(String ButtonName) {
		try {
			String forMattedButtonXpath = String.format(DashBoard_LinkElements, ButtonName);
			logger.info(forMattedButtonXpath);

			waitForNetworkIdle();

			boolean ElementPresence = isElementPresent(By.xpath(forMattedButtonXpath));
			return ElementPresence;
		}

		catch (Exception e) {
			logger.error(LogColor.RED + e + LogColor.RESET);
			return false;
		}

	}

	public void iFLOgOutPresent_clickOnLogout() {
		boolean logOut_Present = isElementPresent(logout_Button_by);

		if (logOut_Present) {
			logger.info("Logout Button is present , clicking on that");
			clickAndDraw(logout_Button);
			acceptAlert();
		}

		

		waitForPageAndAjaxToLoad();

	}

	public boolean clikOnLeftPanelButton(String PageButton, String ExpectedPageTitle) {
		try {
			String forMattedButtonXpath = String.format(DashBoard_LinkElements, PageButton);
			logger.info(forMattedButtonXpath);

			waitForNetworkIdle();

			boolean ElementPresence = isElementPresent(By.xpath(forMattedButtonXpath));
			
			//we didnt use earliar because driver.findelement can cause failure.
			WebElement button=driver.findElement(By.xpath(forMattedButtonXpath));
			
			clickAndDraw(button);
			
			boolean isTilteMatched=verifyPageTitle(ExpectedPageTitle);
			
			return isTilteMatched;

		} catch (Exception e) {
			logger.error(LogColor.RED + e + LogColor.RESET);
			return false;
		}

	}

}
