package seleniumFrameworkDesign.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import seleniumFrameworkDesign.pageobjects.CartPage;
import seleniumFrameworkDesign.pageobjects.OrdersPage;

public class AbstractComponents {
	WebDriver driver;

	public AbstractComponents(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(css = "button[routerlink='/dashboard/myorders']")
	WebElement ordersButton;
	
	@FindBy(css = "[routerlink*='dashboard/cart']")
	WebElement cartHeader;

	public void waitForElementToAppear(By findBy) {

		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}
	
	public void waitForElementToAppearEle(WebElement ele) {

		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.visibilityOf(ele));

	}

	public void waitForElementToDisAppear(By findBy) throws InterruptedException {
		
		Thread.sleep(2000);
//		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
//		w.until(ExpectedConditions.invisibilityOfElementLocated(findBy));

	}

	public void waitForElementToDisAppearWebEle(WebElement ele) {

		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void waitForElementToBeClickable(By findBy) {
		
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.elementToBeClickable(findBy));
	}
	
	public CartPage goToCartPage() {

//		waitForElementToBeClickable(cartButton);
		cartHeader.click();
		CartPage cp = new CartPage(driver);
//		waitForElementToAppear(cartTitle);
		return cp;

	}
	
	public OrdersPage goToOrdersPage() {

		ordersButton.click();
//		waitForElementToAppear(ordersTitle);
		OrdersPage op = new OrdersPage(driver);
		return op;

	}
	


}
