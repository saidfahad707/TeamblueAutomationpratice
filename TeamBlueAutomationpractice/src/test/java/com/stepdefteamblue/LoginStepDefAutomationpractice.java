package com.stepdefteamblue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginStepDefAutomationpractice {
	WebDriver driver;
	String SecondPrice;
	ArrayList<String> NewList;

	@Given("^user open Webbrowser and navigate to My Stor login page$")
	public void user_open_Webbrowser_and_navigate_to_My_Stor_login_page() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "C:\\Driver\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/index.php");
		driver.manage().window().maximize();

	}

	@Then("^user Click on the Sign-in button,$")
	public void user_Click_on_the_Sign_in_button() throws Throwable {
		driver.findElement(By.xpath("//*[@title='Log in to your customer account']")).click();

	}

	@Then("^user enter valid username and password$")
	public void user_enter_valid_username_and_password() throws Throwable {
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys("Fahadfai808@gmail.com");
		driver.findElement(By.xpath("//*[@id='passwd']")).sendKeys("Fahad334721");

	}

	@Then("^user submit Sign-in button$")
	public void user_submit_Sign_in_button() throws Throwable {

		WebElement Singin = driver.findElement(By.xpath("//*[@class='icon-lock left']"));
		Singin.click();

	}

	@Then("^user You will get a home page; on the home page verify page title$")
	public void user_You_will_get_a_home_page_on_the_home_page_verify_page_title() throws Throwable {
		// driver.getTitle();
		System.out.println(driver.getTitle());
		String actule = driver.getTitle();
		String expected = "My Store";
		Assert.assertTrue(actule.contains(expected), "the titel is wrong");

	}

	@Then("^user Click on the upper left corner \\(Dresses\\) link, below displayed showing (\\d+) items;$")
	public void user_Click_on_the_upper_left_corner_Dresses_link_below_displayed_showing_items(int arg1)
			throws Throwable {
		driver.findElement(By.xpath("//*[@id='block_top_menu']/ul/li[2]/a")).click();
	}

	@Then("^user Print all the price values in sorted order \\(descending order\\) on the console$")
	public void user_Print_all_the_price_values_in_sorted_order_descending_order_on_the_console() throws Throwable {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,1200)");

		List<WebElement> AllPrices = driver
				.findElements(By.xpath("//*[@class='product-desc']/following-sibling::div[1]"));

		NewList = new ArrayList<String>();
		for (int i = 0; i < AllPrices.size(); i++) {
			NewList.add(AllPrices.get(i).getText().toString());

		}
		Collections.sort(NewList, Collections.reverseOrder());
		System.out.println("All Prices in Descending Order:" + NewList);
	}

	@Then("^user Select the second dress on that list; remember the price \\(the list may change so it is dynamic\\)$")
	public void user_Select_the_second_dress_on_that_list_remember_the_price_the_list_may_change_so_it_is_dynamic()
			throws Throwable {
		SecondPrice = NewList.get(1);
		System.out.println("Price of the second dress: " + SecondPrice);

		driver.findElement(By.xpath("(//*[contains(text(),'" + SecondPrice + "')])[2]")).click();
		Thread.sleep(3000);
	}

	@When("^user On the next page click on Proceed to checkout$")
	public void user_On_the_next_page_click_on_Proceed_to_checkout() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		WebElement checkOutBtn = driver.findElement(By.xpath("//*[contains(text(),'Proceed to checkout')]"));
		wait.until(ExpectedConditions.visibilityOf(checkOutBtn)).click();
	}

	@Then("^user On the next page verify there is a Total price with shipping, close the window and logout$")
	public void user_On_the_next_page_verify_there_is_a_Total_price_with_shipping_close_the_window_and_logout()
			throws Throwable {
		
		Double expectedshippingprice = Double.parseDouble(SecondPrice.replace("$", ""))+2;
		System.out.println("The expected price is: "+expectedshippingprice);
		Double actualshipingprice = Double.parseDouble(driver.findElement(By.xpath("//*[@id='total_price']")).getText().replace("$", ""));
		
		Assert.assertTrue(actualshipingprice.equals(expectedshippingprice));

		WebElement logout = driver.findElement(By.xpath("//*[@class='logout']"));
//		ExplicitWait
		
		//logout.click();
//		WebDriverWait wait = new WebDriverWait(driver,10);
//		wait.until(ExpectedConditions.visibilityOf(logout)).click();
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(1,
				TimeUnit.SECONDS);
		wait.until(ExpectedConditions.visibilityOf(logout)).click();

		driver.close();

	}

}
