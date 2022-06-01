package stepDefinition;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

//import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
//import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.utility.RandomString;



public class RetailForm {

	WebDriver driver;
	public static String email;
	public static String password;
	
	@Given("^Lunch Upskill$")
	public void lunch_Upskill() throws Throwable {
		//WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver","C:\\ChromeDriver\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://retailm1.upskills.in/");
	}

	@When("^account icon shown$")
	public void signup_button_shown() throws Throwable {
		System.out.println(driver.findElement(By.xpath("//li[contains(@class,'tb_link dropdown tb_menu_system_account_account')]")).isDisplayed());
	}

	@Then("^click on Account icon$")
	public void click_signUp() throws Throwable {
		driver.findElement(By.xpath("//li[contains(@class,'tb_link dropdown tb_menu_system_account_account')]")).click();
	}
	
	
	@Then("^click on LoginRegister link$")
	public void click_on_LoginRegister_link() throws Throwable {	
		driver.findElement(By.xpath("//a[text()='Register']")).click();
	}
	
	@Then("^click on Login link$")
	public void click_on_Login_link() throws Throwable {
		driver.findElement(By.xpath("//input[@value='Login']")).click();
	}
	
	@Then("^Login Retail Portal$")
	public void login_Retail_Portal() throws Throwable {
		driver.findElement(By.name("email")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(password);
	}

	@Then("^fill up the registeration Form for all mandatory fields$")
	public void fill_up_the_registeration_Form_for_all_mandatory_fields(DataTable dt) throws Throwable {
		
		Map<String,String> data = dt.asMap(String.class, String.class);
		
		String usernamevalue=data.get("Email");
		String[]val=usernamevalue.split("@");
		
//		LocalDateTime currentDateTime = LocalDateTime.now();
//	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyhhmmss"); 
//		String formattedDateTime = currentDateTime.format(dtf);
//		usernamevalue=usernamevalue.concat(formattedDateTime);
		
		String rand=RandomString.make(5);
		System.out.println(rand);
		
		String Usernameemail=val[0]+rand+"@"+val[1];
		email=Usernameemail;
		password=data.get("password");
		System.out.println(Usernameemail);
		
		driver.findElement(By.name("firstname")).sendKeys(data.get("FirstName"));
		driver.findElement(By.name("lastname")).sendKeys(data.get("LastName"));
		driver.findElement(By.name("email")).sendKeys(Usernameemail);			
		driver.findElement(By.name("telephone")).sendKeys(data.get("Telephone"));
		driver.findElement(By.name("address_1")).sendKeys(data.get("Address1"));
		driver.findElement(By.name("address_2")).sendKeys(data.get("Address2"));
		driver.findElement(By.name("city")).sendKeys(data.get("city"));
		driver.findElement(By.name("postcode")).sendKeys(data.get("PostCode"));
		
		Select drpCountry = new Select(driver.findElement(By.name("country_id")));
		drpCountry.selectByVisibleText(data.get("Country"));
		
		Select drpState = new Select(driver.findElement(By.name("zone_id")));
		drpState.selectByVisibleText(data.get("State"));


		driver.findElement(By.name("password")).sendKeys(data.get("password"));
		driver.findElement(By.name("confirm")).sendKeys(data.get("confirmPassword"));
		
		driver.findElement(By.xpath("//input[@type='radio' and @value='0']")).click();
		driver.findElement(By.xpath("//input[@type='checkbox' and @value='1']")).click();
		
		
	}

	@Then("^Submit the details$")
	public void submit_the_details() throws Throwable {
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
	}
	
	@When("^Verify Message \"([^\"]*)\"$")
	public void verify_Message(String stringval) throws Throwable {
		
		WebElement continueButton= driver.findElement(By.xpath("//a[contains(text(),'Continue')]"));
		
		List<WebElement> confirmationtexts = driver.findElements(By.tagName("p"));
		System.out.println(confirmationtexts.size());
		for (WebElement webElement : confirmationtexts) {
		String texts = webElement.getText();
		if(texts.contains(stringval)&&continueButton.isDisplayed()) {
		System.out.println(stringval + ": Text Verified");
//		Assert.assertEquals(texts, stringval);
//		break;
		}
		}
		System.out.println("a");
	}
	
	@When("^Verify Header \"([^\"]*)\"$")
	public void verify_Header(String stringval) throws Throwable {
		if(driver.findElements(By.xpath("//h2[contains(text(),'"+stringval+"')]")).size()>0) {
		System.out.println(stringval + ": Text Verified");
//		Assert.assertEquals(texts, stringval);
//		break;
		}
	}
	
	@Then("^Close Browser$")
	public void close_Browser() throws Throwable {
			driver.close();
	}
	


	@Then("^Click on Next$")
	public void click_on_Next() throws Throwable {
		driver.findElement(By.xpath("//button[contains(text(),'Next')]")).click();
	}

	@Then("^Click on Profile$")
	public void click_on_Profile() throws Throwable {
		
		driver.findElement(By.xpath("//a[contains(text(),'Homepage')]")).click();
		driver.findElement(By.xpath("//a[contains(@class,'dropdown-toggle')]")).click();
		driver.findElement(By.xpath("//ul[contains(@class,'dropdown-menu')]//a[contains(text(),'Profile')]")).click();

	}

	@Then("^Click on Compose Message$")
	public void click_on_Compose_Message() throws Throwable {
		
		driver.findElement(By.xpath("//li[@class='messages-icon ']//a")).click();
		
		driver.findElement(By.xpath("//img[@title='Compose message']/parent::a")).click();

	}

	@When("^Enter the details shown on the page$")
	public void enter_the_details_shown_on_the_page() throws Throwable {
		
		driver.findElement(By.xpath("//input[@name='title']")).sendKeys("value");

	}

	@Then("^Click on Send message$")
	public void click_on_Send_message() throws Throwable {
		
		driver.findElement(By.xpath("//button[text()=' Send message']")).click();

	}
	
	
	
	
}
