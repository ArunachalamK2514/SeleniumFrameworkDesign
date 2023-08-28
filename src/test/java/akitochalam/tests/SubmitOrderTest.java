package akitochalam.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import akitochalam.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumFrameworkDesign.pageobjects.CartPage;
import seleniumFrameworkDesign.pageobjects.ConfirmationPage;
import seleniumFrameworkDesign.pageobjects.LoginPage;
import seleniumFrameworkDesign.pageobjects.OrdersPage;
import seleniumFrameworkDesign.pageobjects.PaymentPage;
import seleniumFrameworkDesign.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
//	String productName = "ZARA COAT 3";
	String jsonFilePath = System.getProperty("user.dir") + "\\src\\test\\java\\akitochalam\\Data\\ProductOrder.json";

	public SubmitOrderTest() throws IOException {
		super();
	}

//	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		String country = "India";

		ProductCatalogue pc = lp.loginToApplication(input.get("username"), input.get("password"));

		// Adding items to the cart
		pc.addProductToCart(input.get("productName"));

		// Navigating to Cart Page, validating the items and checking out
		CartPage cp = pc.goToCartPage();
		Boolean match = cp.validateCartItems(input.get("productName"));
		Assert.assertTrue(match);
		PaymentPage pp = cp.checkOut();

		// Selecting Country and placing the order
		ConfirmationPage cfp = pp.selectCountryandPlaceOrder(country);

		// Confirmation Screen
		String orderConfirmation = cfp.getOrderConfirmation();
		Assert.assertEquals(orderConfirmation, "THANKYOU FOR THE ORDER.");
		String orderNumber = cfp.getOrderNumber().split(" ")[1];
		System.out.println("Order Number is: " + orderNumber);

	}

//	@Test(dependsOnMethods = { "submitOrder" }, dataProvider = "getData", groups = { "Purchase" })
	public void OrderHistory(HashMap<String, String> input) {

		ProductCatalogue pc = lp.loginToApplication(input.get("username"), input.get("password"));
		OrdersPage op = pc.goToOrdersPage();
		Boolean match = op.validateOrderItems(input.get("productName"));
		Assert.assertTrue(match);

	}
	
	@Test
	public void PrintingExcelValues() throws IOException {
		
		ArrayList<String> data = getExcelData("TestData", "Purchase");
		for( int i =0; i< data.size(); i++) {
			System.out.println(data.get(i));
		}
	}
	

	// Commenting the below and using the HashMap concept in next method to
	// implement the same functionality
//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] { { "seleniumpractice@gmail.com", "Seleniumpractice@13", "ZARA COAT 3" },
//				{ "practiceselenium@gmail.com", "Practiceselenium@13", "ADIDAS ORIGINAL" } };
//	}
	
	

	// Commenting the below and using the HashMap concept in next method to
	// implement the same functionality
//	@DataProvider
//	public Object[][] getData() {
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("username", "seleniumpractice@gmail.com");
//		map1.put("password", "Seleniumpractice@13");
//		map1.put("productName", "ZARA COAT 3");
//
//		HashMap<String, String> map2 = new HashMap<String, String>();
//		map2.put("username", "practiceselenium@gmail.com");
//		map2.put("password", "Practiceselenium@13");
//		map2.put("productName", "ADIDAS ORIGINAL");
//
//		return new Object[][] { { map1 }, { map2 } };
//
//	}
	
//	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonDataToMap(jsonFilePath);
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}
