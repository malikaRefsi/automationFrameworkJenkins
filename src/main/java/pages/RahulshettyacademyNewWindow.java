package pages;

import base.CommonAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RahulshettyacademyNewWindow extends CommonAPI {
    Logger LOG = LogManager.getLogger(RahulshettyacademyNewWindow.class.getName());
    public RahulshettyacademyNewWindow(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    //object
    @FindBy(xpath = "(//a[@href='https://www.rahulshettyacademy.com'])[8]")
    WebElement contactButton;

    //reusable steps inorder to make the code better
    public void clickOnContactButton(){
        clickOn(contactButton);
    }

}
