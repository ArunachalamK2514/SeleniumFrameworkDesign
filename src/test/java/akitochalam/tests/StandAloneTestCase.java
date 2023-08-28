package akitochalam.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTestCase {

	public static void main(String[] args) throws InterruptedException {

		String productName = "ZARA COAT 3";
		String contry = "India";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();

		driver.get("https://rahulshettyacademy.com/client/");

		// Login
		driver.findElement(By.id("userEmail")).sendKeys("seleniumpractice@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Seleniumpractice@13");
		driver.findElement(By.id("login")).click();

		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("res")));

		List<WebElement> products = driver.findElements(By.cssSelector("div.card-body"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);

		if (prod != null) {
			prod.findElement(By.cssSelector(".fa-shopping-cart")).click();
			System.out.println("Item Added to the cart");
		} else {
			System.out.println("Item not present in the page");
		}

//		w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));
//		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ngx-spinner-overlay"))));
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
//		String confirmation = w.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container"))).getText();
//		Assert.assertEquals(confirmation, "Product Added To Cart");
//		Thread.sleep(3000);
//		w.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[routerlink*='dashboard/cart']")));
		driver.findElement(By.cssSelector("[routerlink*='dashboard/cart']")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".heading.cf")));

		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match = cartItems.stream()
				.anyMatch(item -> item.getText().equalsIgnoreCase(productName));
		
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys(contry);
		List <WebElement> contries = driver.findElements(By.cssSelector(".form-group Section button"));
		
		contries.stream().filter(con -> con.getText().equalsIgnoreCase(contry)).findFirst().orElse(null).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String orderConfirmation = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals(orderConfirmation, "THANKYOU FOR THE ORDER.");
		String orderNumber = driver.findElement(By.cssSelector(".ng-star-inserted td label")).getText();
		System.out.println("The order number is " + orderNumber);

	}
}
