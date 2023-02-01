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
import pages.RahulshettyacademyNewWindow;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import static java.sql.DriverManager.getDriver;

public class HandleNewWindow extends CommonAPI {

    Logger LOG = LogManager.getLogger(HandleNewWindow.class.getName());

    @Test
    public void test() throws Exception {
        RahulshettyacademyHomePage rahulshettyacademyHomePag = new RahulshettyacademyHomePage(getDriver());
        RahulshettyacademyNewWindow rahulshettyacademyNewWindow = new RahulshettyacademyNewWindow(getDriver());
        LOG.info("The title of the current page is: " + getCurrentTitle());
        rahulshettyacademyHomePag.clickOopenNewWindowButton();
        Set<String> windows=getWindowHandles(getDriver());
        switchToNewWindow(getDriver(),windows);
        LOG.info("switch to new window success");
        Thread.sleep(3000);
        rahulshettyacademyNewWindow.clickOnContactButton();
        LOG.info("The title of the new window is: " + getCurrentTitle());
        switchBackToParentWindow(getDriver(),windows);
        LOG.info("switch back to parent window success");
        LOG.info("The title of the current (parent) page is: " + getCurrentTitle());




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
//        System.out.println(driver.getTitle());
//        driver.findElement(By.xpath(" //button[@id='openwindow']")).click();
//        Set<String> windows=driver.getWindowHandles();
//        Iterator<String> itr=windows.iterator();
//        String parent=itr.next();
//        String newWin=itr.next();
//        driver.switchTo().window(newWin);
//        System.out.println("switch to new window success");
//        Thread.sleep(3000);
//        driver.findElement(By.xpath("(//a[@href='https://www.rahulshettyacademy.com'])[8]")).click();
//        System.out.println(driver.getTitle());
//        driver.switchTo().window(parent);//switch back
//        System.out.println(driver.getTitle());
//        //driver.findElement(By.xpath("//button[@id='openwindow']")).click();
//    }
//
//    @AfterMethod
//    public void TearDown(){
//        driver.quit();
//    }