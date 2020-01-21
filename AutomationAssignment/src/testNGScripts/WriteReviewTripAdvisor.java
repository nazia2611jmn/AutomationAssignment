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
import genericLib.WebDriverUtils;
import objectRepository.HomePage_Amazon;
import objectRepository.HomePage_Flipkart;
import objectRepository.HomePage_TripAdvisor;


public class WriteReviewTripAdvisor extends BaseClass

{
@Test
public void tripToClubMahindra() throws Throwable
{
	FileUtils fUtils=new FileUtils();
	HomePage_TripAdvisor hm=PageFactory.initElements(driver, HomePage_TripAdvisor.class);
	Properties pObj=fUtils.getPropertiFileObject();
	
	/* test data */
	String des=pObj.getProperty("destination");
	String reviewTitle=pObj.getProperty("reviewTitle");
	String review=pObj.getProperty("review");
	Reporter.log("Trip Advisior home page",true);
    hm.tripDestination(des);
    hm.clickOnWritereviewSection(reviewTitle,review);
    
    /* The WriteReviewTripAdvisor test case is not completely automatable as we can't automate captcha or otp related test cases */

 


}
}
