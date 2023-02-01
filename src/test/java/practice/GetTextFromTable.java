package practice;

import base.CommonAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RahulshettyacademyHomePage;
import test.TestLogin;

import java.time.Duration;

public class GetTextFromTable extends CommonAPI {

    Logger LOG = LogManager.getLogger(GetTextFromTable.class.getName());

    @Test
    public void test(){
        RahulshettyacademyHomePage rahulshettyacademyHomePag=new RahulshettyacademyHomePage(getDriver());

        LOG.info(rahulshettyacademyHomePag.getTextFromTheLastRowMidlColumnOfWebTableExample());
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
//    public void test(){
//        String text= driver.findElement(By.xpath("//td[text()='Write effective QA Resume that will turn to interview call']")).getText();
//        System.out.println(text);
//    }
//
//    @AfterMethod
//    public void TearDown(){
//        driver.close();
//    }