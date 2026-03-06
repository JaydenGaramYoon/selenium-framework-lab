package garam.page_objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import garam.abstract_components.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver){
		super(driver); //if one child give driver to parent, all child should give it
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css ="ngx-spinner div[class*='spinner']")
	WebElement spinner;
	
	@FindBy(css = "button[routerlink*='cart']")
	WebElement goToCartBtn;
	
	By productsLocator = By.cssSelector(".mb-3");
	By addToCartBtn = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsLocator);
		return products;
	}
	
	public WebElement getProductByName(String proudctName) {
		WebElement product = getProductList().stream() //OR products.strea()
				.filter(prod -> prod.findElement(By.cssSelector("b")).getText().equals(proudctName))
				.findFirst().orElse(null);
		return product;
	}
	
	public void addProductToCart(String proudctName){
		 getProductByName(proudctName).findElement(addToCartBtn).click();
		 //page factory cannot be in the findElment execution line
		 waitForElementToAppear(toastMessage);
		 waitForElementToDisappear(spinner);
	}
	
	public MyCartPage goToCartPage() {
		 waitForElementToDisappear(spinner);
		 goToCartBtn.click();
		 return new MyCartPage(driver);
	}
}
