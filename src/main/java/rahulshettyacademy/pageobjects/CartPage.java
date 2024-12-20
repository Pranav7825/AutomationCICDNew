package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	
	// List<WebElement> cartProducts = driver.findElements(By.xpath("//*[@class ='cartSection']/h3"));
	// boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	
	@FindBy(xpath="//*[@class ='cartSection']/h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutlEle;
	
	public boolean VerifyProductDisplay(String productName)
	{
		boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	// driver.findElement(By.cssSelector(".totalRow button")).click();
	public CheckoutPage goToCheckout()
	{
		checkOutlEle.click();
		return new CheckoutPage(driver);

	}
	
	

}
