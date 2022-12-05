package com.bankguru.payment;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
import data.UserDataMapper;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.*;
import reportConfig.ExtentTestManager;
import ultilities.Environment;

import java.lang.reflect.Method;

public class Payment_01 extends BaseTest {

    @Parameters({"browser", "evnName", "ipAddress", "portNumber", "osName", "osVersion"})
    @BeforeClass
    public void beforeClass(@Optional("firefox") String browserName, @Optional("local") String evnName, @Optional("Windows") String osName, @Optional("10") String osVersion, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber) {
        String environmentName = System.getProperty("evn");
        ConfigFactory.setProperty("env", environmentName);
        env = ConfigFactory.create(Environment.class);

        driver = getBrowserDriver(browserName, env.appUrl(), evnName, osName, osVersion, ipAddress, portNumber);

        loginPage = PageGenerateManager.getLoginPage(driver);
        userData = UserDataMapper.getUserData();
        userID = "mngr460024";
        password = "EjyjEdE";

        customerName = userData.getCustomerName() + " " + generateFakeNumber();
        dateOfBirth = userData.getDateOfBirth();
        address = userData.getAddress();
        city = userData.getCity();
        state = userData.getState();
        pin = userData.getPin();
        mobileNumber = userData.getMobileNumber();
        email = userData.getEmail() + generateFakeNumber() + "@gmail.com";
        customerPassword = userData.getPassword();

        editAddress = userData.getAddress() + generateFakeNumber();
        editCity = userData.getCity() + generateFakeNumber();
        editState = userData.getState() + generateFakeNumber();
        editPin = userData.getEditPin();
        editMobileNumber = userData.getEditMobileNumber();
        editEmail = userData.getEmail() + generateFakeNumber() + "@gmail.com"

        log.info("Pre Condition - Step 01: Input to 'UserID' with value: '" + userID + "'");
        loginPage.inputToUserUI(userID);

        log.info("Pre Condition - Step 02: Input to 'Password' with value '" + password + "'");
        loginPage.inputPassword(password);

        log.info("Pre Condition - Step 03: Click to 'Login' button");
        homePage = loginPage.clickToLoginButton();

        log.info("Pre Condition - Step 04: Verify the Welcome message at Home Page is displayed");
        verifyTrue(homePage.isWelcomeMessageDisplayed());
    }

    @Test
    public void Payment_01_Create_New_Customer(Method method) {
        ExtentTestManager.startTest(method.getName(), "Create New Customer");
        ExtentTestManager.getTest().log(Status.INFO, "Create New Customer - Step 01: Open 'New Customer' page");
        homePage.clickToMenuLinkByMenuText("New Customer");
        newCustomerPage = PageGenerateManager.getNewCustomerPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Create New Customer - Step 02: Input to 'Customer Name' text box with value: '" + customerName + "'");
        newCustomerPage.inputToTextboxByName("name", customerName);

        ExtentTestManager.getTest().log(Status.INFO, "Create New Customer - Step 03: Select value in 'Gender' radio with value: 'Male'");
        newCustomerPage.selectItemAtGenderRadioByElementName("m");

        ExtentTestManager.getTest().log(Status.INFO, "Create New Customer - Step 04: Input to 'Date of Birth' date picker with value: '" + dateOfBirth + "'");
        newCustomerPage.inputToDateOfBirthDatePicker("05301993");

        ExtentTestManager.getTest().log(Status.INFO, "Create New Customer - Step 05: Input to 'Address' text area with value: '" + address + "'");
        newCustomerPage.inputToAddressTextArea(address);

        ExtentTestManager.getTest().log(Status.INFO, "Create New Customer - Step 06: Input to 'City' text box with value: '" + city + "'");
        newCustomerPage.inputToTextboxByName("city", city);

        ExtentTestManager.getTest().log(Status.INFO, "Create New Customer - Step 07: Input to 'State' text box with value: '" + state + "'");
        newCustomerPage.inputToTextboxByName("state", state);

        ExtentTestManager.getTest().log(Status.INFO, "Create New Customer - Step 08: Input to 'PIN' text box with value: '" + pin + "'");
        newCustomerPage.inputToTextboxByName("pinno", pin);

        ExtentTestManager.getTest().log(Status.INFO, "Create New Customer - Step 09: Input to 'Mobile Number' text box with value: '" + mobileNumber + "'");
        newCustomerPage.inputToTextboxByName("telephoneno", mobileNumber);

        ExtentTestManager.getTest().log(Status.INFO, "Create New Customer - Step 10: Input to 'Email' text box with value: '" + email + "'");
        newCustomerPage.inputToTextboxByName("emailid", email);

        ExtentTestManager.getTest().log(Status.INFO, "Create New Customer - Step 11: Input to 'Password' text box with value: '" + customerPassword + "'");
        newCustomerPage.inputToTextboxByName("password", customerPassword);

        ExtentTestManager.getTest().log(Status.INFO, "Create New Customer - Step 12: Click to 'Submit' button");
        newCustomerPage.clickToSubmitButton(driver);
        customerRegisSuccessPage = PageGenerateManager.getCustomerRegisSuccessPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Create New Customer - Step 13: Get 'Customer ID' when register success");
        customerID = customerRegisSuccessPage.getCustomerSuccessValueByTextField("Customer ID");

        ExtentTestManager.getTest().log(Status.INFO, "Create New Customer - Step 13: Verify the Create New Customer success message is displayed");
        verifyTrue(customerRegisSuccessPage.isRegisterSuccessMessageDisplayed());

        ExtentTestManager.getTest().log(Status.INFO, "Create New Customer - Step 14: Verify the 'Customer Name' is the same as the '" + customerName + "'");
        verifyEquals(customerRegisSuccessPage.getCustomerSuccessValueByTextField("Customer Name"), customerName);

        ExtentTestManager.getTest().log(Status.INFO, "Create New Customer - Step 15: Verify the 'Date of Birthday' is the same as the '" + dateOfBirth + "'");
        verifyEquals(customerRegisSuccessPage.getCustomerSuccessValueByTextField("Birthdate"), dateOfBirth);

        ExtentTestManager.getTest().log(Status.INFO, "Create New Customer - Step 16: Verify the 'Address' is the same as the '" + dateOfBirth + "'");
        verifyEquals(customerRegisSuccessPage.getCustomerSuccessValueByTextField("Address"), dateOfBirth);

        ExtentTestManager.getTest().log(Status.INFO, "Create New Customer - Step 16: Verify the 'City' is the same as the '" + city + "'");
        verifyEquals(customerRegisSuccessPage.getCustomerSuccessValueByTextField("City"), city);

        ExtentTestManager.getTest().log(Status.INFO, "Create New Customer - Step 17: Verify the 'State' is the same as the '" + state + "'");
        verifyEquals(customerRegisSuccessPage.getCustomerSuccessValueByTextField("State"), state);

        ExtentTestManager.getTest().log(Status.INFO, "Create New Customer - Step 18: Verify the 'Pin' is the same as the '" + pin + "'");
        verifyEquals(customerRegisSuccessPage.getCustomerSuccessValueByTextField("Pin"), pin);

        ExtentTestManager.getTest().log(Status.INFO, "Create New Customer - Step 19: Verify the 'Mobile Number' is the same as the '" + mobileNumber + "'");
        verifyEquals(customerRegisSuccessPage.getCustomerSuccessValueByTextField("Mobile No."), mobileNumber);

        ExtentTestManager.getTest().log(Status.INFO, "Create New Customer - Step 20: Verify the 'Email' is the same as the '" + email + "'");
        verifyEquals(customerRegisSuccessPage.getCustomerSuccessValueByTextField("Email"), email);

        ExtentTestManager.getTest().log(Status.INFO, "Create New Customer - Step 21: Click to 'Continue' link");
        homePage = customerRegisSuccessPage.clickToContinueLink();
    }

