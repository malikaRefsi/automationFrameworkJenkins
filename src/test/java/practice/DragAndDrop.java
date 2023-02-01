package practice;

import base.CommonAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.JqueryuiHomePage;
import test.TestLogin;
import org.testng.TestNG;


import javax.swing.*;
import java.time.Duration;

public class DragAndDrop extends CommonAPI {

    Logger LOG = LogManager.getLogger(DragAndDrop.class.getName());
    @Test
    public void test() throws InterruptedException {
        JqueryuiHomePage jqueryuihomePage=new JqueryuiHomePage(getDriver());
        jqueryuihomePage.FrameSwitchFromDraggableToDroppableUsingIndex(getDriver());
        jqueryuihomePage.DragSmallSquareAndDropInLargerSquare(getDriver());
        Thread.sleep(3000);
    }



    /**************************************************************************/
//    WebDriver driver;
//    @BeforeMethod
//    public void setUp(){
//        driver=new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.manage().window().maximize();
//        driver.get("https://jqueryui.com/droppable/");
//
//    }
//
//    @Test
//    public void test() throws InterruptedException {
//        driver.switchTo().frame(0);//since we have only one Iframe
//        LOG.info("Switch to Iframe success");
//        WebElement draggable= driver.findElement(By.xpath("//div[@id='draggable']"));
//        WebElement  droppable=  driver.findElement(By.xpath("//div[@id='droppable']"));
//        Actions actions=new Actions(driver);
//        actions.dragAndDrop(draggable,droppable).build().perform();
//        Thread.sleep(3000);
//    }
//
//    @AfterMethod
//    public void TearDown(){
//        driver.close();
//    }
}
