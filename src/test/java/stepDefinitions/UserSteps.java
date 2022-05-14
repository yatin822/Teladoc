package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class UserSteps {
    WebDriver driver;

    @Given("^I open user page")
    public void i_open_user_page() {
        System.setProperty("webdriver.chrome.driver", "src/test/driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.way2automation.com/angularjs-protractor/webtables/");
    }

    @When("^I click Add User button")
    public void iClickAddUserButton() {

        driver.findElement(By.xpath("//button[@type='add']")).click();
    }


    @When("^I add user with following data:")
    public void iAddUserWithFollowingData(DataTable table) {
        //Initialize data table
        List<Map<String,String>> data = table.asMaps(String.class,String.class);

        driver.findElement(By.xpath("//input[@name='FirstName']")).sendKeys(data.get(0).get("First Name"));

        driver.findElement(By.xpath("//input[@name='LastName']")).sendKeys(data.get(0).get("Last Name"));

        driver.findElement(By.xpath("//input[@name='UserName']")).sendKeys(data.get(0).get("User Name"));

        driver.findElement(By.xpath("//input[@name='Password']")).sendKeys(data.get(0).get("Password"));

        driver.findElement(By.xpath("//input[@value='15']")).click();

        Select dropDownCustomer = new Select(driver.findElement(By.name("RoleId")));
        dropDownCustomer.selectByValue("2");

        driver.findElement(By.xpath("//input[@name='Email']")).sendKeys(data.get(0).get("Email Address"));

        driver.findElement(By.xpath("//input[@name='Mobilephone']")).sendKeys(data.get(0).get("Cell Phone"));

        driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();

    }

    @When("I delete {string} user")
    public void iDeleteUser(String user) {
        driver.findElement(By.xpath("//table[contains(@class,'table-striped')]/tbody/tr/td[text()='" + user + "']/following-sibling::td[8]/button/i")).click();

        driver.findElement(By.xpath("//div/button[text()='OK']")).click();
    }

    @Then("The table contains {string} user")
    public void theTableContainsUser(String expectedUserName) {
        String actualUsername = driver.findElement(By.xpath("//table[contains(@class,'table-striped')]/tbody/tr/td[text()='" + expectedUserName + "']")).getText();
        assertEquals(expectedUserName + " is found in the table", actualUsername , expectedUserName);
        System.out.println("Assert passed and " + actualUsername + " is found in the table");
        driver.close();
    }

    @Then("The table doesn't contain {string} user")
    public void theTableDoesnTContainUser(String user) {
        List<WebElement> data =  driver.findElements(By.xpath("//table[contains(@class,'table-striped')]/tbody/tr/td[3]"));
        int totalUser = data.size();
        for(int i = 1 ; i <= totalUser ; i++ ){
            String actualUsername = driver.findElement(By.xpath("//table[contains(@class,'table-striped')]/tbody/tr["+ i +"]/td[3]")).getText();
            if (actualUsername != user ) {
                assertNotEquals(user + " is not found in the table", actualUsername , user);
                System.out.println("Assert passed and " + user + " is not found. This row is for user " + actualUsername );
            }
        }
        driver.close();
    }
}
