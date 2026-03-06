package garam.selenium_framework_lab;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import garam.page_objects.CheckOutPage;
import garam.page_objects.LandingPage;
import garam.page_objects.MyCartPage;
import garam.page_objects.OrderHistoryPage;
import garam.page_objects.ProductCatalogue;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// WebDriverManager.chromedriver().setup(); - not necessary since Selenium 4.6
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		LandingPage landingPage = new LandingPage(driver);
		landingPage.openApp();
		ProductCatalogue productCatalogue = landingPage.login("garam@gmail.com", "Test123!");

		List<WebElement> proudcts = productCatalogue.getProductList();
		productCatalogue.addProductToCart("ZARA COAT 3");
		MyCartPage cartPage = productCatalogue.goToCartPage();

		Assert.assertTrue(cartPage.hasProductInCart("ZARA COAT 3"));
		CheckOutPage checkOutPage = cartPage.goToCheckout();

		OrderHistoryPage orderHistoryPage = checkOutPage.checkOut();
		Assert.assertTrue(orderHistoryPage.getMessage().equalsIgnoreCase("Thankyou for the order."));
	}

}
