package junitcucumber.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private static int LINK_PRESENSE_TIMEOUT = 60;

    @FindBy(id = "mailbox:login")
    private WebElement loginField;

    @FindBy(id = "mailbox:password")
    private WebElement passwordField;

    @FindBy(id = "PH_logoutLink")
    private WebElement logoutLink;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage enterLogin(String login){
        (new WebDriverWait(driver, LINK_PRESENSE_TIMEOUT )).until(ExpectedConditions.visibilityOf(loginField));
        loginField.sendKeys(login);
        return this;
    }

    public LoginPage enterPassword(String password){
        passwordField.sendKeys(password);
        return this;
    }

    public HomePage login(){
        passwordField.submit();
        (new WebDriverWait(driver, LINK_PRESENSE_TIMEOUT )).until(ExpectedConditions.visibilityOf(logoutLink));
        return new HomePage(driver);
    }

}
