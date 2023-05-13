package ru.kinopoisk.Pages;

import org.hamcrest.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(className = "styles_loginButton__LWZQp")
    private WebElement loginButton;

    @FindBy(id = "passp-field-login")
    private WebElement loginField;

    @FindBy(id = "passp-field-passwd")
    private WebElement passwordField;

    @FindBy(id = "passp:sign-in")
    private WebElement buttonEnter;

    By loginWait = By.id("passp-field-login");
    By passwordWait = By.id("passp-field-passwd");

    public void EnterLogin(String login){
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginWait));
        loginField.sendKeys(login);
    }

    public void EnterPassword(String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordWait));
        passwordField.sendKeys(password);
    }

    public void ClickEnter() throws InterruptedException {
        buttonEnter.click();
        Thread.sleep(10000);
    }
}
