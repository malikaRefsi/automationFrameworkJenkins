package practice;

import base.CommonAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RahulshettyacademyHomePage;

import java.time.Duration;

public class StaticDropdown extends CommonAPI {

    Logger LOG = LogManager.getLogger(GetTextFromTable.class.getName());

    @Test
    public void test() throws Exception {
        RahulshettyacademyHomePage rahulshettyacademyHomePag = new RahulshettyacademyHomePage(getDriver());
        rahulshettyacademyHomePag.selectOption1FromDropDown(getDriver());
    }

    }

//    WebDriver driver;
//    @BeforeMethod
//    public void setUp(){
//        driver=new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.manage().window().maximize();
//        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
//
//    }
//@Test
//public void test() throws Exception{
//    WebElement webElement=driver.findElement(By.xpath("//select[@id='dropdown-class-example']"));
//    Select select=new Select(webElement);
//    select.selectByVisibleText("Option1");
//    Thread.sleep(3000);
//}
//    @AfterMethod
//    public void TearDown(){
//        driver.close();
//    }