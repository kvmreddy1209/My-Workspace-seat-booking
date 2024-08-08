package testcases;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import freemarker.cache.FileTemplateLoader;

public class Base {
	
	WebDriver driver;
	
	@SuppressWarnings("deprecation")
	@Parameters({"browser"})
	@BeforeTest 
	public void setUp(String s) throws IOException {
		
		if(s.equals("chrome")) {
			driver=new ChromeDriver();
			}
		else if(s.equals("edge")) {
			driver=new EdgeDriver();
		}
		driver. manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://onecognizant.cognizant.com/Home");
		driver.manage().window().maximize();
		
		
		
		}
	
	
	@Test(priority=1)
	@Parameters({"browser"}) 
	public void searchBox(String s) throws InterruptedException {
		
		//WebElement oneC_search_chrome=driver.findElement(By.id("oneC_searchAutoComplete"));
		//WebElement onec_search_edge=driver.findElement(By.className("searchTopBar"));
		if(s.equals("chrome")) {
			WebElement oneC_search=driver.findElement(By.id("oneC_searchAutoComplete"));
			System.out.println(oneC_search.isEnabled());
			oneC_search.click();
			oneC_search.sendKeys("my workspace");
			}
		else if(s.equals("edge")) {
			WebElement oneC_search=driver.findElement(By.className("searchTopBar"));
			System.out.println(oneC_search.isEnabled());
			oneC_search.click();
			WebElement oneC_searchBar=driver.findElement(By.className("oneCSearchTopBar"));
			oneC_searchBar.sendKeys("my workspace");
			
		}
			
	}
	
	@Test(priority=2, enabled=true)
	public void clickonMyworkspace() throws InterruptedException {
		WebElement myworksapce=driver.findElement(By.className("appsSearchResult"));
		myworksapce.click();
		driver.switchTo().frame("appFrame");
		driver.findElement(By.xpath("(//section[@class='home-block'])[1]")).click();
		driver.findElement(By.xpath("//input[@class='check-mark termsandconditioncheck']")).click();
		driver.findElement(By.xpath("//button[@class='bold-family agree ap-confrim-disabled']")).click();
		
		driver.findElement(By.id("home-autocomplete")).sendKeys("HYD");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='ui-menu-item-wrapper' and text()=\"HYD DLF Block 1\"]")).click();
		
	}
	
	@Test(priority=3, enabled=true)
	public void datepicker() {
		String month="August 2024";
		String day="01";
		
		while(true) {
			String month_present=driver.findElement(By.xpath("//span[@id='monthlabel']")).getText();
			System.out.println(month_present);
			if(month_present.equals(month)) {
				break;
			}
			else {
				WebElement nextBtn=driver.findElement(By.xpath("//button[@id='nextmonth']"));
				nextBtn.click();
			}
		}
		
		WebElement chkdate_enabled=driver.findElement(By.xpath("//button[@class='day-show ng-scope']/span[@class='date-def' and text()='"+day+"']"));
		System.out.println(chkdate_enabled.isEnabled());
		
		chkdate_enabled.click();
		
		
		
        //Locating element by link text and store in variable "Element"        		
        WebElement Element =driver.findElement(By.xpath("//button[@id='searchbutton']"));

        // Scrolling down the page till the element is found
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", Element);
        js.executeScript("window.document.getElementById('searchbutton').click()");

	}
	
	@Test(priority=4, enabled=false)
	public void timeSelecter() {
		WebElement startTime=driver.findElement(By.xpath("//span[@id='slider_0']"));
		WebElement endTime=driver.findElement(By.xpath("//span[@id='slider1_0']"));
		
		/*
		 * Actions act=new Actions(driver); //Drag and Drop by Offset. act.move
		 * act.dragAndDropBy(startTime,100, 0).build().perform(); //
		 * act.dragAndDropBy(endTime,30, 0).build().perform();if(l.getText().contains("GBI03B005B02A048")) {
				l.click();
			}
		 */	    
	    driver.findElement(By.xpath("//button[@id='searchbutton']")).click();	    
	}
	
	@Test(priority=5, enabled=true)
	public void selectSeat() {
		WebElement availableSeats=driver.findElement(By.xpath("//p[@id='countofEachSeats']"));
		System.out.println("Total available Seats in DLF:"+ availableSeats.getText());
		WebElement seatsList=driver.findElement(By.xpath("//div[@id='TimeSlot_0_0']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", seatsList);
        js.executeScript("window.document.getElementById('TimeSlot_0_0').click()");
		//seatsList.click();
		
		//div[@data-building="HYD DLF Block 1"]HYD Raheja IT Park | BLD 20
		List<WebElement> ListofSeats=driver.findElements(By.xpath("//div[@data-building=\"HYD DLF Block 1\" and @data-wing=\"B\" and @data-bay=\"2\"]"));
		for(WebElement l:ListofSeats) {
			System.out.println(l.getText());
			
			if(l.getText().contains("GBI03B005B02A049")) {
				
				WebElement checkBox=driver.findElement(By.xpath("//input[@class='check-mark' and @id='GBI03B005B02A049']"));
				js.executeScript("arguments[0].scrollIntoView();", checkBox);
		        js.executeScript("window.document.getElementById('GBI03B005B02A049').click()");
			}
			
			
		}
		
		 driver.findElement(By.xpath("//*[@id='list_0']/section/button")).click();
		
	
		 driver.findElement(By.xpath("//button[@class='bold-family error-confrim opencart']")).click();
		
		 driver.findElement(By.xpath("//button[@class='bold-family cart-confrim']")).click();
		// driver.findElement(By.xpath("//button[@class='bold-family seat-request-confrim']")).click();
		 String Success_msg=driver.findElement(By.xpath("//div[@class='modal-body cart-submitted-body']")).getText();
		 System.out.println(Success_msg);
	}
	
	
	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}

	}

