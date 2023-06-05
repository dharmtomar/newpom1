package testpages;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SigninPage;
import webtest.BaseTest;

public class SigninTest {


//public WebDriver driver;

	@Test
	public void doLogin() {
	HomePage home=new HomePage();
	SigninPage signpage= home.goToSignin();
	try {
		signpage.signin();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
