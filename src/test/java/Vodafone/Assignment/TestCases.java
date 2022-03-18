package Vodafone.Assignment;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


@Test
public class TestCases {
	
	WebDriver driver;
	// Function for random email
	Random randomGenerator = new Random();  
	int randomInt = randomGenerator.nextInt(1000);
	String emaill=("username"+ randomInt+randomInt +"@gmail.com");
	
	
	@BeforeTest
	public void OpenUrl() {
		
     	System.setProperty( "webdriver.chrome.driver", System.getProperty("user.dir")+"\\Resources\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.navigate().to( "http://automationpractice.com");
		driver.manage().window().maximize();
		WebElement Signin_button= driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a"));
		Signin_button.click();
	
	}
	@Test (priority=0)
	public void InvalidScenario() {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement Signin = driver.findElement(By.id("SubmitCreate"));
		Signin.click();
		WebElement error=driver.findElement(By.xpath("//*[@id=\"create_account_error\"]/ol/li"));
		String s1= (error.getText());
		Assert.assertTrue(s1.contains("Invalid email address."));	
	}
	
	@Test (priority=1)
	public void InvalidEmail() {
		WebElement email2=driver.findElement(By.id("email_create"));
		email2.clear();
		email2.sendKeys("mm@gmail.com");
		WebElement Signin1 = driver.findElement(By.id("SubmitCreate"));
		Signin1.click();
		WebElement error1=driver.findElement(By.xpath("//*[@id=\"create_account_error\"]/ol/li"));
		String s2= (error1.getText());
		Assert.assertTrue(s2.contains("An account using this email address has already been registered. Please enter a valid password or request a new one."));	
	}
	
	@Test (priority=2)
	public void valid()	 {
		
		WebElement email= driver.findElement(By.id("email_create"));
		email.clear();

		email.sendKeys(emaill); 
		WebElement btn = driver.findElement(By.xpath("//*[@id=\"SubmitCreate\"]"));
		btn.click();
		}
	
	@Test(priority=3)
	public void InvalidSignUp() {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement register_button=driver.findElement(By.id("submitAccount"));
		register_button.click();
		WebElement error=driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/p"));
	    String s3= error.getText();
	    Assert.assertTrue(s3.contains("There are 8 errors"));
	}
	
	@Test (priority=4)
	public void signup() {
		WebElement radio1=driver.findElement(By.xpath("//*[@id=\"uniform-id_gender2\"]/span"));
		radio1.click();
		WebElement first_name=driver.findElement(By.id("customer_firstname"));
		first_name.sendKeys("Mohamed");
		WebElement last_name=driver.findElement(By.id("customer_lastname"));
		last_name.sendKeys("Maged");
		WebElement mail=driver.findElement(By.id("email"));
		mail.click();
		WebElement password=driver.findElement(By.id("passwd"));
		password.sendKeys("12345678");
		WebElement day= driver.findElement(By.xpath("//*[@id=\"days\"]"));
		Select select1 = new Select(day);
		select1.selectByValue("6");
		WebElement month= driver.findElement(By.id("months"));
		Select select2 = new Select(month);
		select2.selectByValue("8");
		WebElement year= driver.findElement(By.id("years"));
		Select select3 = new Select(year);
		select3.selectByValue("1997");
		WebElement check1=driver.findElement(By.xpath("//*[@id=\"uniform-newsletter\"]"));
		check1.click();
		WebElement check2=driver.findElement(By.xpath("//*[@id=\"uniform-optin\"]"));
		check2.click();
		WebElement company=driver.findElement(By.id("company"));
		company.sendKeys("Vodafone");
		WebElement address1=driver.findElement(By.id("address1"));
		address1.sendKeys("Maadi,Cairo");
		WebElement City=driver.findElement(By.id("city"));
		City.sendKeys("Giza");
		WebElement state= driver.findElement(By.id("id_state"));
		Select select4 = new Select(state);
		select4.selectByValue("30");
		WebElement post_code=driver.findElement(By.id("postcode"));
		post_code.sendKeys("54321");
		WebElement additional=driver.findElement(By.id("other"));
		additional.sendKeys("additional informaton");
		WebElement phone=driver.findElement(By.id("phone"));
		phone.sendKeys("9876543210");
		WebElement phone_mobile=driver.findElement(By.id("phone_mobile"));
		phone_mobile.sendKeys("0123456789");
		WebElement alias=driver.findElement(By.id("alias"));
		alias.clear();
		alias.sendKeys("address");
		WebElement Submit=driver.findElement(By.id("submitAccount"));
		Submit.click();
	}
	
	// to authenticate new user
	@Test (priority=5)
	public void InvalidAuthenticateUser () {
	
	WebElement Sign_out=driver.findElement(By.linkText("Sign out"));
	Sign_out.click();
	
	WebElement email=driver.findElement(By.id("email"));
	email.sendKeys("mmm");
	WebElement password =driver.findElement(By.id("passwd"));
	password.sendKeys("12345");
	WebElement button= driver.findElement(By.name("SubmitLogin"));
	button.click();
	WebElement error1=driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li"));
	String erorr_mail=error1.getText();
	Assert.assertTrue(erorr_mail.contains("Invalid email address."));
	WebElement email2=driver.findElement(By.id("email"));
	email2.sendKeys(emaill);
	WebElement password2 =driver.findElement(By.id("passwd"));
	password2.sendKeys("12345678");
	WebElement button2= driver.findElement(By.name("SubmitLogin"));
	button2.click();
	WebElement error2=driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li"));
	String erorr_pass=error2.getText();
	Assert.assertTrue(erorr_pass.contains("Authentication failed."));
  
	}
	
	@Test (priority=6)
	public void validAuthenticate() {
		WebElement email=driver.findElement(By.id("email"));
		email.clear();
		email.sendKeys(emaill);
		WebElement password =driver.findElement(By.id("passwd"));
		password.clear();
		password.sendKeys("12345678");
		WebElement button= driver.findElement(By.name("SubmitLogin"));
		button.click();
	}
	
	@Test (priority=7)
	public void category() {
		
		WebElement searchBtn = driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]"));
		Actions action = new Actions(driver);
		action.moveToElement(searchBtn).build().perform();
		WebElement menuOption = driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/ul/li[1]/ul/li[2]/a"));
		action.moveToElement(searchBtn).moveToElement(menuOption).click().build().perform();
		
	}
	
