package test;

import base.CommonAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utility.ConnectDB;

public class TestLogin extends CommonAPI {

    Logger LOG = LogManager.getLogger(TestLogin.class.getName());
    @Test
    public void invalidEmailAddress() throws InterruptedException {
        HomePage homePage=new HomePage(getDriver());
        LoginPage loginPage=new LoginPage(getDriver());
        String email= ConnectDB.getTableColumnData("select * from credentials","email").get(0);
         homePage.hoverOverFloatingMenu(getDriver());// replacing hoverOver("");// which replaces both of  actions.moveToElement(driver.findElement(By.xpath("//span[@id='nav-link-accountList-nav-line-1']"))).build().perform(); and Actions actions=new Actions(driver);//instance of the actions class that handle the mousse actions
        homePage.clickOnLoginButton();//replacing  clickOn("");     // which replace driver.findElement(By.xpath(" //div//a[@rel='nofollow']/span [@class='nav-action-inner']")).click();// (//span [@class='nav-action-inner'])[1]

        String actualTitle=getCurrentTitle();// which replaces String actualTitle=driver.getTitle();
        Assert.assertEquals(actualTitle,"Amazon Sign-In");
        LOG.info("title validation success");
       loginPage.typeEmailAddress(email);// typeText(" ","refsi05@gmail.com");// which replaces driver.findElement(By.xpath(" //input[@id='ap_email']")).sendKeys("refsi05@gmail.com");
       loginPage.clickOnConnectButton();// replacing clickOn(""); //which replaces driver.findElement(By.xpath("//input [@id='continue']")).click();

        String error=loginPage.getErrorMessage();  // String error=getTextFromElement(""); // String error=driver.findElement(By.xpath("//h4 [contains(text(),'There was a problem')]")).getText();
        //to grab a text
        Assert.assertEquals(error,"There was a problem");
        LOG.info("error message validation success");


    }
}
