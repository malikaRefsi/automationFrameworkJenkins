package base;


import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Optional;
//import pages.CheckoutPage;
import reporting.ExtentManager;
import reporting.ExtentTestManager;
import utility.Utility;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class CommonAPI {//"malikarefsi_XlpajD";//"savcKMWcbFXKst8Ysv6H";//
    /********************************************************/
    Logger LOG = LogManager.getLogger(CommonAPI.class.getName());

    String takeScreenshot = Utility.getProperties().getProperty("take.screenshot", "false");
    String maximizeBrowser = Utility.getProperties().getProperty("browser.maximize", "true");
    String implicitWait = Utility.getProperties().getProperty("implicit.wait", "10");//take this value by default if we did not include the implicit.wait in properties
    /********************************************************/
    String username=Utility.decode(Utility.getProperties().getProperty("browserstack-userName"));
    String password =Utility.decode(Utility.getProperties().getProperty("browserstack-passWord"));


    public WebDriver driver;//created outside to make it global

    /***************************************************************/
    //report setup from line 48 to 105
    public static com.relevantcodes.extentreports.ExtentReports extent;

    @BeforeSuite
    public void extentSetup(ITestContext context) {
        ExtentManager.setOutputDirectory(context);
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void startExtent(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName().toLowerCase();
        ExtentTestManager.startTest(method.getName());
        ExtentTestManager.getTest().assignCategory(className);
    }
    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    @AfterMethod
    public void afterEachTestMethod(ITestResult result) {
        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));
        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));

        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);
        }

        if (result.getStatus() == 1) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
        } else if (result.getStatus() == 2) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, getStackTrace(result.getThrowable()));
        } else if (result.getStatus() == 3) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
        }
        ExtentTestManager.endTest();
        extent.flush();
        if (takeScreenshot.equalsIgnoreCase("true")){
            if (result.getStatus() == ITestResult.FAILURE) {
                takeScreenshot(result.getName());
            }
        }
        driver.quit();
    }
    @AfterSuite
    public void generateReport() {
        extent.close();
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
    /********************************************************************/
    public void getLocalDriver(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            //how to iniciate a draver
            // WebDriverManager.chromedriver().setup(); //this it does which computer we are using and grab the dependency//after adding the dependency WebDriverManager we replace System.setProperty("webdriver.chromedriver","C:\\Users\\Malika Refsi\\IdeaProjects\\AutomationProject1\\driver");
            // this line set the location where the location where the chromedriver is located
            driver = new ChromeDriver();// create an instance of the web driver
        } else if (browserName.equalsIgnoreCase("firefox")) {
            //WebDriverManager.firefoxdriver().setup();//this statement can be deleted if we are using 4.6 or more selenium's version
            driver = new FirefoxDriver();
        }
    }

    public void getCloudDriver(String envName,String os,String osVersion,String browser,String browserVersion, String username, String password) throws MalformedURLException {
        DesiredCapabilities cap=new DesiredCapabilities();
        //these capabilities will be grabbed from the documentation of the websites that does the virtual automation
        cap.setCapability("os",os);
        cap.setCapability("os_version",osVersion);
        cap.setCapability("browser",browser);
        cap.setCapability("browser_version",browserVersion);
        if (envName.equalsIgnoreCase("browserstack")){
            cap.setCapability("resolution", "1024x768");
            driver = new RemoteWebDriver(new URL("http://"+username+":"+password+"@hub-cloud.browserstack.com:80/wd/hub"),cap);//        URL url1=new URL("http://malikarefsi_XlpajD:savcKMWcbFXKst8Ysv6H@hub-cloud.browserstack.com:80/wd/hub");//"http://username:password@hub-cloud.browserstack.com:80/wd/hub"
        } else if (envName.equalsIgnoreCase("saucelabs")) {
            driver = new RemoteWebDriver(new URL("http://"+username+":"+password+"@ondemand.saucelabs.com:80/wd.hub"),cap);
        }

    }

    @Parameters({"useCloudEnv","envName","os","osVersion","browserName","browserVersion","url"})
    @BeforeMethod
    public void setUp(@Optional("false") boolean useCloudEnv, @Optional("browserstack") String envName,
                      @Optional("windows") String os, @Optional("11") String osVersion,
                      @Optional("chrome") String browserName, @Optional("108") String browserVersion,
                      @Optional("https://www.google.com") String url) throws InterruptedException, MalformedURLException {
        if (useCloudEnv){
            getCloudDriver(envName, os,osVersion,browserName,browserVersion, username, password);
        }else {
            getLocalDriver(browserName);
        }
        /***********************************/
        if (maximizeBrowser.equalsIgnoreCase("true")){
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(implicitWait)));
        }
