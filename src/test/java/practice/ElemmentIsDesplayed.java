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

public class ElemmentIsDesplayed extends CommonAPI {

    Logger LOG = LogManager.getLogger(ElemmentIsDesplayed.class.getName());

    @Test
    public void test() throws InterruptedException {
        RahulshettyacademyHomePage rahulshettyacademyHomePag=new RahulshettyacademyHomePage(getDriver());
        rahulshettyacademyHomePag.typeWordInHideAndShowTypeField("malika");
        rahulshettyacademyHomePag.clickOnHideButton();
        Thread.sleep(3000);
        rahulshettyacademyHomePag.checkIfHideButtonDoesItsFunctionality();
        rahulshettyacademyHomePag.clickOnShowButton();
        Thread.sleep(3000);
        rahulshettyacademyHomePag.checkIfShowButtonDoesItsFunctionality();
        LOG.info(rahulshettyacademyHomePag.getTextFromHideAndShowTypeField());

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
//        driver.findElement(By.xpath("//input[@id='displayed-text']")).sendKeys("malika");
//        driver.findElement(By.xpath("//input[@id='hide-textbox']")).click();//the element her is the hide button
//        Thread.sleep(3000);
//        System.out.println(driver.findElement(By.xpath("//input[@id='displayed-text']")).isDisplayed());
//        driver.findElement(By.xpath("//input[@id='show-textbox']")).click(); //the element her is the show button
//        Thread.sleep(3000);
//        System.out.println(driver.findElement(By.xpath("//input[@id='displayed-text']")).isDisplayed());
//        System.out.println(driver.findElement(By.xpath("//input[@id='displayed-text']")).getText());
//    }
//
//    @AfterMethod
//    public void TearDown(){
//        driver.close();
//    }
