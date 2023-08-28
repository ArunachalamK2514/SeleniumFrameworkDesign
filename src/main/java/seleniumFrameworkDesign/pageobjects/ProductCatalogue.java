package seleniumFrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import seleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By prodList = By.cssSelector("div.card-body");
	By addToCart = By.cssSelector(".fa-shopping-cart");
	By toastMessage = By.id("toast-container");
	By spinner = By.cssSelector(".ngx-spinner-overlay");

	@FindBy(css = "div.card-body")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement animation;

	public List<WebElement> getProductsList() {

		waitForElementToAppear(prodList);
		return products;

	}

	public WebElement getProductByName(String productName) {

		WebElement prod = getProductsList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		return prod;
	}

	public void addProductToCart(String productName) throws InterruptedException {

		WebElement prod = getProductByName(productName);
		if (prod != null) {
			prod.findElement(addToCart).click();
			System.out.println("Item Added to the cart");
//			waitForElementToAppear(toastMessage);
//			waitForElementToDisAppearWebEle(animation);
			waitForElementToDisAppear(spinner);
			

		} else {
			System.out.println("Item not present in the page");
		}
		
//		return new CartPage(driver);		
	}

}
