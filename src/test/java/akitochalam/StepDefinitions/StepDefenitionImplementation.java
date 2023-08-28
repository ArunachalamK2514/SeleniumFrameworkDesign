package akitochalam.StepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import akitochalam.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seleniumFrameworkDesign.pageobjects.CartPage;
import seleniumFrameworkDesign.pageobjects.ConfirmationPage;
import seleniumFrameworkDesign.pageobjects.LoginPage;
import seleniumFrameworkDesign.pageobjects.PaymentPage;
import seleniumFrameworkDesign.pageobjects.ProductCatalogue;

public class StepDefenitionImplementation extends BaseTest {

	public LoginPage lp;
	public ProductCatalogue pc;
	public ConfirmationPage cfp;

	public StepDefenitionImplementation() throws IOException {
		super();
	}

	@Given("I landed on the Ecommerce website")
	public void I_landed_on_Ecommerce_website() throws IOException {

		lp = launchApplication();
		
	}

	@Given("^I logged in with username (.+) and password (.+)$")
	public void logged_in_username_password(String username, String password) {

		pc = lp.loginToApplication(username, password);
		
	}

	@When("^I add product (.+) to cart$")
	public void add_product_to_cart(String productName) throws InterruptedException {

		pc.addProductToCart(productName);
		
	}

	// Here And and When can be used. When using And make sure the previous step is
	// the corresponding "when"

	@When("^Checkout (.+) and enter (.+) and submit order$")
	public void checkout_and_submit_order(String productName, String country) {

		// Navigating to Cart Page, validating the items and checking out
		CartPage cp = pc.goToCartPage();
		Boolean match = cp.validateCartItems(productName);
		Assert.assertTrue(match);
		PaymentPage pp = cp.checkOut();

		// Selecting Country and placing the order
		cfp = pp.selectCountryandPlaceOrder(country);
		
	}

	@Then("{string} message is displayed on confirmation page")
	public void message_in_confirmation_page(String message) {

		String orderConfirmation = cfp.getOrderConfirmation();
		Assert.assertEquals(orderConfirmation, message);
		String orderNumber = cfp.getOrderNumber().split(" ")[1];
		System.out.println("Order Number is: " + orderNumber);
		driver.close();
		
	}
	
	@Then("{string} message is displayed")
	public void some_message_is_displayed(String errorMessage) {
		
		String actualErrorMessage = lp.loginErrorMessage();
		Assert.assertEquals(actualErrorMessage, errorMessage);
		driver.close();
		
	}

}
