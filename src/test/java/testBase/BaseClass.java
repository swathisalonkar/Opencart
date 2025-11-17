package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass(groups = { "Sanity", "Regression", "Datadriven", "Master" })
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {

		// loading config.properties
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);

		// initializing log4j2 file
		logger = LogManager.getLogger(this.getClass());

		// for remote execution
		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {

			DesiredCapabilities capability = new DesiredCapabilities();

			// for choosing OS
			switch (os.toLowerCase()) {
			case "windows":
				capability.setPlatform(Platform.WIN11);
				break;
			case "mac":
				capability.setPlatform(Platform.MAC);
				break;
			case "linux":
				capability.setPlatform(Platform.LINUX);
				break;
			default:
				System.out.println("Invalid OS");
				return;
			}

			// for choosing browser
			switch (br.toLowerCase()) {
			case "chrome":
				capability.setBrowserName("chrome");
				break;
			case "edge":
				capability.setBrowserName("MicrosoftEdge");
				break;
			case "firefox":
				capability.setBrowserName("firefox");
				break;
			default:
				System.out.println("Invalid Browser");
				return;
			}

			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"), capability);
		}

		// for local execution
		if (p.getProperty("execution_env").equalsIgnoreCase("local")) {

			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			default:
				System.out.println("Invalid Browser");
				return;
			}
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL")); // reading URL from properties file
		driver.manage().window().maximize();
	}

	@AfterClass(groups = { "Sanity", "Regression", "Datadriven", "Master" })
	public void teardown() {

		driver.quit();
	}

	public String randomString() {

		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomNumber() {

		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}

	public String randomAlphaNumeric() {

		String generatedPwd = RandomStringUtils.randomAlphanumeric(12);
		return generatedPwd;
	}

	public String captureScreen(String tname) throws IOException {

		String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timestamp + ".png";
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;

	}

}
