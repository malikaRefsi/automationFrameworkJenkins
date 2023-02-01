package pages;

import base.CommonAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends CommonAPI {
    Logger LOG = LogManager.getLogger(HomePage.class.getName());
    public  HomePage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    //objects
    @FindBy(xpath = "//*[@id='twotabsearchtextbox']")
    WebElement searchField;
    @FindBy(css = "#nav-search-submit-button")
    WebElement searchButton;
    @FindBy(xpath = "//span[@id='nav-link-accountList-nav-line-1']")
    WebElement floatingMenu;//hover over
    @FindBy(xpath = "//div//a[@rel='nofollow']/span [@class='nav-action-inner']")
    WebElement loginButton;
    @FindBy(css = "#searchDropdownBox")
    WebElement menuDropDown;

    //reusable steps inorder to make the code more better
    public void typeItemToSearch(String item){
        typeText(searchField,item);
        LOG.info("Item name type success");
    }
    public void clickOnSearchButton(){
        clickOn(searchButton);
        LOG.info("sign in button successfully selected");
    }
    public void searchItem(String item){
        typeTextAndEnter(searchField,item);
        LOG.info("Item name type and enter success");
    }
    public void selectOptionFromMenuDropdown(String option){
        selectOptionFromDropdown(menuDropDown,option);
        LOG.info("the "+option+" option successfully selected from the drop down");
    }

    public void hoverOverFloatingMenu(WebDriver driver){
        hoverOver(driver,floatingMenu);
        LOG.info("the hover over menu success");
    }
    public void clickOnLoginButton(){
        clickOn(loginButton);
        LOG.info("click on the login button success");
    }


}
