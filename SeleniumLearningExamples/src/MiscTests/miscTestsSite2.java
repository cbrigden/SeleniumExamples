package MiscTests;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class miscTestsSite2 {
	WebDriver driver;

	String URL = "http://the-internet.herokuapp.com/";
	// =============================the-internet.herokuapp Tests
	// =========================

	/** Checks that the site can be reached */
	@Test
	public void herokuappTest_ReachSite() {
		System.setProperty("webdriver.gecko.driver", ".//drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get(URL);
		assertEquals(driver.getCurrentUrl(), URL);
		driver.close();

	}

	/** Checks navigation to add/remove element page */
	@Test
	public void herokuappTest_NavFromHomeToElementPage() {
		System.setProperty("webdriver.gecko.driver", ".//drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get(URL);
		driver.findElement(By.xpath("//a[contains(text(),'Add/Remove Elements')]")).click();
		assertEquals(driver.getCurrentUrl(), "http://the-internet.herokuapp.com/add_remove_elements/");
		driver.close();

	}

	/**
	 * checks add element button adds an element
	 */
	@Test
	public void herokuappTest_addElement() {
		System.setProperty("webdriver.gecko.driver", ".//drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		// navigate to the add/remove elements page
		driver.get(URL + "add_remove_elements/");
		driver.findElement(By.xpath("//button[contains(text(),'Add Element')]")).click();
		List<WebElement> list = driver.findElements(By.className("added-manually"));
		/*
		 * the element should be added, and there should only be that one element, so
		 * the find elements function should return us a list with only one element
		 */
		assertEquals(list.size(), 1);
		driver.close();
	}

	/**
	 * checks that when you click the add element button twice, it adds 2 elements
	 */
	@Test
	public void herokuappTest_addmultipleElements() {
		System.setProperty("webdriver.gecko.driver", ".//drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		// navigate to the add/remove elements page
		driver.get(URL + "add_remove_elements/");
		driver.findElement(By.xpath("//button[contains(text(),'Add Element')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Add Element')]")).click();
		List<WebElement> list = driver.findElements(By.className("added-manually"));
		/*
		 * two elements should have been added, so the list should have a size of 2 to
		 * imply that both elements were added.
		 */
		assertEquals(list.size(), 2);
		driver.close();
	}

	/**
	 * checks that when you click an added elements delete button that it deletes
	 * the element when there is only one element to be deleted
	 */
	@Test
	public void herokuappTest_removeElement() {
		System.setProperty("webdriver.gecko.driver", ".//drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		// navigate to the add/remove elements page
		driver.get(URL + "add_remove_elements/");
		driver.findElement(By.xpath("//button[contains(text(),'Add Element')]")).click();
		List<WebElement> list = driver.findElements(By.className("added-manually"));
		// the element should have been added, so we get the first element in the list
		// and click on it
		list.get(0).click();
		list = driver.findElements(By.className("added-manually"));
		/*
		 * the element that was clicked should have been removed, so the list of all the
		 * elements should be empty, therefore it's size == 0 implies that the element
		 * was removed
		 */
		assertEquals(list.size(), 0);
		driver.close();
	}

	/**
	 * checks that when you remove an element when there are two elements, that it
	 * only removes one element, and keeps the other
	 */
	@Test
	public void herokuappTest_removeOneElement() {
		System.setProperty("webdriver.gecko.driver", ".//drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		// navigate to the add/remove elements page
		driver.get(URL + "add_remove_elements/");
		driver.findElement(By.xpath("//button[contains(text(),'Add Element')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Add Element')]")).click();
		List<WebElement> list = driver.findElements(By.className("added-manually"));
		/*
		 * the list should contain 2 elements, we click the first element to attempt to
		 * remove it
		 */
		list.get(0).click();
		list = driver.findElements(By.className("added-manually"));
		/*
		 * the first element should have been removed so after getting the list of
		 * elements again it should only have a size of 1 now, implying that 1 element
		 * was removed and the other stayed
		 */
		assertEquals(list.size(), 1);
		driver.close();
	}

	/** checks that you can remove 2 elements */
	@Test
	public void herokuappTest_removemultipleElements() {
		System.setProperty("webdriver.gecko.driver", ".//drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		// navigate to the add/remove elements page
		driver.get(URL + "add_remove_elements/");
		driver.findElement(By.xpath("//button[contains(text(),'Add Element')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Add Element')]")).click();
		List<WebElement> list = driver.findElements(By.className("added-manually"));
		/*
		 * both elements should have been found and put in the list, we click both of
		 * them to attempt to remove them both
		 */
		list.get(0).click();
		list.get(1).click();
		list = driver.findElements(By.className("added-manually"));
		/*
		 * both elements should have been removed to the list resulting from searching
		 * for them should be empty
		 */
		assertEquals(list.size(), 0);
		driver.close();
	}
}
