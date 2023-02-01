package practice;

import base.CommonAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RahulshettyacademyHomePage;

import java.time.Duration;
import java.util.List;

import static java.sql.DriverManager.getDriver;

public class RadioButton extends CommonAPI {

        Logger LOG = LogManager.getLogger(RadioButton.class.getName());

        @Test
        public void test() throws InterruptedException {
            RahulshettyacademyHomePage rahulshettyacademyHomePag = new RahulshettyacademyHomePage(getDriver());
            rahulshettyacademyHomePag.selectRadioButton1(getDriver());
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
//    // the purpess of this method is to be able to handle the radioputons a nd if option as a parameter will turn into genirique method
//    @Test
//    public void test() throws InterruptedException {
//        List<WebElement> radioButtons=driver.findElements(By.xpath("//input[@name='radioButton']"));
//        for(WebElement radioButton:radioButtons){
//            if (radioButton.getAttribute("value").equals("radio1")){
//                radioButton.click();
//            }
//        }
//        Thread.sleep(3000);
//    }
//
//    @AfterMethod
//    public void TearDown(){
//        driver.close();
//    }
