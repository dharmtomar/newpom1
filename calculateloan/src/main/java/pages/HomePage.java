package pages;



public class HomePage extends BasePage {


	public SigninPage goToSignin() {
		click("homepagesignin_XPATH");
		return new SigninPage();
	}
	
}
