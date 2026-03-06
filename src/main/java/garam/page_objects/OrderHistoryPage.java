package garam.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import garam.abstract_components.AbstractComponent;

public class OrderHistoryPage extends AbstractComponent {
	WebDriver driver;

	public OrderHistoryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	@FindBy(css = "h1[class*='hero-primary']")
	WebElement compeletionMessage;

	By messageLocator = By.cssSelector("h1[class*='hero-primary']");

	public String getMessage() {
		// open till the page appear
		waitForElementToAppear(messageLocator);
		return compeletionMessage.getText();
	}
}
