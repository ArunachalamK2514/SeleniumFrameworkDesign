package seleniumFrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents {

	WebDriver driver;

	public OrdersPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	By ordersTitle = By.cssSelector("h1[class='ng-star-inserted']");

	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> orderItems;


	public boolean validateOrderItems(String productName) {

		return orderItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase(productName));

	}

}
