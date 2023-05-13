package ru.kinopoisk.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(id = "from_year")
    private WebElement fromYearSelect;

    @FindBy(id = "to_year")
    private WebElement toYearSelect;

    @FindBy(id = "find_film")
    private WebElement filmNameField;

    @FindBy(css = "input.el_18.submit.nice_button[type='button']")
    private WebElement searchButton;

    @FindBy(css = "[href=\"/film/79850/sr/1/\"]")
    private WebElement film;

    @FindBy(xpath = "//h2[contains(text(), 'К сожалению, по вашему запросу ничего не найдено...')]")
    private WebElement element;

    @FindBy(css = "div.tdtext")
    private WebElement notification;

    By formMain = By.id("formSearchMain");

    public SearchPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void setDates(String FromYear, String ToYear){
        Select fromYearDropdown = new Select(fromYearSelect);
        Select toYearDropdown = new Select(toYearSelect);
        fromYearDropdown.selectByValue(FromYear);
        toYearDropdown.selectByValue(ToYear);
    }
    public void sendFilmName(String FilmName){
        filmNameField.sendKeys(FilmName);
    }
    public void clickSearch(){
        searchButton.click();
    }

    public Boolean filmDisplay(){
        if(film.isDisplayed()){
            return true;
        }else return false;
    }

    public Boolean MistakeDisplay(){
        if(element.isDisplayed()){
            return true;
        }else return false;
    }

    public Boolean NotificationDisplay(){
        if(notification.isDisplayed()){
            return true;
        }else return false;
    }
}
