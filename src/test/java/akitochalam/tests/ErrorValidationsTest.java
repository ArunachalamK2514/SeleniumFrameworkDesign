package akitochalam.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import akitochalam.TestComponents.BaseTest;
import akitochalam.TestComponents.Retry;
import seleniumFrameworkDesign.pageobjects.CartPage;
import seleniumFrameworkDesign.pageobjects.ConfirmationPage;
import seleniumFrameworkDesign.pageobjects.PaymentPage;
import seleniumFrameworkDesign.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	public ErrorValidationsTest() throws IOException {
		super();
	}

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws IOException {

		lp.loginToApplication("seleniumpractice@gmail.com", "Seleniumpract@13");
		String actualErrorMessage = lp.loginErrorMessage();
		Assert.assertEquals(actualErrorMessage, "Incorrect email password.");

	}

	@Test(groups = { "ErrorHandling" })
	public void ProductErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";

		ProductCatalogue pc = lp.loginToApplication("seleniumpractice@gmail.com", "Seleniumpractice@13");

		// Adding items to the cart
		pc.addProductToCart(productName);

		CartPage cp = pc.goToCartPage();
		Boolean match = cp.validateCartItems("Zara cort 33"); // Checking for wrong product on purpose.
		Assert.assertFalse(match);

	}
}
