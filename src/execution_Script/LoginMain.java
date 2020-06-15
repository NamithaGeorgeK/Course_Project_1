package execution_Script;

import org.testng.annotations.Test;

import data_store.DataFile;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;

public class LoginMain 
{
		WebDriver driver;
		String baseURL ="http://hms.techcanvass.co";
  @Test
  public void login_Multiple_users() 
  {
		DataFile df=new DataFile();
		df.FileRead();
		for(int i=0;i<3;i++) {
		String userID=df.userName.get(i);
		String password=df.password.get(i);
		String loginBox="txtUserName";
		WebElement loginID= driver.findElement(By.id(loginBox));
		loginID.sendKeys(userID);
		String passwordBox="txtPassword";
		WebElement passWord= driver.findElement(By.id(passwordBox));
		passWord.sendKeys(password);
		String loginButton="//*[@id=\"LoginButton\"]";
		WebElement loginBtn= driver.findElement(By.xpath(loginButton));
		loginBtn.click();
	  
	if(isAlert())
	{
		String errorMessage = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		System.out.println(userID+", Your login failed! " + errorMessage);		
	}
	else 
	{			
	  String messageDisplayed="//*[@id=\"aspnetForm\"]/table/tbody/tr[3]/td/table/tbody/tr[1]/td/table/tbody/tr[1]/td";
	  String welcomeMessage= driver.findElement(By.xpath(messageDisplayed)).getText();
	  System.out.println(welcomeMessage);	
	  String logoutButton="#ctl00_ImageButton1";
	  WebElement logoutBtn=driver.findElement(By.cssSelector(logoutButton));
	  logoutBtn.click();
	  
		}
	 }
  }
  
  public  boolean isAlert() 
  {		try 
		{
			driver.switchTo().alert();
			return true;
		} 
		catch (NoAlertPresentException Ex)
		{
			return false;
		}
		catch(UnhandledAlertException uae) 
		{
			System.out.println("Unhandled Alert Exception caught");
			return true;			
		}
	}

  @BeforeMethod
  public void beforeMethod()
  {
	  System.setProperty("webdriver.chrome.driver", "external_resources/chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.get(baseURL);
  }

  @AfterMethod
  public void afterMethod()
  {
	  driver.close();
  }

}
