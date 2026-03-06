package garam.page_objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import garam.abstract_components.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	@FindBy(css = "input[class*='input']")
	List<WebElement> infoInputFileds;

	@FindBy(css = "a[class*='submit']")
	WebElement placeOrderBtn;
	
	@FindBy(css = "button[class*='inserted']")
	WebElement selectCountry;
	
	By infoInputLocator = By.cssSelector("input[class*='input']");
	By selectCountryLocator = By.cssSelector("button[class*='inserted']");
	By placeOrderBtnLocator = By.cssSelector("a[class*='submit']");
	By orderCompletionLogoLocator = By.cssSelector("h1[class*='hero-primary']");
	
	public OrderHistoryPage checkOut() {
		waitForElementToAppear(infoInputLocator);
		// 2.enter personal information
		String[] infos = { "1234 5678 0000", "123", "garam", "1111", "garam@gmail.com", "Canada" };
		for (int i = 0; i < infos.length; i++) {
			infoInputFileds.get(i).sendKeys(infos[i]);
		}
		// explicit wait to country auto-suggested visible
		waitForElementToAppear(selectCountryLocator);
		selectCountry.click();
		placeOrderBtn.click();
		return new OrderHistoryPage(driver);
	}

}
