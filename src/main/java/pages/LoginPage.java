package pages;

import base.CommonAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CommonAPI {
    Logger LOG = LogManager.getLogger(LoginPage.class.getName());
    public  LoginPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    //objects or locators
    @FindBy(xpath = "//input[@id='ap_email']")
    WebElement emailField;
    @FindBy(xpath = "//input [@id='continue']")
    WebElement continueButton;
    @FindBy(xpath = "//h4 [contains(text(),'There was a problem')]")
    WebElement errorMessage;


    //reusable steps
    public void typeEmailAddress(String emailAddress){
        typeText(emailField,emailAddress);
        LOG.info("successfully typed in the email address");
    }
    public void clickOnConnectButton(){
        clickOn(continueButton);
        LOG.info("click connect button success");
    }
        public String getErrorMessage(){
        return getTextFromElement(errorMessage);

    }


}
