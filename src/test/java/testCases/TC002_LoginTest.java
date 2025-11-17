package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups = { "Sanity", "Master" })
	public void validateLogin() {

		logger.info("****** Starting Login test *****");

		try {

			logger.info("Click on Login link");
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			logger.info("Entering customer details");
			LoginPage lp = new LoginPage(driver);
			lp.enterEmail(p.getProperty("email"));
			lp.enterPassword(p.getProperty("password"));
			lp.clickLogin();

			logger.info("Validating login");
			MyAccountPage myacc = new MyAccountPage(driver);
			boolean status = myacc.validateLogin();
			Assert.assertEquals(status, true, "Test Failed");

		} catch (Exception e) {

			Assert.fail();
		}

		logger.info("***** Finished Login Test *****");
	}

}
