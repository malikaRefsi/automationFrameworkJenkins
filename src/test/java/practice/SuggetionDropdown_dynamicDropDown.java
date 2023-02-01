package practice;

import base.CommonAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RahulshettyacademyHomePage;

import java.time.Duration;

public class SuggetionDropdown_dynamicDropDown extends CommonAPI {

    Logger LOG = LogManager.getLogger(GetTextFromTable.class.getName());

    @Test
    public void howToSelectSecondOptionOfDynamicDropDown() throws InterruptedException {
        RahulshettyacademyHomePage rahulshettyacademyHomePag = new RahulshettyacademyHomePage(getDriver());
        rahulshettyacademyHomePag.typeTextIntoDynamicDropDown("can");
        Thread.sleep(3000);
        rahulshettyacademyHomePag.arrowDownTwiceAndEnterInTheDynamicDropDown();
        Thread.sleep(3000);
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
//    @Test
//    public void test() throws InterruptedException {
//        driver.findElement(By.xpath(" //input[@id='autocomplete']")).sendKeys("can");
//        Thread.sleep(3000);
//        driver.findElement(By.xpath(" //input[@id='autocomplete']")).sendKeys( Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ENTER);
//
//        Thread.sleep(3000);
//    }
//
//
//    @AfterMethod
//    public void TearDown(){
//        driver.close();
//    }