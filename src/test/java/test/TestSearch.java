package test;

import base.CommonAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utility.ReadFromExcel;

public class TestSearch extends CommonAPI {
    Logger LOG = LogManager.getLogger(TestSearch.class.getName());
    @Test
    public void searchJavaBook() throws InterruptedException {
        HomePage homePage=new HomePage(getDriver());
        ReadFromExcel readFromExcel=new ReadFromExcel("C:\\Users\\Malika Refsi\\IdeaProjects\\AutomationFrameWork\\data\\titles.xlsx","sheet1");


        // to test this step or verify that are taking us to the wright page we will compare the page titles
        //where the actuale will grabet from devtoolin the ui front back by serching title
        String expectedtitle1=readFromExcel.getCellValueForGivenHeaderAndKey("key","home page title");
        String actualResult1=getCurrentTitle();
        Assert.assertEquals(expectedtitle1,actualResult1);
        LOG.info("Tite validation success");
        LOG.info("Land to amazon webpage successful");
        homePage.typeItemToSearch("java book");// replacing this  typeText("","java book");//replace driver.findElement(By.id("twotabsearchtextbox")).sendKeys("java book");
        // this to fint the equivalent of the action ui front end and this because we want to enter a text 'java book'
       homePage.clickOnSearchButton();// replacing clickOn("");// replace driver.findElement(By.id("nav-search-submit-button")).click();
        //and this way we can automate all the steps
        // similarly we can do the test to this step
        String expectedtitle=readFromExcel.getCellValueForGivenHeaderAndKey("key","java serch title");
        String actualResult=getCurrentTitle();//replace String actualResult2=driver.getTitle();
        Assert.assertEquals(expectedtitle,actualResult);
        LOG.info("java book search Tile validation success");



    }
//    @Test
    public void searchSeleniumBook() throws InterruptedException {
        HomePage homePage=new HomePage(getDriver());
        String expectedHomePageTitle="Amazon.com. Spend less. Smile more.";
        String actualHomePageTitle=getCurrentTitle();
        Assert.assertEquals(expectedHomePageTitle,actualHomePageTitle);
        LOG.info("Tile validation success");
        LOG.info("Land to amazon webpage successful");
        homePage.typeItemToSearch("selenium book");// replacing typeText("","selenium book");// driver.findElement(By.id("twotabsearchtextbox")).sendKeys("selenium book");
        // this to fint the equivalent of the action ui front end and this because we want to enter a text 'java book'
        homePage.clickOnSearchButton(); //replacing clickOn("");// driver.findElement(By.id("nav-search-submit-button")).click();
        // similarly we can do the test to this step
        String expectedSearchPageTitle="Amazon.com : selenium book";
        String actualSearchPageTitle=getCurrentTitle();// replace  String actualResult2=driver.getTitle();
        Assert.assertEquals(expectedSearchPageTitle,actualSearchPageTitle);
        LOG.info("java book search Tile validation success");



    }
//    @Test
    public void searchJava() throws InterruptedException {
//dropdwn exmp
        HomePage homePage=new HomePage(getDriver());

        String expectedHomePageTitle="Amazon.com. Spend less. Smile more.";
        String actualHomePageTitle=getCurrentTitle();
        Assert.assertEquals(expectedHomePageTitle,actualHomePageTitle);
        LOG.info("Tile validation success");
        LOG.info("Land to amazon webpage successful");
        homePage.selectOptionFromMenuDropdown("Books");//replacing selectOptionFromDropdown("","Books");// this replace: WebElement dropdown =driver.findElement(By.id("searchDropdownBox"));//finind the locator when selecting the drop down button
//        Select select=new Select(dropdown);//to put in drop dwon
//        select.selectByVisibleText("Books");//to indicta what option selected


        //the locatores like twotabsearchtextbox is selected after doing all the steps needed like typing java then hit enter
        homePage.searchItem("java");//typeTextAndEnter("","java");// this replace driver.findElement(By.id("twotabsearchtextbox")).sendKeys("java", Keys.ENTER);// sendkeys is the respensible any typing
        // this to find the equivalent of the action ui front end and this because we want to enter a text 'java book'
        // similarly we can do the test to this step
        String expectedSearchPageTitle= "Amazon.com : java";
        String actualSearchPageTitle =getCurrentTitle();//replace String actualResult3 = driver.getTitle();
        Assert.assertEquals(expectedSearchPageTitle, actualSearchPageTitle);
        LOG.info("java book search Tile validation success");


    }
    }
