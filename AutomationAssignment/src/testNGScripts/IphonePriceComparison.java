package testNGScripts;

/*
 * Author: Nazia Firdaus
 */

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import genericLib.BaseClass;
import genericLib.FileUtils;
import objectRepository.HomePage_Amazon;
import objectRepository.HomePage_Flipkart;

public class IphonePriceComparison extends BaseClass
{
	
@Test

public void iPhonePrice() throws Throwable
{
FileUtils fUtils=new FileUtils();
HomePage_Amazon hm=PageFactory.initElements(driver, HomePage_Amazon.class);
HomePage_Flipkart hm_fl=PageFactory.initElements(driver, HomePage_Flipkart.class);
Properties pObj=fUtils.getPropertiFileObject();

/*Test data */
String product=pObj.getProperty("product");
String url_Flipkart=pObj.getProperty("url2");

/* step 1: Search product on Amazon */

int IphnPrice_Amazon=hm.checkProductPrice(product);

/* step 2: Use robot class to press Concurrent keys operation to open a new tab{Ctrl+t)  */  
Robot robot = new Robot();                          
robot.keyPress(KeyEvent.VK_CONTROL); 
robot.keyPress(KeyEvent.VK_T); 
robot.keyRelease(KeyEvent.VK_CONTROL); 
robot.keyRelease(KeyEvent.VK_T);

/* step 3: Switch focus to new tab*/
ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
driver.switchTo().window(tabs.get(1));

/* step 4: Launch URL in the new tab*/
driver.get(url_Flipkart);
driver.findElement(By.xpath("//button[text()='✕']")).click();
int IphnPrice_Flipkrt=hm_fl.checkProductPrice(product);

/* step 5: Validation*/
if(IphnPrice_Amazon<IphnPrice_Flipkrt)
{
	Assert.assertTrue(true);
	Reporter.log("price of Iphone is higher on Flipkart compared to Amazon ",true);
	
}
else
	Reporter.log("price of Iphone is higher on Amazon compared to Flipkart ",true);

/* step 6: close the child window*/
driver.close();

/*step 7: switch to parent window */
driver.switchTo().window(tabs.get(0));


	


}
}

