package seleniumFrameworkDesign.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css = ".hero-primary")
	WebElement confirmationMessage;
	
	@FindBy(css = "label[class='ng-star-inserted']")
	WebElement orderNumber;
	
	public String getOrderConfirmation() {
		
		return confirmationMessage.getText();
		
	}
	
public String getOrderNumber() {
		
		return orderNumber.getText();
		
	}
	

}