/******************************************/

        driver.manage().window().maximize();
        driver.get(url);// after run the will lunch

    }


    // generic methods

    public WebDriver getDriver() {
        return driver;
    }

    public void clickOn(WebElement element) {

        element.click();
    }

    public void typeText(WebElement element, String text) {
        element.sendKeys(text);
    }

    public String getTextFromElement(WebElement element) {
        return element.getText();
    }

    public void hoverOver(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);//instance of the actions class that handle the mousse actions
        actions.moveToElement(element).build().perform();
    }

    public String getCurrentTitle() {
        return driver.getTitle();
    }

    public void typeTextAndEnter(WebElement element, String text) {
        element.sendKeys(text, Keys.ENTER);
    }
    public void selectOptionFromDropdown(WebElement dropdown, String option){
        Select select = new Select(dropdown);
        try {
            select.selectByVisibleText(option);
        }catch (Exception e){
            select.selectByValue(option);
        }
    }

    // this method we call it whenever we take a screenshot
    public void waitForElementToBeVisible(WebDriver driver, int duration, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void captureScreenshot() {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);//all the screenshots will be stored in object of type file and named it file
        try {
            FileUtils.copyFile(file,new File("screenshots"+File.separator+"screenshot.png"));//this in order to copy that image we took and create a new folder and put the screenshot inside
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //but this method we call it explicitly when we want to take screenshot whenever there is failures
    public void takeScreenshot(String screenshotName){
        DateFormat df = new SimpleDateFormat("MMddyyyyHHmma");//this help us differentiate between the screenshots
        Date date = new Date();
        df.format(date);

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(Utility.path + File.separator +"screenshots"+ File.separator + screenshotName+" "+df.format(date)+".jpeg"));
            LOG.info("Screenshot captured");
        } catch (Exception e) {
            LOG.info("Exception while taking screenshot "+e.getMessage());
        }
    }

    /**************************************************************************************/
    /*********************************************************************************/
    public void FrameSwitchUsingIndex(WebDriver driver, int index) {
        driver.switchTo().frame(index);//since we have only one Iframe
        // LOG.info("Switch to Iframe success");
    }

    public void FrameSwitchUsingId(WebDriver driver, String id) {
        driver.switchTo().frame(id);//since we have only one Iframe
        // LOG.info("Switch to Iframe success");
    }

    public void DragAndDrop(WebDriver driver, WebElement draggable, WebElement droppable) {

        Actions actions = new Actions(driver);
        actions.dragAndDrop(draggable, droppable).build().perform();
//        the equivalence of dragAndDrop method:
//        actions.clickAndHold(draggable).pause(Duration.ofSeconds(4)).moveToElement(droppable).pause(Duration.ofSeconds(5)).release().pause(Duration.ofSeconds(5)).build().perform();

    }

    public Boolean verifyCheckBoxIfSelected(WebElement element) {
        return element.isSelected();
    }


