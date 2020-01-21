package objectRepository;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import genericLib.WebDriverUtils;

public class HomePage_TripAdvisor 

{
	WebDriver driver;
	@FindBy (xpath="(//*[text()='Search'])[1]")
	private WebElement search;

	@FindBy (xpath="//input[@id='mainSearch']")
	private WebElement searchTextBox;

	@FindBy (xpath="//*[text()='Where to?']")
	private WebElement tripDestination;

	@FindBy (xpath="(//div[@class='ui_column is-9-desktop is-8-mobile is-9-tablet content-block-column']/div/div)[1]")
	private WebElement selectFirstOption;
	
	@FindBy (xpath="//a[text()='Write a review']")
	private WebElement clickWriteReview;
	
	
	@FindBy (xpath="//span[@id='bubble_rating']")
	private WebElement bubbleRating;
	
	@FindBy (xpath="//input[@id='ReviewTitle']")
	private WebElement reviewTitile;
	
	@FindBy (xpath="//*[@id='ReviewText']")
	private WebElement review;

	@FindBy (xpath="//input[@id='noFraud']")
	private WebElement reviewCheckbox;
	
	@FindBy (xpath="//div[@id='SUBMIT']")
	private WebElement submit;
	
	@FindBy (xpath="//*[text()='Solo']")
	private WebElement tripType;
	
	@FindBy (xpath="//select[@id='trip_date_month_year']")
	private WebElement travelMonthAndYear;
	
	@FindBy (xpath="//span[@class='ui_button primary w100p regEmailContinue newRegFormButtonStyles']")
	private WebElement clickOnEmailOption;
	
	@FindBy (partialLinkText="//span[text()='Log in']")
	private WebElement loginLink;
	
	
	@FindBy (partialLinkText="//input[@id='regSignIn.email']")
	private WebElement userNametextBox;
	
	@FindBy (partialLinkText="//input[@id='regSignIn.password']")
	private WebElement passwordTextBox;
	
	@FindBy (partialLinkText="//div[text()='Log in']")
	private WebElement login;
	
	
	
	@FindBy (partialLinkText="//div[@class='rc-anchor-logo-text']")
	private WebElement captchaLogo;
	
	@FindBy (partialLinkText="//div[@class='rc-anchor-logo-portrait']/div[2][text()='reCAPTCHA']")
	private WebElement captcha;
	
	WebDriverUtils wUtils=new WebDriverUtils();
	
	public HomePage_TripAdvisor(WebDriver driver)
	{
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}

	public void tripDestination(String destination)
	{
		search.click();
		searchTextBox.sendKeys(destination,Keys.ENTER);
		selectFirstOption.click();
		Reporter.log("The trip destination is :"+ destination ,true);
	}
	
	public void clickOnWritereviewSection(String reviewTitle,String reviewText) throws Exception
	{
		
		boolean flag=false;
		ArrayList<String>win=new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(win.get(1));
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("window.scrollBy(0, 1100)");
		String xpath="//a[text()='Write a review']";
		/* wait for element to completely load in GUI */
		wUtils.ExplicitWait(driver, xpath);
		
		clickWriteReview.click();
		Thread.sleep(3000);
		ArrayList<String>wins=new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(wins.get(2));
		Actions act=new Actions(driver);
		act.moveToElement(bubbleRating).perform();
		bubbleRating.click();
		reviewTitile.sendKeys(reviewTitle);
		review.sendKeys(reviewText);
		Reporter.log("Review is entered in the review text box" ,true);
		tripType.click();
		Select sel= new Select(travelMonthAndYear);
		sel.selectByIndex(1);
		reviewCheckbox.click();
		submit.click();
		/* switch to iframe */
		driver.switchTo().frame("overlayRegFrame");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[text()='Continue with email']")).click();
		Thread.sleep(5000);
		String captchaL="//div[@class='rc-anchor-logo-text']";
		wUtils.ExplicitWait(driver, captchaL);
		if(captchaLogo.getText().equals("reCAPTCHA"))
		{
			Reporter.log("Captcha is available hence The WriteReviewTripAdvisor test case is not completely automatable as we can't automate captcha or otp related test cases",true);
		   flag=true;
		}
		else
		{
			Reporter.log("No Captcha is available hence we can continue with the test case",true);
		}
		
		if(flag==true)
		{
			Assert.fail();
		}

		
	}
}
