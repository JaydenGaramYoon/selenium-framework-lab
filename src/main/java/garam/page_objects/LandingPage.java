package garam.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import garam.abstract_components.AbstractComponent;

public class LandingPage extends AbstractComponent{
	WebDriver driver;
	
	public LandingPage(WebDriver driver){
		super(driver); // send it to the parent class
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement emailField;
	
	@FindBy(id="userPassword")
	WebElement pwdField;
	
	@FindBy(id="login")
	WebElement submitBtn;
	
	public ProductCatalogue login(String email, String password) {
		emailField.sendKeys(email);
		pwdField.sendKeys(password);
		submitBtn.click();
		return new ProductCatalogue(driver);
	}
	
	public void openApp() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