//    public void verifyCheckBoxIfSelectedAmongSetOfExistingOptions(WebElement optionToSelect,WebElement option1,WebElement  option2,WebElement option3) throws InterruptedException {
//        clickOn(optionToSelect);
//        //optionToSelect.click();
//        Thread.sleep(3000);
//        LOG.info(verifyCheckBoxIfSelected(  option1));
//        LOG.info(verifyCheckBoxIfSelected( option2));
//        LOG.info(verifyCheckBoxIfSelected( option3));
//
//
//    }

    public Boolean checkIfElementIsDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public void switchToAlertAndAcceptIt(WebDriver driver) {
        driver.switchTo().alert().accept();
    }

    public void switchToAlertAndDismissIt(WebDriver driver) {
        driver.switchTo().alert().dismiss();
    }

    public String getTextFromAnAlert(WebDriver driver) {
        return driver.switchTo().alert().getText();
    }

    public void selectRadioButton(String option, WebDriver driver, List<WebElement> radioButtons) throws InterruptedException {
        for (WebElement radioButton : radioButtons) {
            if (radioButton.getAttribute("value").equals("option")) {
                clickOn(radioButton);
                //radioButton.click();
                Thread.sleep(3000);
            }
        }
        //Thread.sleep(3000);
    }

    public void scrollToElement(WebDriver driver, WebElement element) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        // this is an example of javascript click method js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//td[text()='Smith']")));

        Thread.sleep(3000);
    }

    public void selectAnOptionFromDropDown(WebDriver driver, WebElement element, String option) throws Exception {
        Select select = new Select(element);
        select.selectByVisibleText(option);
        Thread.sleep(3000);
    }

    public void arrowDownTwiceAndEnter(WebElement element) {
        element.sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
    }

    public void arrowDownOnce(WebElement element) {
        element.sendKeys(Keys.ARROW_DOWN);
    }

    public Set<String> getWindowHandles(WebDriver driver) {
        Set<String> windows = driver.getWindowHandles();
        return windows;
    }

    public void switchToNewWindow(WebDriver driver, Set<String> windows) {
        Iterator<String> itr = windows.iterator();
        String parent = itr.next();
        String newWin = itr.next();
        driver.switchTo().window(newWin);
    }

    public void switchBackToParentWindow(WebDriver driver, Set<String> windows) {
        Iterator<String> itr = windows.iterator();
        String parent = itr.next();
        String newWin = itr.next();
        driver.switchTo().window(parent);//switch back
    }
}
/*****************************************************************************************/
//                                     second update
/*****************************************************************************************/
//public class CommonAPI {//"malikarefsi_XlpajD";//"savcKMWcbFXKst8Ysv6H";//
//    String username=Utility.decode(Utility.getProperties().getProperty("browserstack-userName"));
//    String password =Utility.decode(Utility.getProperties().getProperty("browserstack-passWord"));
//
//
//    public WebDriver driver;//created outside to make it global
//
//    //Logger LOG = LogManager.getLogger( CommonAPI.class.getName());
//    public void getLocalDriver(String browserName) {
//        if (browserName.equalsIgnoreCase("chrome")) {
//            //how to iniciate a draver
//            // WebDriverManager.chromedriver().setup(); //this it does which computer we are using and grab the dependency//after adding the dependency WebDriverManager we replace System.setProperty("webdriver.chromedriver","C:\\Users\\Malika Refsi\\IdeaProjects\\AutomationProject1\\driver");
//            // this line set the location where the location where the chromedriver is located
//            driver = new ChromeDriver();// create an instance of the web driver
//        } else if (browserName.equalsIgnoreCase("firefox")) {
//            //WebDriverManager.firefoxdriver().setup();//this statement can be deleted if we are using 4.6 or more selenium's version
//            driver = new FirefoxDriver();
//        }
//    }
//    public void getCloudDriver(String envName,String os,String osVersion,String browser,String browserVersion, String username, String password) throws MalformedURLException {
//        DesiredCapabilities cap=new DesiredCapabilities();
//        //these capabilities will be grabbed from the documentation of the websites that does the virtual automation
//        cap.setCapability("os",os);
//        cap.setCapability("os_version",osVersion);
//        cap.setCapability("browser",browser);
//        cap.setCapability("browser_version",browserVersion);
//        if (envName.equalsIgnoreCase("browserstack")){
//            cap.setCapability("resolution", "1024x768");
//            driver = new RemoteWebDriver(new URL("http://"+username+":"+password+"@hub-cloud.browserstack.com:80/wd/hub"),cap);//        URL url1=new URL("http://malikarefsi_XlpajD:savcKMWcbFXKst8Ysv6H@hub-cloud.browserstack.com:80/wd/hub");//"http://username:password@hub-cloud.browserstack.com:80/wd/hub"
//        } else if (envName.equalsIgnoreCase("saucelabs")) {
//            driver = new RemoteWebDriver(new URL("http://"+username+":"+password+"@ondemand.saucelabs.com:80/wd.hub"),cap);
//        }
//
//    }
//
//    @Parameters({"useCloudEnv","envName","os","osVersion","browserName","browserVersion","url"})
//    @BeforeMethod
//    public void setUp(@Optional("false") boolean useCloudEnv, @Optional("browserstack") String envName,
//                      @Optional("windows") String os, @Optional("11") String osVersion,
//                      @Optional("chrome") String browserName, @Optional("108") String browserVersion,
//                      @Optional("https://www.google.com") String url) throws InterruptedException, MalformedURLException {
//        if (useCloudEnv){
//            getCloudDriver(envName, os,osVersion,browserName,browserVersion, username, password);
//        }else {
//            getLocalDriver(browserName);
//        }
//
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        //by this step we be able to open a browser
//        // and if I want to go some where to google do the following
//        driver.manage().window().maximize();
//        driver.get(url);// after run the will lunch
//
//
//        // since selenium is too fast I have to ask it to slow down by the following
//        Thread.sleep(2000);// 2 sec
//    }
//
//    @AfterMethod
//    public void shutBrowser() {
//// driver.close();
//        driver.quit();//since close() closes only the actual (parent) window and quit() close all the windows even the once that will be get open in the test
//    }
//
//
//    // generic methods
//
//    public WebDriver getDriver() {
//        return driver;
//    }
//
//    public void clickOn(WebElement element) {
//
//        element.click();
//    }
//
//    public void typeText(WebElement element, String text) {
//        element.sendKeys(text);
//    }
//
//    public String getTextFromElement(WebElement element) {
//        return element.getText();
//    }
//
//    public void hoverOver(WebDriver driver, WebElement element) {
//        Actions actions = new Actions(driver);//instance of the actions class that handle the mousse actions
//        actions.moveToElement(element).build().perform();
//    }
//
//    public String getCurrentTitle() {
//        return driver.getTitle();
//    }
//
//    public void typeTextAndEnter(WebElement element, String text) {
//        element.sendKeys(text, Keys.ENTER);
//    }
//
//    public void selectOptionFromDropdown(WebElement dropdown, String option) {
//        Select select = new Select(dropdown);//to put in drop dwon
//        try {
//            select.selectByVisibleText(option);
//        } catch (Exception e) {
//            select.deselectByValue(option);
//        }
//    }
//
//    /**************************************************************************************/
//    /*********************************************************************************/
//    public void FrameSwitchUsingIndex(WebDriver driver, int index) {
//        driver.switchTo().frame(index);//since we have only one Iframe
//        // LOG.info("Switch to Iframe success");
//    }
//
//    public void FrameSwitchUsingId(WebDriver driver, String id) {
//        driver.switchTo().frame(id);//since we have only one Iframe
//        // LOG.info("Switch to Iframe success");
//    }
//
//    public void DragAndDrop(WebDriver driver, WebElement draggable, WebElement droppable) {
//
//        Actions actions = new Actions(driver);
//        actions.dragAndDrop(draggable, droppable).build().perform();
//        //the equivalence of dragAndDrop method:
////        actions.clickAndHold(draggable).pause(Duration.ofSeconds(4))
////                .moveToElement(droppable).pause(Duration.ofSeconds(5)).release().pause(Duration.ofSeconds(5)).build().perform();
//
//    }
//
//    public Boolean verifyCheckBoxIfSelected(WebElement element) {
//        return element.isSelected();
//    }
//
//
////    public void verifyCheckBoxIfSelectedAmongSetOfExistingOptions(WebElement optionToSelect,WebElement option1,WebElement  option2,WebElement option3) throws InterruptedException {
////        clickOn(optionToSelect);
////        //optionToSelect.click();
////        Thread.sleep(3000);
////        LOG.info(verifyCheckBoxIfSelected(  option1));
////        LOG.info(verifyCheckBoxIfSelected( option2));
////        LOG.info(verifyCheckBoxIfSelected( option3));
////
////
////    }
//
//    public Boolean checkIfElementIsDisplayed(WebElement element) {
//        return element.isDisplayed();
//    }
//
//    public void switchToAlertAndAcceptIt(WebDriver driver) {
//        driver.switchTo().alert().accept();
//    }
//
//    public void switchToAlertAndDismissIt(WebDriver driver) {
//        driver.switchTo().alert().dismiss();
//    }
//
//    public String getTextFromAnAlert(WebDriver driver) {
//        return driver.switchTo().alert().getText();
//    }
//
//    public void selectRadioButton(String option, WebDriver driver, List<WebElement> radioButtons) throws InterruptedException {
//        for (WebElement radioButton : radioButtons) {
//            if (radioButton.getAttribute("value").equals("option")) {
//                clickOn(radioButton);
//                //radioButton.click();
//                Thread.sleep(3000);
//            }
//        }
//        //Thread.sleep(3000);
//    }
//
//    public void scrollToElement(WebDriver driver, WebElement element) throws InterruptedException {
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].scrollIntoView();", element);
//        // this is an example of javascript click method js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//td[text()='Smith']")));
//
//        Thread.sleep(3000);
//    }
//
//    public void selectAnOptionFromDropDown(WebDriver driver, WebElement element, String option) throws Exception {
//        Select select = new Select(element);
//        select.selectByVisibleText(option);
//        Thread.sleep(3000);
//    }
//
//    public void arrowDownTwiceAndEnter(WebElement element) {
//        element.sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
//    }
//
//    public void arrowDownOnce(WebElement element) {
//        element.sendKeys(Keys.ARROW_DOWN);
//    }
//
//    public Set<String> getWindowHandles(WebDriver driver) {
//        Set<String> windows = driver.getWindowHandles();
//        return windows;
//    }
//
//    public void switchToNewWindow(WebDriver driver, Set<String> windows) {
//        Iterator<String> itr = windows.iterator();
//        String parent = itr.next();
//        String newWin = itr.next();
//        driver.switchTo().window(newWin);
//    }
//
//    public void switchBackToParentWindow(WebDriver driver, Set<String> windows) {
//        Iterator<String> itr = windows.iterator();
//        String parent = itr.next();
//        String newWin = itr.next();
//        driver.switchTo().window(parent);//switch back
//    }
//}


    /*************************************************************************************/
    // the generic method used moving to the page object design
