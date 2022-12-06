package pageObjects;

import commons.BasePage;
import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import pageUIs.CustomerRegisSuccessPageUI;
import pageUIs.NewAccountPageUI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class NewAccountPageObject extends BasePage {
    WebDriver driver;

    public NewAccountPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void inputToCustomerIDTextBox() {
        waitForElementVisible(driver, NewAccountPageUI.CUSTOMER_ID_TEXTBOX);
        sendkeysToElement(driver, NewAccountPageUI.CUSTOMER_ID_TEXTBOX, readCustomerIdFromTxtFile());
    }

    public void selectItemAtAccountTypeDropdown(String itemText) {
        waitForElementClickable(driver, NewAccountPageUI.ACCOUNT_TYPE_DROPDOWN);
        selectItemInDefaultDropdown(driver, NewAccountPageUI.ACCOUNT_TYPE_DROPDOWN, itemText);
    }

    public void inputToInitialDepositTextBox(String value) {
        waitForElementVisible(driver, NewAccountPageUI.INITIAL_DEPOSIT_TEXTBOX);
        sendkeysToElement(driver, NewAccountPageUI.INITIAL_DEPOSIT_TEXTBOX, value);
    }

    public void clickToSubmitButtonAtNewAccountPage() {
        waitForElementClickable(driver, NewAccountPageUI.SUBMIT_BUTTON);
        clickToElement(driver, NewAccountPageUI.SUBMIT_BUTTON);
    }

}
