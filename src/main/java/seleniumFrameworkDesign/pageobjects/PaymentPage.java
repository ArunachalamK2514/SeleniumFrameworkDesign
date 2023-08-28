package seleniumFrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class PaymentPage extends AbstractComponents {
	WebDriver driver;

	public PaymentPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css = ".form-group Section button")
	List<WebElement> countries;
	
	@FindBy(css = ".action__submit")
	WebElement placeOrderButton;
	
	

	public ConfirmationPage selectCountryandPlaceOrder(String countryName) {

		country.sendKeys(countryName);
		countries.stream().filter(con -> con.getText().equalsIgnoreCase(countryName)).findFirst().orElse(null).click();
		placeOrderButton.click();
		return new ConfirmationPage(driver);

	}

}
