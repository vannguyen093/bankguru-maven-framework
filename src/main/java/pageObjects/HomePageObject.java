package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.BasePageUI;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage {
    WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isWelcomeMessageDisplayed() {
        waitForElementVisible(driver, HomePageUI.WELCOME_HEADER);
        return isElementDisplayed(driver, HomePageUI.WELCOME_HEADER);
    }

    public void clickToMenuLinkByMenuText(String menuText) {
        waitForElementClickable(driver, BasePageUI.MENU_LINK_BY_MENU_TEXT, menuText);
        clickToElement(driver, BasePageUI.MENU_LINK_BY_MENU_TEXT, menuText);
    }
}
