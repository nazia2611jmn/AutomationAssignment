package genericLib;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtils 

{

/* Implicitly Wait: to load complete page */

	public void implicitWait(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}
	
	
/* Explicitly wait : wait for the element to load */
	
	public void ExplicitWait(WebDriver driver,String xpath)
	{
	WebDriverWait wait=new WebDriverWait(driver,10);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		
		
	}
	
/* Custom Wait : Wait for element to completly loan in GUI */
	public void customWait(WebElement wb)
	{
		
	int count=0;
	while(count<20)
	{
	try
	{
		wb.isDisplayed();
		break;
	}catch(Throwable t)
	{
		System.out.println("Handled Exception and continue");
		count++;
	}
	}
	}
		
	
		
	}

