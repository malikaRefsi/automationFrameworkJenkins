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
import pages.HomePage;
import pages.RahulshettyacademyHomePage;
import test.TestLogin;

import java.time.Duration;

public class HandleFrame extends CommonAPI {

    Logger LOG = LogManager.getLogger(HandleFrame.class.getName());

    @Test
    public void test() throws InterruptedException {

       RahulshettyacademyHomePage rahulshettyacademyHomePag=new RahulshettyacademyHomePage(getDriver());
        rahulshettyacademyHomePag.FrameSwitchFromHomeToJobSupportUsingIndex(getDriver());
       Thread.sleep(1000);
        rahulshettyacademyHomePag.clickOnJobSupportLink();
        Thread.sleep(3000);
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
//        driver.switchTo().frame(0);//or the value id iframe wich includes the targeted element "courses-iframe"
//        System.out.println("Switch to Iframe success");
//        Thread.sleep(1000);
//        driver.findElement(By.xpath(" //a[text()='Job Support'][1]")).click();
//        Thread.sleep(3000);
//    }
//
//    @AfterMethod
//    public void TearDown(){
//        driver.close();
//    }

