package practice;

import base.CommonAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RahulshettyacademyHomePage;
import test.TestLogin;

import java.time.Duration;

public class ScrollToElement extends CommonAPI {

    Logger LOG = LogManager.getLogger(ScrollToElement.class.getName());
    @Test

    public void test() throws InterruptedException {

        RahulshettyacademyHomePage rahulshettyacademyHomePag = new RahulshettyacademyHomePage(getDriver());
        rahulshettyacademyHomePag.scrollDownToTheElementSmith(getDriver());

    }

    }


//
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
//        JavascriptExecutor js=(JavascriptExecutor)driver;
//        js.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//td[text()='Smith']")));
//        // this is an example of javascript click method js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//td[text()='Smith']")));
//
//        Thread.sleep(3000);
//    }
//
//    @AfterMethod
//    public void TearDown(){
//        driver.close();
//    }