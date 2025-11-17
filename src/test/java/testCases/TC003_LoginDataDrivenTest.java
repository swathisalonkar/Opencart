package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDataDrivenTest extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "Datadriven")
	public void veryfy_loginDDT(String email, String pwd, String exp) {

		logger.info("***** Starting Login DDT *****");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			LoginPage lp = new LoginPage(driver);
			lp.enterEmail(email);
			lp.enterPassword(pwd);
			lp.clickLogin();

			MyAccountPage myacc = new MyAccountPage(driver);
			boolean status = myacc.validateLogin();

			if (exp.equalsIgnoreCase("Valid")) {
				if (status == true) {
					myacc.clickLogout();
					Assert.assertTrue(true);
				} else
					Assert.assertTrue(false);
			} else if (exp.equalsIgnoreCase("Invalid")) {
				if (status == true) {
					myacc.clickLogout();
					Assert.assertTrue(false);
				} else
					Assert.assertTrue(true);
			}

		} catch (Exception e) {

			Assert.fail();
		}

		logger.info("***** Finished Login DDT *****");

	}

}
