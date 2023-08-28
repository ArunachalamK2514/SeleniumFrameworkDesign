package seleniumFrameworkDesign.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class LoginPage extends AbstractComponents {

	WebDriver driver;

	public LoginPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userName;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement loginButton;
	
	@FindBy(xpath = "//*[@id=\"toast-container\"]/div")
	WebElement incorrectPasswordMessage;

	public void goTo(String url) {

		driver.get(url);

	}

	public ProductCatalogue loginToApplication(String email, String password) {

		userName.sendKeys(email);
		userPassword.sendKeys(password);
		loginButton.click();
		return new ProductCatalogue(driver);

	}
	
	public String loginErrorMessage() {
		waitForElementToAppearEle(incorrectPasswordMessage);
		return incorrectPasswordMessage.getText();
	}

}
