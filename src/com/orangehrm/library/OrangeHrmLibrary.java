package com.orangehrm.library;

import java.awt.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrangeHrmLibrary
{

public static WebDriver driver;
public static String url="http://opensource.demo.orangehrm.com/";
public String expVal,actVal;
public String Uid="Admin";
public String Pwd="admin";
public String fName,lName;
public String empName,uName,Pword,res;



	
public String launchApp(String url)
{
	expVal="LOGIN";
	driver=new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	driver.get(url);
	actVal=driver.findElement(By.id("btnLogin")).getAttribute("value").toUpperCase();
	Assert.assertEquals(actVal, expVal, "Launch App Test Failed");
	if (expVal.equalsIgnoreCase(actVal)) 
	{
		res="Pass";
	} else 
	{
		res="Fail";
	}
		return res;
}
	
public String adminLogin(String Uid,String Pwd)
{
	driver.findElement(By.id("txtUsername")).sendKeys(Uid);
	driver.findElement(By.id("txtPassword")).sendKeys(Pwd);
	driver.findElement(By.id("btnLogin")).click();
	Assert.assertTrue(driver.findElement(By.linkText("Welcome Admin")).isDisplayed(), "Admin Link does not displayed");
	if (driver.findElement(By.linkText("Welcome Admin")).isDisplayed()) 
	{
		res="Pass";
	} else 
	{
		res="Fail";
	}
		return res;
}

public String adminLogout() throws InterruptedException
{

driver.findElement(By.partialLinkText("Welcome")).click();
Thread.sleep(5000);
driver.findElement(By.linkText("Logout")).click();
Assert.assertTrue(driver.findElement(By.id("btnLogin")).isDisplayed(),"Admin Logout Failed");
if (driver.findElement(By.id("btnLogin")).isDisplayed()) 
{
	res="Pass";
} else 
{
	res="Fail";
}
	return res;
}
	

public void closeApp()
{
	driver.quit();
}
public String empReg(String fName,String lName)
{
	String res;
	expVal=fName+" "+lName;
	driver.findElement(By.linkText("PIM")).click();
	driver.findElement(By.linkText("Add Employee")).click();
	driver.findElement(By.id("firstName")).sendKeys(fName);
	driver.findElement(By.id("lastName")).sendKeys(lName);
	driver.findElement(By.id("btnSave")).click();
	actVal=driver.findElement(By.xpath(".//*[@id='profile-pic']/h1")).getText();
	Assert.assertEquals(actVal, expVal, "Employee regestration  failed");
	if (expVal.equalsIgnoreCase(actVal)) 
	{
		res="Pass";
	} else 
	{
		res="Fail";
	}
	return res;
	
	}
public String userReg(String empName,String uName,String pword) throws InterruptedException
{
	res="Fail";
	java.util.List<WebElement> rows;
	boolean userExist=false;
	driver.findElement(By.linkText("Admin")).click();
	driver.findElement(By.linkText("User Management")).click();
	driver.findElement(By.linkText("Users")).click();
	driver.findElement(By.id("btnAdd")).click();
	driver.findElement(By.id("systemUser_employeeName_empName")).sendKeys(empName);
	driver.findElement(By.id("systemUser_userName")).sendKeys(uName);
	driver.findElement(By.id("systemUser_password")).sendKeys(pword);
	driver.findElement(By.id("systemUser_confirmPassword")).sendKeys(pword);
	driver.findElement(By.id("btnSave")).click();
	Thread.sleep(5000);
	rows=driver.findElements(By.xpath(".//*[@id='resultTable']/tbody/tr/td[2]/a"));
	expVal=uName.toUpperCase();
	for (int i = 0; i < rows.size(); i++)
	{
	actVal=rows.get(i).getText().toUpperCase();
	if (actVal.equalsIgnoreCase(expVal))
	{
	userExist=true;
	res="Pass";
	break;
	}
	}
     Assert.assertTrue(userExist, "user Reggistration fail"+"Expected:"+expVal+"Actual: "+actVal);
     return res;
}
public String userLogin(String Uname,String pword)
{
	driver.findElement(By.id("txtUsername")).sendKeys(Uname);
	driver.findElement(By.id("txtPassword")).sendKeys(pword);
	driver.findElement(By.id("btnLogin")).click();
	Assert.assertTrue(driver.findElement(By.partialLinkText("Welcome")).isDisplayed(), "User Login Failed ");
	if (driver.findElement(By.partialLinkText("Welcome")).isDisplayed()) 
	{
		res="Pass";
	} else 
	{
		res="Fail";
	}
		return res;
}


}
