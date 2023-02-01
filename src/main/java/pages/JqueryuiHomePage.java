package pages;

import base.CommonAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JqueryuiHomePage extends CommonAPI {
    Logger LOG = LogManager.getLogger(JqueryuiHomePage.class.getName());
    public  JqueryuiHomePage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    //objects
    @FindBy(xpath = "//div[@id='draggable']")
    WebElement draggableElement;
    @FindBy(xpath = "//div[@id='droppable']")
    WebElement droppableElement;

    //reusable steps inorder to make the code more better

    public void FrameSwitchFromDraggableToDroppableUsingIndex(WebDriver driver){
        driver.switchTo().frame(0);//since we have only one Iframe
        LOG.info("Switch Draggable To Droppable Iframe success");
    }


    public void DragSmallSquareAndDropInLargerSquare(WebDriver driver)  {
        DragAndDrop(driver,draggableElement,droppableElement);
        LOG.info("the small square dropped in the bigger square successfully");

    }





}
