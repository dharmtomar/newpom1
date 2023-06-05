package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Properties;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.Status;

import extentlisteners.ExtentListeners;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExcelReader;

public class BasePage {

	public static WebDriver driver;
	private static Properties or = new Properties();
	private static Properties config = new Properties();
	private static FileInputStream fis;
	private static Logger log = Logger.getLogger(BasePage.class);
	public static ExcelReader excel = new ExcelReader(".//src//test//resources//excel//testdata.xlsx");
	//public static MonitoringMail mail = new MonitoringMail();
	public static WebDriverWait wait;
	public static WebElement dropdown;
	
	//------keywords-------
	public void click(String locaterkey) {
		try {
			if(locaterkey.endsWith("_ID")) {
				driver.findElement(By.id(or.getProperty(locaterkey))).click();
			}
			else if(locaterkey.endsWith("_NAME")) {
				driver.findElement(By.name(or.getProperty(locaterkey))).click();
			}
			else if(locaterkey.endsWith("_XPATH")) {
				driver.findElement(By.xpath(or.getProperty(locaterkey))).click();
			}
			else if(locaterkey.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(or.getProperty(locaterkey))).click();
			}
			/*log.info("click an element"+locaterkey);
			ExtentListeners.test.log(Status.INFO," Clicking the element "+locaterkey);
			
		} catch (Throwable t) {
			log.error(" Error while clicking on element: "+locaterkey+" Error message "+t.getMessage());
			ExtentListeners.test.log(Status.FAIL,"Error while Clicking on an Element : " + locaterkey + " error message : " + t.getMessage());
			Assert.fail(t.getMessage());*/
			
			log.info("Clicking on an Element : " + locaterkey);
			ExtentListeners.test.log(Status.INFO, "Clicking on an Element : " + locaterkey);
		} catch (Throwable t) {

			log.error("Error while Clicking on an Element : " + locaterkey + " error message : " + t.getMessage());
			//ExtentListeners.test.log(Status.FAIL,"Error while Clicking on an Element : " + locaterkey + " error message : " + t.getMessage());
			//Assert.fail(t.getMessage());
		}
	}
	
	public static boolean isElementPresent(String locaterkey) {
	try {
		if(locaterkey.endsWith("_ID")) {
			driver.findElement(By.id(or.getProperty(locaterkey)));
		}
		else if(locaterkey.endsWith("_NAME")) {
			driver.findElement(By.name(or.getProperty(locaterkey)));
		}
		else if(locaterkey.endsWith("_XPATH")) {
			driver.findElement(By.xpath(or.getProperty(locaterkey)));
		}
		else if(locaterkey.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(or.getProperty(locaterkey)));
		}
	} catch (Throwable t) {
		log.error(" Element not found "+locaterkey+" Error message "+t.getMessage());
		//ExtentListeners.test.log(Status.FAIL, " Element not found "+locaterkey +" Error message: "+t.getMessage());

	}
	log.info(" Finding an element"+locaterkey);
	ExtentListeners.test.log(Status.INFO," Finding an element "+locaterkey);
	return true;
}
	
	public void type(String locaterkey,String value) {
		try {
			if(locaterkey.endsWith("_ID")) {
				driver.findElement(By.id(or.getProperty(locaterkey))).sendKeys(value);
			}
			else if(locaterkey.endsWith("_NAME")) {
				driver.findElement(By.name(or.getProperty(locaterkey))).sendKeys(value);
			}
			else if(locaterkey.endsWith("_XPATH")) {
				driver.findElement(By.xpath(or.getProperty(locaterkey))).sendKeys(value);
			}
			else if(locaterkey.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(or.getProperty(locaterkey))).sendKeys(value);
			}
			log.info(" Typing in to an element "+locaterkey +" Entered value is "+value);
			ExtentListeners.test.log(Status.INFO, " Typing in to en element "+locaterkey +" Entered value is "+value);
		} catch (Throwable t) {
			log.error(" Error while typing into element "+locaterkey+" Error message "+t.getMessage());
			//ExtentListeners.test.log(Status.FAIL, " Error while typing into element "+locaterkey +" Error message: "+t.getMessage());
			//Assert.fail(t.getMessage());
		}
	}
	
	public void select(String locaterkey,String value) {
		try {
			if(locaterkey.endsWith("_ID")) {
				dropdown= driver.findElement(By.id(or.getProperty(locaterkey)));
			}
			else if(locaterkey.endsWith("_NAME")) {
				dropdown=driver.findElement(By.name(or.getProperty(locaterkey)));
			}
			else if(locaterkey.endsWith("_XPATH")) {
				dropdown=driver.findElement(By.xpath(or.getProperty(locaterkey)));
			}
			else if(locaterkey.endsWith("_CSS")) {
				dropdown=driver.findElement(By.cssSelector(or.getProperty(locaterkey)));
			}
			log.info(" Selecting an element "+locaterkey +" Selected element is "+value);
			ExtentListeners.test.log(Status.INFO, " Selecting an element "+locaterkey +" Selected element is "+value);
		} catch (Throwable t) {
			log.error(" Error while selecting an element "+locaterkey+" Error message "+t.getMessage());
			//ExtentListeners.test.log(Status.FAIL, " Error while selecting an element "+locaterkey +" Error message: "+t.getMessage());
			//Assert.fail(t.getMessage());
		}
	}
	
	public BasePage() {
		if(driver==null) {
		PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");
		
		try {
			fis=new FileInputStream("./src/test/resources/properties/config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			config.load(fis);
			log.info(" config property loaded ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			fis=new FileInputStream("./src/test/resources/properties/or.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			or.load(fis);
			log.info(" or property loaded ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(config.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			log.info(" Launching chrome ");
		}
		else if(config.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			log.info(" Launching firefox ");
		}
		driver.get(config.getProperty("testurl"));
		log.info(" Navigating to "+config.getProperty("testurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicitwait"))));
		wait=new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(config.getProperty("explicitwait"))));
		}
	}
	//@AfterSuite
	public void teardown() {
		//driver.quit();
		log.info(" Test execution completed ");
	}
	
	/*public static void takescreenshot() {
		String user_dir=System.getProperty("user.dir");
		String customDir="\\Screenshots\\";
		//System.out.println("this is user dir"+System.getProperty("user.dir"));
		String scshotname=user_dir + customDir  +new SimpleDateFormat("mmddhhss").format(Calendar.getInstance().getTime()) +"-" +".png";
		//System.out.println("this is screenshot name"+scshotname);
    	File scrfile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	File destination= new File(scshotname);
       try {
			FileUtils.copyFile(scrfile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
}
