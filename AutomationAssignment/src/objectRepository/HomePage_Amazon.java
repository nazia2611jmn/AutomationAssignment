package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class HomePage_Amazon 

{
WebDriver driver;
@FindBy (id="twotabsearchtextbox")
private WebElement searchTextBox;

@FindBy (xpath="//h2/a/span[text()='Apple iPhone XR (64GB) - Yellow']/../../../../../../../div[2]/div/div/div/div/div/a/span[@class='a-price']/span[2]/span[@class='a-price-whole']")
private WebElement iphoneprice_Amazon;


public HomePage_Amazon(WebDriver driver)
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
	String price_amazon=iphoneprice_Amazon.getText().replace(",","");
	int priceIphone_amazon=Integer.parseInt(price_amazon);
	Reporter.log("price of Iphone on Amazon : "+priceIphone_amazon,true);
    return priceIphone_amazon;
}
}
