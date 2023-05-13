package ru.kinopoisk;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.kinopoisk.Pages.HomePage;
import ru.kinopoisk.Pages.LoginPage;
import ru.kinopoisk.Pages.ProfilePage;
import ru.kinopoisk.Pages.SearchPage;

import java.time.Duration;

public class KinopoiskTest extends WebDriverSettings{

    Duration duration10sec = Duration.ofSeconds(10);

    @Test
    public void checkTitle(){
        driver.get("https://www.kinopoisk.ru/");
        WebDriverWait wait = new WebDriverWait(driver, duration10sec);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("__next")));
        String title = driver.getTitle();
        Assert.assertEquals("Кинопоиск. Все фильмы планеты.", title);
    }

    @Test
    public void findFilmPositive(){
        driver.get("https://www.kinopoisk.ru/");
        WebDriverWait wait = new WebDriverWait(driver, duration10sec);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("__next")));

        WebElement header = driver.findElement(By.id("__next"));
        header.findElement(By.cssSelector("[href=\"/s/\"]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("formSearchMain")));
        WebElement form = driver.findElement(By.id("formSearchMain"));

        form.findElement(By.id("find_film")).sendKeys("Ночной дозор");

        form.findElement(By.cssSelector("input.el_18.submit.nice_button[type='button']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("block_left_pad")));

        WebElement film = driver.findElement(By.id("block_left_pad"));
        film.findElement(By.cssSelector("[href=\"/film/79850/sr/1/\"]")).click();

        WebElement element = driver.findElement(By.xpath("//*[contains(text(), 'Ночной дозор (2004)')]"));
        Assert.assertTrue(element.isDisplayed()==true);
    }
    @Test
    public void findFilmNegative(){
        driver.get("https://www.kinopoisk.ru/");

        WebDriverWait wait = new WebDriverWait(driver, duration10sec);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("__next")));

        WebElement header = driver.findElement(By.id("__next"));
        header.findElement(By.cssSelector("[href=\"/s/\"]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("formSearchMain")));
        WebElement form = driver.findElement(By.id("formSearchMain"));

        form.findElement(By.id("find_film")).sendKeys("АБВГДЖЗЕКЛМНОПРОА");
        form.findElement(By.cssSelector("input.el_18.submit.nice_button[type='button']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("block_left_pad")));

        WebElement element = driver.findElement(By.xpath("//h2[contains(text(), 'К сожалению, по вашему запросу ничего не найдено...')]"));
        Assert.assertTrue(element.isDisplayed()==true);
    }

    @Test
    public void findForElevenYearsNegative(){
        driver.get("https://www.kinopoisk.ru/");

        WebDriverWait wait = new WebDriverWait(driver, duration10sec);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("__next")));

        WebElement header = driver.findElement(By.id("__next"));
        header.findElement(By.cssSelector("[href=\"/s/\"]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("formSearchMain")));

        WebElement fromYearSelect = driver.findElement(By.id("from_year"));
        WebElement toYearSelect = driver.findElement(By.id("to_year"));

        // Создаем объекты Select для элементов select
        Select fromYearDropdown = new Select(fromYearSelect);
        Select toYearDropdown = new Select(toYearSelect);

        fromYearDropdown.selectByValue("2009");
        toYearDropdown.selectByValue("2020");
        WebElement element = driver.findElement(By.cssSelector("div.tdtext"));
        Assert.assertTrue(element.isDisplayed()==true);
    }

    @Test
    public void LoginChangeLinkInProfile(){
        driver.get("https://www.kinopoisk.ru/");

        WebDriverWait wait = new WebDriverWait(driver, duration10sec);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("__next")));

        WebElement loginButton = driver.findElement(By.className("styles_loginButton__LWZQp"));
        loginButton.click();

        wait = new WebDriverWait(driver, duration10sec);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passp-field-login")));

        WebElement loginField = driver.findElement(By.id("passp-field-login"));
        loginField.sendKeys("bulat.almuchammetov-smartf");

        driver.findElement(By.id("passp:sign-in")).click();

        wait = new WebDriverWait(driver, duration10sec);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passp-field-passwd")));

        WebElement passwordInput = driver.findElement(By.name("passwd"));
        passwordInput.sendKeys("1232Bbilly");

        WebElement loginBtn = driver.findElement(By.id("passp:sign-in"));
        loginBtn.click();

        wait = new WebDriverWait(driver, duration10sec);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("__next")));
        // Найти элемент, содержащий ссылку
        WebElement linkElement = driver.findElement(By.xpath("//a[@href='/user/6087449/go/']"));

        String href = linkElement.getAttribute("href");
        driver.get(href);

        wait = new WebDriverWait(driver, duration10sec);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("profileInfoWrap")));
        WebElement profileInfoWrap = driver.findElement(By.id("profileInfoWrap"));
        profileInfoWrap.findElement(By.cssSelector("[href=\"/mykp/edit_main/\"]")).click();
        WebElement vkontakteInput = driver.findElement(By.name("edit[main][social][vkontakte]"));

        vkontakteInput.sendKeys("https://twitter.com/ev");
        //vkontakteInput.sendKeys("https://vk.com/durov");

        WebElement saveButton = driver.findElement(By.id("js-save-edit-form"));
        saveButton.click();

        WebElement isNoticePresent = driver.findElement(By.id("ui_notice_1"));
        Assert.assertTrue(isNoticePresent.isDisplayed()==true);
    }

    @Test
    public void test1(){
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.openHomePage();
        String title = homePage.getTitle(driver);
        Assert.assertEquals("Кинопоиск. Все фильмы планеты.", title);
    }

    @Test
    public void test2(){
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.openHomePage();
        homePage.clickSearch();
        SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
        String FilmName = "Ночной дозор";
        searchPage.sendFilmName(FilmName);
        searchPage.clickSearch();
        Assert.assertTrue(searchPage.filmDisplay());
    }

    @Test
    public void test3(){
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.openHomePage();
        homePage.clickSearch();
        SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
        String FilmName = "АБВГДЖЗЕКЛМНОПРОА";
        searchPage.sendFilmName(FilmName);
        searchPage.clickSearch();
        Assert.assertTrue(searchPage.MistakeDisplay());
    }

    @Test
    public void test4(){
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.openHomePage();
        homePage.clickSearch();
        SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
        String dateFrom = "2009";
        String dateTo = "2020";
        searchPage.setDates(dateFrom, dateTo);
        Assert.assertTrue(searchPage.NotificationDisplay());

    }

    @Test
    public void test5() throws InterruptedException {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.openHomePage();
        homePage.clickLogin();
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        String login = "bulat.almuchammetov-smartf";
        String password = "1232Bbilly";
        loginPage.EnterLogin(login);
        loginPage.ClickEnter();
        loginPage.EnterPassword(password);
        loginPage.ClickEnter();
        homePage.clickProfile();
        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);
        profilePage.EnterLink("https://twitter.com/ev");
        profilePage.clickSave();
        Assert.assertTrue(profilePage.NotificationDisplay());
    }

}
