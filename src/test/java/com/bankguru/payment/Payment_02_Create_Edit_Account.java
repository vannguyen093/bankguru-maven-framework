package com.bankguru.payment;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.*;
import reportConfig.ExtentTestManager;
import ultilities.DataHelper;
import ultilities.Environment;

import java.lang.reflect.Method;

public class Payment_02_Create_Edit_Account extends BaseTest {

    @Parameters({"browser", "evnName", "ipAddress", "portNumber", "osName", "osVersion"})
    @BeforeClass
    public void beforeClass(@Optional("firefox") String browserName, @Optional("local") String evnName, @Optional("Windows") String osName, @Optional("10") String osVersion, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber) {
        String environmentName = System.getProperty("evn");
        ConfigFactory.setProperty("env", environmentName);
        env = ConfigFactory.create(Environment.class);

        driver = getBrowserDriver(browserName, env.appUrl(), evnName, osName, osVersion, ipAddress, portNumber);
        loginPage = PageGenerateManager.getLoginPage(driver);
        accountType = "Savings";
        initialDeposit = String.valueOf(generateFakeNumber());
        userID = "mngr460024";
        password = "EjyjEdE";

        loginPage.inputToUserUI(userID);

        loginPage.inputPassword(password);

        homePage = loginPage.clickToLoginButton();

        verifyTrue(homePage.isWelcomeMessageDisplayed());
    }

    @Test
    public void Payment_03_Create_New_Account(Method method) {
        ExtentTestManager.startTest(method.getName(), "Create New Account with existing Customer ID");
        ExtentTestManager.getTest().log(Status.INFO, "Create New Account - Step 01: Open 'New Account' page");
        homePage.clickToMenuLinkByMenuText(driver, "New Account");
        newAccountPage = PageGenerateManager.getNewAccountPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Create New Account  - Step 02: Input to 'Customer ID' with existing customer ID before");
        newAccountPage.inputToCustomerIDTextBox();

        ExtentTestManager.getTest().log(Status.INFO, "Create New Account  - Step 03: Select item at 'Account type' dropdown with value: ''" + accountType + "'");
        newAccountPage.selectItemAtAccountTypeDropdown("Savings");

        ExtentTestManager.getTest().log(Status.INFO, "Create New Account  - Step 04: Input to 'Initial deposit' with value: '" + initialDeposit + "'");
        newAccountPage.inputToInitialDepositTextBox(initialDeposit);

        ExtentTestManager.getTest().log(Status.INFO, "Create New Account - Step 05: Click to 'Submit' button");
        newAccountPage.clickToSubmitButtonAtNewAccountPage();
        registerSuccessAccountPage = PageGenerateManager.getRegisterSuccessAccountPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Create New Account - Step 06: Write 'Account No.' into txt file");
        registerSuccessAccountPage.writeAccountNoIntoTxtFile();

        ExtentTestManager.getTest().log(Status.INFO, "Create New Account - Step 06: Verify the create successfully message is displayed");
        verifyTrue(registerSuccessAccountPage.isCreateAccountSuccessMessageDisplayed());

        ExtentTestManager.getTest().log(Status.INFO, "Create New Account - Step 07: Verify the 'Current Amount' is exactly with 'Initial deposit' value: '" + initialDeposit + "'");
        verifyEquals(registerSuccessAccountPage.getCurrentAmount(), initialDeposit);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }

    private WebDriver driver;
    private String accountType, initialDeposit;
    private String userID, password;
    Environment env;
    LoginPageObject loginPage;
    HomePageObject homePage;
    NewAccountPageObject newAccountPage;
    RegisterSuccessAccountPageObject registerSuccessAccountPage;
}