	@Test (priority=8)
	public void select() {
		WebElement searchBtn0 = driver.findElement(By.linkText("Blouse"));
		Actions action = new Actions(driver);
		action.moveToElement(searchBtn0).build().perform();
		WebElement searchBtn = driver.findElement(By.linkText("Add to cart"));
		action.moveToElement(searchBtn0).moveToElement(searchBtn).click().build().perform();
	}
	
	@Test (priority=9)
	public void check_out01() {
		WebElement checkOut=driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span"));
		checkOut.click();
	}
	
	@Test (priority=10)
	public void check_out02() {
	WebElement checkout= driver.findElement(By.linkText("Proceed to checkout"));
	checkout.click();
  }
	@Test (priority=11)
	public void check_out03() {
		WebElement checkout= driver.findElement(By.name("processAddress"));
		checkout.click();
	}
	@Test (priority=11)
	public void terms() {
		
		WebElement CheckBox=driver.findElement(By.xpath("//*[@id=\"cgv\"]"));
		CheckBox.click();
	}
	@Test (priority=12)
	public void ProcessOfCheckOut() {
		WebElement ProceedToCheck_Out=driver.findElement(By.xpath("//*[@id=\"form\"]/p/button/span"));
		ProceedToCheck_Out.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement Pay_bybank=driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a"));
		Pay_bybank.click();
		WebElement Confirm=driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button/span"));
		Confirm.click();
	}
	@Test (priority=13)
	public void history() {
		WebElement history=driver.findElement(By.xpath("//*[@id=\"center_column\"]/p/a"));
		history.click();	
		WebElement ref=driver.findElement(By.xpath("//*[@id=\"order-list\"]/tbody/tr/td[1]/a"));
		ref.click();
		WebElement product=driver.findElement(By.xpath("//*[@id=\"order-detail-content\"]/table/tbody/tr/td[2]"));
		String productt=product.getText();
	    Assert.assertTrue(productt.contains("Blouse - Color : Black, Size : S"));	
	}
	
	@AfterTest
	public void Exit() {
		driver.quit();;
	}
}