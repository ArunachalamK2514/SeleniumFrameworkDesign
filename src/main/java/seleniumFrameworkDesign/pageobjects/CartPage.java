package seleniumFrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	WebDriver driver;

	public CartPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	By cartButton = By.cssSelector("[routerlink*='dashboard/cart']");
	By cartTitle = By.cssSelector(".heading.cf");


	@FindBy(css = ".cartSection h3")
	List<WebElement> cartItems;

	@FindBy(css = ".totalRow button")
	WebElement checkOutButton;



	public boolean validateCartItems(String productName) {

		return cartItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase(productName));

	}

	public PaymentPage checkOut() {

		checkOutButton.click();
		return new PaymentPage(driver);

	}

}
