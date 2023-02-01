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
import pages.JqueryuiHomePage;
import pages.RahulshettyacademyHomePage;

import java.time.Duration;

import static java.sql.DriverManager.getDriver;

public class CheckBox extends CommonAPI {

    Logger LOG = LogManager.getLogger( CheckBox.class.getName());
    @Test
    public void test() throws InterruptedException {
         RahulshettyacademyHomePage rahulshettyacademyHomePage = new RahulshettyacademyHomePage(getDriver());
        rahulshettyacademyHomePage.verifyOption2IfSelectedAmongSetOfExistingOptions();
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
//    //this method verify within set of checkboxes if specific checkbox is checked after selecting it
//    public void test() throws InterruptedException {
//        driver.findElement(By.xpath("//input[@id='checkBoxOption2']")).click();
//        Thread.sleep(3000);
//        System.out.println(driver.findElement(By.xpath("//input[@id='checkBoxOption1']")).isSelected());
//        System.out.println(driver.findElement(By.xpath("//input[@id='checkBoxOption2']")).isSelected());
//        System.out.println(driver.findElement(By.xpath("//input[@id='checkBoxOption3']")).isSelected());
//
//    }
//
//    @AfterMethod
//    public void TearDown(){
//        driver.close();
//    }
