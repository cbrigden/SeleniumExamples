package MiscTests;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMisc {
	WebDriver driver;

	// URL's
	String demoSiteURL = "http://thedemosite.co.uk/";
	String theInterNetExamplesURL = "http://the-internet.herokuapp.com/";
	String automationPracticeURL = "http://automationpractice.com/index.php";
	String bookTheAutomationTesterURL = "http://book.theautomatedtester.co.uk/";

	// http://thedemosite.co.uk/
	// http://newtours.demoaut.com/
	// http://the-internet.herokuapp.com/
	// http://automationpractice.com/index.php

	// http://book.theautomatedtester.co.uk/

	// ======================== The DemoSite Tests
	// ==========================================

	/**
	 * Checks that thedemosite.co.uk/ is reached
	 */
	@Test
	public void DemoSiteTest_01_ReachSite() {
		System.setProperty("webdriver.gecko.driver", ".//drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get(demoSiteURL);
		assertEquals(driver.getCurrentUrl(), demoSiteURL);
		driver.close();
	}

	/**
	 * Checks That it can navigate from the home screen to the add new user screen
	 * by clicking the add user button.
	 */
	@Test
	public void DemoSiteTest_02_NavigateFromHomeToAddUser() {
		System.setProperty("webdriver.gecko.driver", ".//drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get(demoSiteURL);
		assertEquals(driver.getCurrentUrl(), demoSiteURL);
		driver.findElement(By.xpath("//a[contains(text(),'3. Add a User')]")).click();
		assertEquals(driver.getCurrentUrl(), "http://thedemosite.co.uk/addauser.php");
		driver.close();
	}

	/**
	 * test adding a user
	 * 
	 */
	@Test
	public void DemoSiteTest_03_AddingAUser() {
		String key = "test";
		System.setProperty("webdriver.gecko.driver", ".//drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		// navigate to the site's adduser page
		driver.get("http://thedemosite.co.uk/addauser.php");

		// input username
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(key);
		// input password
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(key);
		// click save
		driver.findElement(By.name("FormsButton2")).click();

		String output = driver.findElement(By.xpath("//body//blockquote//blockquote//blockquote[1]")).getText();

		assertEquals("The username: test\n" + "The password: test", output);
		driver.close();
	}

	/**
	 * test adding a user then logging in
	 * 
	 */
	@Test
	public void DemoSiteTest_04_LoggingIn() {
		String key = "test";
		System.setProperty("webdriver.gecko.driver", ".//drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		// navigate to the site's adduser page
		driver.get("http://thedemosite.co.uk/addauser.php");

		// input username
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(key);
		// input password
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(key);
		// click save
		driver.findElement(By.name("FormsButton2")).click();

		String output = driver.findElement(By.xpath("//body//blockquote//blockquote//blockquote[1]")).getText();

		assertEquals("The username: test\n" + "The password: test", output);

		driver.findElement(By.xpath("//a[contains(text(),'4. Login')]")).click();
		// check we could navigate to the right page
		assertEquals(driver.getCurrentUrl(), "http://thedemosite.co.uk/login.php");

		driver.findElement(By.name("username")).sendKeys(key);
		driver.findElement(By.name("password")).sendKeys(key);
		driver.findElement(By.name("FormsButton2")).click();
		output = driver.findElement(By.xpath("//td[@class='auto-style1']//big//blockquote//blockquote//font//center"))
				.getText();
		assertEquals("**Successful Login**", output);

		driver.close();
	}

	/**
	 * test failing to login by inputting wrong name and password
	 * 
	 */
	@Test
	public void DemoSiteTest_05_LoginFail() {
		String key = "test";
		System.setProperty("webdriver.gecko.driver", ".//drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		// navigate to the site's adduser page
		driver.get("http://thedemosite.co.uk/addauser.php");

		// input username
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(key);
		// input password
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(key);
		// click save
		driver.findElement(By.name("FormsButton2")).click();

		String output = driver.findElement(By.xpath("//body//blockquote//blockquote//blockquote[1]")).getText();

		assertEquals("The username: test\n" + "The password: test", output);

		driver.findElement(By.xpath("//a[contains(text(),'4. Login')]")).click();
		// check we could navigate to the right page
		assertEquals(driver.getCurrentUrl(), "http://thedemosite.co.uk/login.php");

		driver.findElement(By.name("username")).sendKeys(key + "1");
		driver.findElement(By.name("password")).sendKeys(key + "1");
		driver.findElement(By.name("FormsButton2")).click();
		output = driver.findElement(By.xpath("//td[@class='auto-style1']//big//blockquote//blockquote//font//center"))
				.getText();
		assertEquals("**Failed Login**", output);

		driver.close();
	}

	/**
	 * test inputting the correct name but wrong password
	 * 
	 */
	@Test
	public void DemoSiteTest_06_LoginFailWrongPassword() {
		String key = "test";
		System.setProperty("webdriver.gecko.driver", ".//drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		// navigate to the site's adduser page
		driver.get("http://thedemosite.co.uk/addauser.php");

		// input username
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(key);
		// input password
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(key);
		// click save
		driver.findElement(By.name("FormsButton2")).click();

		String output = driver.findElement(By.xpath("//body//blockquote//blockquote//blockquote[1]")).getText();

		assertEquals("The username: test\n" + "The password: test", output);

		driver.findElement(By.xpath("//a[contains(text(),'4. Login')]")).click();
		// check we could navigate to the right page
		assertEquals(driver.getCurrentUrl(), "http://thedemosite.co.uk/login.php");

		driver.findElement(By.name("username")).sendKeys(key);
		driver.findElement(By.name("password")).sendKeys(key + "1");
		driver.findElement(By.name("FormsButton2")).click();
		output = driver.findElement(By.xpath("//td[@class='auto-style1']//big//blockquote//blockquote//font//center"))
				.getText();
		assertEquals("**Failed Login**", output);

		driver.close();
	}

	// =============================the-internet.herokuapp Tests
	// =========================

	/** Checks that the site can be reached */
	@Test
	public void herokuappTest_00_ReachSite() {
		System.setProperty("webdriver.gecko.driver", ".//drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get(theInterNetExamplesURL);
		assertEquals(driver.getCurrentUrl(), theInterNetExamplesURL);
		driver.close();

	}

	/** Checks navigation to add/remove element page */
	@Test
	public void herokuappTest_01_NavFromHomeToElementPage() {
		System.setProperty("webdriver.gecko.driver", ".//drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get(theInterNetExamplesURL);
		driver.findElement(By.xpath("//a[contains(text(),'Add/Remove Elements')]")).click();
		assertEquals(driver.getCurrentUrl(), "http://the-internet.herokuapp.com/add_remove_elements/");
		driver.close();

	}

	/**
	 * checks add element button adds an element
	 */
	@Test
	public void herokuappTest_02_addElement() {
		System.setProperty("webdriver.gecko.driver", ".//drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		// navigate to the add/remove elements page
		driver.get(theInterNetExamplesURL + "add_remove_elements/");
		driver.findElement(By.xpath("//button[contains(text(),'Add Element')]")).click();
		List<WebElement> list = driver.findElements(By.className("added-manually"));
		// the element should be added, and there should only be that one element, so
		// the find elements function should return us a list with only one element
		assertEquals(list.size(), 1);
		driver.close();
	}

	/**
	 * checks that when you click the add element button twice, it adds 2 elements
	 */
	@Test
	public void herokuappTest_02_addmultipleElements() {
		System.setProperty("webdriver.gecko.driver", ".//drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		// navigate to the add/remove elements page
		driver.get(theInterNetExamplesURL + "add_remove_elements/");
		driver.findElement(By.xpath("//button[contains(text(),'Add Element')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Add Element')]")).click();
		List<WebElement> list = driver.findElements(By.className("added-manually"));
		// the element should be added, and there should only be that one element, so
		// the find elements function should return us a list with only one element
		assertEquals(list.size(), 2);
		driver.close();
	}
}