    @Test
    public void Payment_02_Edit_Customer(Method method) {
        ExtentTestManager.startTest(method.getName(), "Edit Customer");
        ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 01: Open 'Edit Customer' page");
        homePage.clickToMenuLinkByMenuText("Edit Customer");
        preEditCustomerPage = PageGenerateManager.getPreEditCustomerPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 02: Input to 'Customer ID' text box with value: '" + customerID + "'");
        preEditCustomerPage.inputToCustomerIDTextBox(customerID);

        ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 03: Click to 'Submit' button");
        preEditCustomerPage.clickToSubmitButton(driver);
        editCustomerPage = PageGenerateManager.getEditCustomerPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 04: Input edit customer address to 'Address' text box with value: '" + editAddress + "'");
        editCustomerPage.inputToTextboxByName("addr", editAddress);

        ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 05: Input edit customer city to 'City' text box with value: '" + editCity + "'");
        editCustomerPage.inputToTextboxByName("city", editCity);

        ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 06: Input edit customer state to 'State' text box with value: '" + editState + "'");
        editCustomerPage.inputToTextboxByName("state", editState);

        ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 07: Input edit customer pin to 'Pin' text box with value: '" + editPin + "'");
        editCustomerPage.inputToTextboxByName("pinno", editPin);

        ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 08: Input edit customer mobile number to 'Mobile Number' text box with value: '" + editMobileNumber + "'");
        editCustomerPage.inputToTextboxByName("telephoneno", editMobileNumber);

        ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 09: Input edit customer email to 'Email' text box with value: '" + editEmail + "'");
        editCustomerPage.inputToTextboxByName("emailid", editEmail);

        ExtentTestManager.getTest().log(Status.INFO, "Create New Customer - Step 10: Click to 'Submit' button");
        editCustomerPage.clickToSubmitButton(driver);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }

    private WebDriver driver;
    private String customerName, dateOfBirth, address, city, state, pin, mobileNumber, email, customerPassword;
    private String editAddress, editCity, editState, editPin, editMobileNumber, editEmail;
    private String userID, password, customerID;
    private UserDataMapper userData;
    Environment env;
    LoginPageObject loginPage;
    HomePageObject homePage;
    NewCustomerPageObject newCustomerPage;
    CustomerRegisSuccessPageObject customerRegisSuccessPage;
    PreEditCustomerPageObject preEditCustomerPage;
    EditCustomerPageObject editCustomerPage;
}
