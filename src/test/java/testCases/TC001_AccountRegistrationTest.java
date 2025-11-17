package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups = { "Regression", "Master" })
	public void verify_account_registration() {

		logger.info("Starting TC");

		try {

			logger.info("On the Home page");
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickRegister();

			logger.info("Entering customer detials");
			AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
			regPage.setFirstname(randomString().toUpperCase());
			regPage.setLastName(randomString().toUpperCase());
			regPage.setEmail(randomString() + "@gmail.com");
			regPage.setPhone(randomNumber());

			String password = randomAlphaNumeric();
			regPage.setPassword(password);
			regPage.setConfirmPassword(password);

			regPage.clickPrivacyPolicy();
			regPage.clickContinue();

			logger.info("Validating confirm msg");
			String confirmMsg = regPage.getConfirmationMsg();
			if (confirmMsg.equals("Your Account Has Been Created!")) {

				Assert.assertTrue(true);

			} else {

				logger.error("Test failed");
				logger.debug("Debug logs: ");
				Assert.assertTrue(false);
			}

		} catch (Exception e) {

			Assert.fail();
		}

		logger.info("TC Finished");
	}
}
