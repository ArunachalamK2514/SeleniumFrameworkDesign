package akitochalam.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.schemas.office.visio.x2012.main.CellType;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumFrameworkDesign.pageobjects.LoginPage;

public class BaseTest {
	public WebDriver driver;
	public Properties prop;
	public LoginPage lp;
	String browserName;
	String pageURL;

	public BaseTest() throws IOException {

		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\seleniumFrameworkDesign\\resources\\GlobalData.properties");
		prop.load(fis);
		browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		pageURL = prop.getProperty("url");

	}

	public WebDriver InitializeDriver() throws IOException {

		if (browserName.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
	}

	public List<HashMap<String, String>> getJsonDataToMap(String jsonFilePath) throws IOException {

		// Read JSON data to a string
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

		// Convert String to HashMap -> add the Jakson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
		// Returned data will be a list like - {map1, map2}
	}

	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationPath = System.getProperty("user.dir") + "\\Reports\\" + testCaseName
				+ System.currentTimeMillis() + ".png";
		FileUtils.copyFile(source, new File(destinationPath));
		return System.getProperty("user.dir") + "\\Reports\\" + testCaseName + System.currentTimeMillis() + ".png";
	}

	public ArrayList<String> getExcelData(String sheetName, String testCaseName) throws IOException {

		ArrayList<String> arr = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\seleniumFrameworkDesign\\resources\\TestData_Excel.xlsx");

		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int numSheets = workbook.getNumberOfSheets();
		for (int i = 0; i < numSheets; i++) {

			if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {

				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rows = sheet.iterator();

				Row firstRow = rows.next();
				Iterator<Cell> cells = firstRow.cellIterator();

				while (cells.hasNext()) {

					Cell cell = cells.next();
					if (cell.getStringCellValue().equalsIgnoreCase("TestCases")) {

						int columnNumber = cell.getColumnIndex();

						while (rows.hasNext()) {

							Row currentRow = rows.next();

							if (currentRow.getCell(columnNumber).getStringCellValue().equalsIgnoreCase(testCaseName)) {

								Iterator<Cell> ce = currentRow.iterator();

								while (ce.hasNext()) {
									Cell currentCell = ce.next();
									if (currentCell.getCellType() == org.apache.poi.ss.usermodel.CellType.STRING) {
										arr.add(currentCell.getStringCellValue());
									}
									else {
										
										arr.add(NumberToTextConverter.toText(currentCell.getNumericCellValue()));
									}

									
								}

							}

						}

					}

				}

			}

		}
		return arr;

	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws IOException {

		driver = InitializeDriver();
		lp = new LoginPage(driver);
		lp.goTo(pageURL);
		return lp;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

}
