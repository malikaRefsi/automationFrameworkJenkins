package pages;

import base.CommonAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Set;

public class RahulshettyacademyHomePage extends CommonAPI {
    Logger LOG = LogManager.getLogger(RahulshettyacademyHomePage.class.getName());
    public  RahulshettyacademyHomePage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    //objects
    @FindBy(xpath = "//a[text()='Job Support'][1]")
    WebElement JobSupportElement;
    @FindBy(xpath = "//input[@id='checkBoxOption1']")
    WebElement checkBoxOption1;
    @FindBy(xpath = "//input[@id='checkBoxOption2']")
    WebElement checkBoxOption2;
    @FindBy(xpath = "//input[@id='checkBoxOption3']")
    WebElement checkBoxOption3;
    @FindBy(xpath = "//input[@id='displayed-text']")
    WebElement HideAndShowTypeField;
    @FindBy(xpath = "//input[@id='hide-textbox']")
    WebElement HideButton;
    @FindBy(xpath = "//input[@id='show-textbox']")
    WebElement showButton;
    @FindBy(xpath = "//td[text()='Write effective QA Resume that will turn to interview call']")
    WebElement LastRowMidlColumnOfWebTableExampleElement;
    @FindBy(xpath = "//input[@id='alertbtn']")
    WebElement alertButton;
    @FindBy(xpath = "//input[@id='confirmbtn']")
    WebElement confirmButton;
    @FindAll({@FindBy(xpath = "//input[@name='radioButton']")})
    List<WebElement> radioButtons;
    @FindBy(xpath = "//td[text()='Smith']")
    WebElement SmithTheElementToScrollTo;
    @FindBy(xpath = "//select[@id='dropdown-class-example']")
    WebElement dropDownTypeField;
    @FindBy(xpath = "//input[@id='autocomplete']")
    WebElement dynamicDropDown;
    @FindBy(xpath = "//button[@id='openwindow']")
    WebElement openNewWindowButton;












    //reusable steps inorder to make the code better
    public void FrameSwitchFromHomeToJobSupportUsingIndex(WebDriver driver){
        driver.switchTo().frame(0);//since we have only one Iframe
        LOG.info("Switch Home To JobSupport Iframe success");
    }
    public void clickOnJobSupportLink(){
        clickOn(JobSupportElement);
        LOG.info("JobSupport link successfully selected");
    }


//    public void verifyOption2IfSelectedAmongSetOfExistingOptions() throws InterruptedException {
//        verifyCheckBoxIfSelectedAmongSetOfExistingOptions(checkBoxOption2,checkBoxOption1,checkBoxOption2,checkBoxOption3);
//
//    }
    public void verifyOption2IfSelectedAmongSetOfExistingOptions() throws InterruptedException {
        clickOn(checkBoxOption2);
        //optionToSelect.click();
        Thread.sleep(3000);
        LOG.info(verifyCheckBoxIfSelected( checkBoxOption1));
        LOG.info(verifyCheckBoxIfSelected( checkBoxOption2));
        LOG.info(verifyCheckBoxIfSelected( checkBoxOption3));

    }
    public void checkIfHideButtonDoesItsFunctionality(){
        LOG.info("Is the hide and show type field displayed after clicking Hide button?, the answer is: "+checkIfElementIsDisplayed(HideAndShowTypeField));
    }
    public void checkIfShowButtonDoesItsFunctionality(){

        LOG.info("Is the hide and show type field displayed?after clicking Show button, the answer is: "+checkIfElementIsDisplayed(HideAndShowTypeField));
    }
    public void typeWordInHideAndShowTypeField(String word){
        typeText(HideAndShowTypeField,word);
        LOG.info("word type success");
    }
    public void clickOnHideButton(){
        clickOn(HideButton);
        LOG.info("Hide Button successfully selected");
    }
    public void clickOnShowButton(){
        clickOn(showButton);
        LOG.info("Show Button successfully selected");
    }
    public String getTextFromHideAndShowTypeField(){
        return getTextFromElement(HideAndShowTypeField);
    }
    public String getTextFromTheLastRowMidlColumnOfWebTableExample(){
        return LastRowMidlColumnOfWebTableExampleElement.getText();
    }
    public void typeWordInAlertTypeField(String word){
        typeText(HideAndShowTypeField,word);
        LOG.info("text type success");
    }
    public void clickOnAlertButton(){
        clickOn(alertButton);
        LOG.info("Alert Button successfully selected");
    }
    public void clickOnConfirmButton(){
        clickOn(confirmButton);
        LOG.info("Confirm Button successfully selected");
    }
    public String getTextFromAlertBox(){
        return getTextFromElement(HideAndShowTypeField);
    }
   public void selectRadioButton1(WebDriver driver) throws InterruptedException {
        selectRadioButton("radio1",driver,radioButtons);
        LOG.info("The radio button selected successfully");
   }
    public void scrollDownToTheElementSmith(WebDriver driver) throws InterruptedException {
        scrollToElement(driver,SmithTheElementToScrollTo);
        LOG.info("The scroll to where word Smith situated success");
    }
    public void selectOption1FromDropDown(WebDriver driver) throws Exception{
        selectAnOptionFromDropDown(driver,dropDownTypeField,"Option1");
        LOG.info("Option1 successful selected");
    }
    public void typeTextIntoDynamicDropDown(String str) {
       typeText(dynamicDropDown,str);
    }
    public void arrowDownTwiceAndEnterInTheDynamicDropDown() {
        arrowDownTwiceAndEnter(dynamicDropDown);
    }
    public void clickOopenNewWindowButton(){
        clickOn(openNewWindowButton);
    }








}
