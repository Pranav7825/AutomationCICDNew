package rahulshettyacademy.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;


public class StandAloneTest2 {

	public static void main(String[] args) throws InterruptedException {
	
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("pk@automation.com");
		driver.findElement(By.id("userPassword")).sendKeys("Audi@7825");
		driver.findElement(By.id("login")).click();
		Thread.sleep(3000);
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		// List<WebElement> buttons = driver.findElements(By.cssSelector(".card div button:last-of-type"));
		
		for (int i = 0; i < products.size(); i++)
		{
			WebElement product = products.get(i);
			if (product.getText().equalsIgnoreCase("IPHONE 13 PRO"))
			{
				driver.findElement(By.cssSelector(".card div button:last-of-type")).click();
			}
		}
		
		

		
		
		
		
	}
	}

