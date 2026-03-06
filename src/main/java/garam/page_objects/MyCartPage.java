package garam.page_objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import garam.abstract_components.AbstractComponent;


public class MyCartPage  extends AbstractComponent{
	WebDriver driver;
	
	public MyCartPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;
	By cartProductsLocator = By.cssSelector(".cartSection h3");
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutBtn;


	public List<WebElement> getCartProducts() {
		waitForElementToAppear(cartProductsLocator);
		return cartProducts;
	}
	
	public boolean hasProductInCart(String productName) {
		waitForElementToAppear(cartProductsLocator);
		boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage goToCheckout() {
		checkoutBtn.click();
		return new CheckOutPage(driver);
	}
}
