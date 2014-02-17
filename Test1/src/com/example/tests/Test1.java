package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.input.CharSequenceInputStream;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

public class Test1 {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {

		FirefoxProfile profile = new FirefoxProfile();
		profile.setEnableNativeEvents(true);

		driver = new FirefoxDriver(profile);
		baseUrl = "http://www.ringoman.ru/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testRegistration() throws Exception {

		// go to the registration page:
		driver.get(baseUrl + "/auth/register.html");
		
		//click the tab Author:
		driver.findElement(By.className("block_registration_menu_item")).click();
		
		//Name:
		driver.findElement(By.cssSelector("input[name='email']")).sendKeys(
				"test@test.rus");
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys("test@test.rus");
		driver.findElement(By.cssSelector("input[name='password_c']")).sendKeys("test@test.rus");
		
		driver.findElement(By.cssSelector("[name=name]")).sendKeys("test@test.rus");
		driver.findElement(By.cssSelector("[name=last_name]")).sendKeys("test@test.rus");
		driver.findElement(By.cssSelector("[name=sur_name]")).sendKeys("test@test.rus");
		driver.findElement(By.name("author_url")).sendKeys("test");
		driver.findElement(By.name("nickname")).sendKeys("test@test.rus");

		WebElement bdList = driver.findElement(By.name("date_birthday[day]"));
		Select bdDropDown = new Select(bdList);
		int bdListSize = bdDropDown.getOptions().size();
		bdDropDown.selectByIndex(bdListSize - 1);

		WebElement bmList = driver.findElement(By.name("date_birthday[month]"));
		Select bmDropDown = new Select(bmList);
		int bmListSize = bmDropDown.getOptions().size();
		bmDropDown.selectByIndex(bmListSize - 1);

		WebElement byList = driver.findElement(By.name("date_birthday[year]"));
		Select byDropDown = new Select(byList);
		int byListSize = byDropDown.getOptions().size();
		byDropDown.selectByIndex(byListSize - 1);

		driver.findElement(By.xpath("//label[contains(.,'Мужской')]/input")).click();
		//if (radioSex.isSelected()) {
		//	driver.findElement(By.name("gender")).findElement(By.tagName("F"))
		//			.click();
		//} else {
		//	fail("Radio btn M/F is not selected");
		//}

		WebElement mgList = driver.findElement(By.name("main_genre"));
		Select mgDropDown = new Select(mgList);
		int mgListSize = mgDropDown.getOptions().size();
		mgDropDown.selectByIndex(mgListSize - 1);

		
		//WebElement formBox = driver.findElement(By.className("form_box"));
		//Select checkedBoxes = new Select(formBox);
		//int numOfOptions = checkedBoxes.getOptions().size();
		//checkedBoxes.selectByIndex(numOfOptions - 1);

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
