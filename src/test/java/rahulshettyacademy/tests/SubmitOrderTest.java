package rahulshettyacademy.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;


public class SubmitOrderTest extends BaseTest {
	
	String productName = "IPHONE 13 PRO";

	@Test (dataProvider = "getData", groups = {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException
	{
	
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage =	checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		System.out.println(confirmMessage);
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
	}
	
	@Test (dependsOnMethods = "submitOrder")
	public void OrderHistoryTest()
	{
		ProductCatalogue productCatalogue = landingPage.loginApplication("pk@automation.com", "Audi@7825");
		OrderPage orderPage =  productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}
	
	// Method 1 of data provider.
	//@DataProvider
	//public Object[][] getData()
	//{
	//	return new Object[][] {{"pk@automation.com", "Audi@7825", "IPHONE 13 PRO"}, {"shetty@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"}};
	//}
	
	// Method 2 of data provider with the help of hashmap.	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		//HashMap<String,String> map = new HashMap<String,String>();
		//map.put("email", "pk@automation.com");
		//map.put("password", "Audi@7825");
		//map.put("product", "IPHONE 13 PRO");
		
		//HashMap<String,String> map1 = new HashMap<String,String>();
		//map1.put("email", "shetty@gmail.com");
		//map1.put("password", "Iamking@000");
		//map1.put("product", "ADIDAS ORIGINAL");
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}

	
	}

