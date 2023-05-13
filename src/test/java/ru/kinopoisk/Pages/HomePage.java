package ru.kinopoisk.Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "__next")
    private WebElement mainForm;
    @FindBy(css = "[href=\"/s/\"]")
    private WebElement searchLink;

    @FindBy(className = "styles_loginButton__LWZQp")
    private WebElement loginButton;

    @FindBy(xpath = "//a[@href='/user/6087449/go/']")
    private WebElement userButton;

    public HomePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public String getTitle(WebDriver driver){
        return driver.getTitle();
    }

    public void openHomePage(){
        driver.get("https://www.kinopoisk.ru/");
    }

    public void clickSearch(){
        searchLink.click();
    }

    public void clickProfile(){
        String href = userButton.getAttribute("href");
        driver.get(href);
    }

    public void clickLogin(){
        loginButton.click();
    }
}
