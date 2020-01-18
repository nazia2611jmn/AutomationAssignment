package genericLib;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass 

{
public static WebDriver driver;	
static String XlPath="/Seleniumautomation/testData/ConfigFile.xlsx";
static String sheetName="Sheet1";
FileUtils fUtils=new FileUtils();
WebDriverUtils wUtils=new WebDriverUtils();

@BeforeClass
public void launchBrowser() throws Throwable
{
	//String browserName=fUtils.getMapData1("browser", XlPath, sheetName);
	//String url=fUtils.getMapData1("url1", XlPath, sheetName);
	Reporter.log("Launching the Browser",true);
	Properties pObj=fUtils.getPropertiFileObject();
	String browserName=pObj.getProperty("browser");
	String url=pObj.getProperty("url1");
	if(browserName.equals("chrome"))
	{
	System.setProperty("webdriver.chrome.driver","C:\\Users\\samir mallick\\eclipse-workspace\\Seleniumautomation\\Drivers\\chromedriver.exe");	
	driver=new ChromeDriver();	
	driver.manage().window().maximize();
	wUtils.implicitWait(driver);
	driver.get(url);

	}
	if(browserName.equals("firefox"))
	{
	System.setProperty("webdriver.chrome.driver","C:\\Users\\samir mallick\\eclipse-workspace\\Seleniumautomation\\Drivers\\firefoxdriver.exe");	
	driver=new FirefoxDriver();	
	driver.manage().window().maximize();
	wUtils.implicitWait(driver);
	driver.get(url);

	}
	if(browserName.equals("ie"))
	{
	System.setProperty("webdriver.chrome.driver","C:\\Users\\samir mallick\\eclipse-workspace\\Seleniumautomation\\Drivers\\internetExplorerdriver.exe");	
	driver=new InternetExplorerDriver();
	driver.manage().window().maximize();
	wUtils.implicitWait(driver);
	driver.get(url);

	}
}
	@AfterClass
	public void closeBrowser()
	{
	Reporter.log("Closing the Browser",true);	
		driver.close();
	}
		
	





}
