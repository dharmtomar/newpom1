package pages;

public class SigninPage extends BasePage{

	public ZohoAppPage signin() throws InterruptedException {
		type("loginemail_XPATH","dharmiqualisys@gmail.com");
		click("nextbutton_XPATH");
		Thread.sleep(8000);
		type("loginpass_ID","Dharm@54321");
		click("signinbutton_XPATH");
		return new ZohoAppPage();		
	}
}
