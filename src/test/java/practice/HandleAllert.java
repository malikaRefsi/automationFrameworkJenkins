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

import java.time.Duration;

public class HandleAllert extends CommonAPI {

    Logger LOG = LogManager.getLogger(HandleAllert.class.getName());

    @Test
    public void test() throws InterruptedException {
        RahulshettyacademyHomePage rahulshettyacademyHomePag=new RahulshettyacademyHomePage(getDriver());
        rahulshettyacademyHomePag.typeWordInAlertTypeField("malika refsi");
        rahulshettyacademyHomePag.clickOnAlertButton();
        LOG.info(getTextFromAnAlert(getDriver()));
        switchToAlertAndAcceptIt(getDriver());
        Thread.sleep(3000);
        //how to handle an alert with two options
       //first option
        rahulshettyacademyHomePag.typeWordInAlertTypeField("malika refsi");
        rahulshettyacademyHomePag.clickOnConfirmButton();
        LOG.info(getTextFromAnAlert(getDriver()));
        switchToAlertAndAcceptIt(getDriver());
        Thread.sleep(3000);
        //second option
        rahulshettyacademyHomePag.typeWordInAlertTypeField("malika refsi");
        rahulshettyacademyHomePag.clickOnConfirmButton();
        LOG.info(getTextFromAnAlert(getDriver()));
        switchToAlertAndDismissIt(getDriver());
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
//
//    @Test
//    public void test() throws InterruptedException {
//        //box will popup and ave only one choice to select which is force us to acsepte by that select ok
//        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("malika");// the element here is the field where we want to type
//        driver.findElement(By.xpath("//input[@id='alertbtn']")).click();// the element here is the clicked button
//        System.out.println(driver.switchTo().alert().getText());
//        driver.switchTo().alert().accept();
//        Thread.sleep(3000);
//
//
////since the pop up alert hase to options to chose frome which is accept and dismiss
//        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("malika");
//        driver.findElement(By.xpath("//input[@id='confirmbtn']")).click();
//        System.out.println(driver.switchTo().alert().getText());// to print out the message we will within the alert
//        driver.switchTo().alert().accept();//this statement will swich us to the alert box and we will accept it (click ok)
//        Thread.sleep(3000);
//
//        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("malika");
//        driver.findElement(By.xpath("//input[@id='confirmbtn']")).click();
//        System.out.println(driver.switchTo().alert().getText());
//        driver.switchTo().alert().dismiss();
//        Thread.sleep(3000);
//
//    }
//
//    @AfterMethod
//    public void TearDown(){
//        driver.close();
//    }
