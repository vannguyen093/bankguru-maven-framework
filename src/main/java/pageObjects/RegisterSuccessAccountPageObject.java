package pageObjects;

import commons.BasePage;
import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import pageUIs.CustomerRegisSuccessPageUI;
import pageUIs.NewAccountPageUI;
import pageUIs.RegisterSuccessAccountPageUI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterSuccessAccountPageObject extends BasePage {
    WebDriver driver;

    public RegisterSuccessAccountPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public String getAccountID() {
        waitForElementVisible(driver, RegisterSuccessAccountPageUI.ACCOUNT_ID_TEXT);
        return getElementText(driver, RegisterSuccessAccountPageUI.ACCOUNT_ID_TEXT);
    }

    public void writeAccountNoIntoTxtFile() {
        try {
            File file = new File(GlobalConstants.PROJECT_PATH + "/src/test/resources/accountID.txt");
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(getAccountID());
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isCreateAccountSuccessMessageDisplayed() {
        waitForElementVisible(driver, RegisterSuccessAccountPageUI.CREATE_NEW_ACCOUNT_SUCCESS_MESSAGE_TEXT);
        return isElementDisplayed(driver, RegisterSuccessAccountPageUI.CREATE_NEW_ACCOUNT_SUCCESS_MESSAGE_TEXT);
    }

    public String getCurrentAmount() {
        waitForElementVisible(driver, RegisterSuccessAccountPageUI.CURRENT_AMOUNT_TEXT);
        return getElementText(driver, RegisterSuccessAccountPageUI.CURRENT_AMOUNT_TEXT);
    }
}
