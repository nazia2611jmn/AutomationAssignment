package objectRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;


public class HomePage_Flipkart
{

WebDriver driver;
@FindBy (xpath="//input[@name='q']")
private WebElement searchTextBox;

@FindBy (xpath="//div[text()='Apple iPhone XR (Yellow, 64 GB)']/../following-sibling::div/div/div/div")
private WebElement iphoneprice_Flipkart;

public HomePage_Flipkart(WebDriver driver)
{
this.driver=driver;
PageFactory.initElements(driver, this);
}


public void searchProduct(String product)
{
	searchTextBox.sendKeys(product,Keys.ENTER);
}

public int checkProductPrice(String product)
{
	searchTextBox.sendKeys(product,Keys.ENTER);
	String priceIphone=iphoneprice_Flipkart.getText();
	String priceIphone_f=priceIphone.replace("₹","");
	String priceIphoneFlipkart=priceIphone_f.replace(",","");
	int priceIphone_flipkart=Integer.parseInt(priceIphoneFlipkart);
	Reporter.log("price of Iphone on Flipkart : "+priceIphone_flipkart,true);
    return priceIphone_flipkart;
}






}
