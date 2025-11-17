package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	// constructor
	public AccountRegistrationPage(WebDriver driver) {

		super(driver);
	}

	// locators
	@FindBy(id = "input-firstname")
	WebElement txtFirstname;
	@FindBy(id = "input-lastname")
	WebElement txtLastname;
	@FindBy(id = "input-email")
	WebElement txtEmail;
	@FindBy(id = "input-telephone")
	WebElement txtTelephone;
	@FindBy(id = "input-password")
	WebElement txtPassword;
	@FindBy(id = "input-confirm")
	WebElement txtConfirmPassword;
	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkPolicy;
	@FindBy(xpath = "//input[@type='submit']")
	WebElement btnContinue;
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;

	// action methods
	public void setFirstname(String firstName) {
		txtFirstname.sendKeys(firstName);
	}

	public void setLastName(String lastName) {
		txtLastname.sendKeys(lastName);
	}

	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}

	public void setPhone(String phone) {
		txtTelephone.sendKeys(phone);
	}

	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}

	public void setConfirmPassword(String pwd) {
		txtConfirmPassword.sendKeys(pwd);
	}

	public void clickPrivacyPolicy() {
		chkPolicy.click();
	}

	public void clickContinue() {
		btnContinue.click();
	}

	public String getConfirmationMsg() {
		try {
			return (msgConfirmation.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}

}
