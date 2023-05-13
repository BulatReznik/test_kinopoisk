package ru.kinopoisk.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.WatchEvent;
import java.time.Duration;

public class ProfilePage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "profileInfoWrap")
    private WebElement profileInfoWrap;

    @FindBy(css = "[href=\"/mykp/edit_main/\"]")
    private WebElement searchButton;

    @FindBy(name = "edit[main][social][vkontakte]")
    private  WebElement vkontakteInput;

    @FindBy(id = "js-save-edit-form")
    private  WebElement saveButton;

    @FindBy(id = "ui_notice_1")
    private WebElement notice;

    public ProfilePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void EnterLink(String link){
        vkontakteInput.sendKeys(link);
    }
    public void clickSave(){
        saveButton.click();
    }

    public Boolean NotificationDisplay(){
        if(notice.isDisplayed()){
            return true;
        }else return false;
    }
}