//    public void clickOn(String  cssOrXpath){
//        try {
//            driver.findElement(By.xpath(cssOrXpath)).click();
//        }catch (Exception e){ driver.findElement(By.cssSelector(cssOrXpath)).click();}
//
//
//    }
//    public void typeText(String  cssOrXpath,String text){
//        try {
//            driver.findElement(By.xpath(cssOrXpath)).sendKeys(text);
//        }catch (Exception e){ driver.findElement(By.cssSelector(cssOrXpath)).sendKeys(text);
//        }
//    }
//    public String getTextFromElement(String  cssOrXpath){
//        try {
//            return driver.findElement(By.xpath(cssOrXpath)).getText();
//        }catch (Exception e){return driver.findElement(By.cssSelector(cssOrXpath)).getText();
//        }
//    }
//
//    public void hoverOver(String  cssOrXpath){
//        Actions actions =new Actions(driver);//instance of the actions class that handle the mousse actions
//        try {
//            actions.moveToElement(driver.findElement(By.xpath(cssOrXpath))).build().perform();
//        }catch (Exception e){actions.moveToElement(driver.findElement(By.cssSelector(cssOrXpath))).build().perform();
//        }
//    }
//    public String getCurrentTitle(){
//        return driver.getTitle();
//    }
//
//    public void typeTextAndEnter(String  cssOrXpath,String text){
//        try {
//            driver.findElement(By.xpath(cssOrXpath)).sendKeys(text);
//        }catch (Exception e){ driver.findElement(By.cssSelector(cssOrXpath)).sendKeys(text, Keys.ENTER);
//        }
//    }
//    public void selectOptionFromDropdown(String  cssOrXpath,String option){
//        WebElement dropdown ;
//        try {
//            dropdown =driver.findElement(By.xpath(cssOrXpath));//finind the locator when selecting the drop down button
//            Select select=new Select(dropdown);//to put in drop dwon
//            try {
//                select.selectByVisibleText(option);
//            }catch (Exception e2){select.deselectByValue(option);}
//        }catch (Exception e){
//            dropdown =driver.findElement(By.cssSelector(cssOrXpath));//finind the locator when selecting the drop down button
//            Select select=new Select(dropdown);//to put in drop dwon
//            try {
//                select.selectByVisibleText(option);
//            }catch (Exception e2){select.deselectByValue(option);}
//        }
//    }

