package ru.kinopoisk;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Properties;

public class WebDriverSettings {
    public ChromeDriver driver;
    @Before
    public void setUp(){
        Properties props = new Properties();
        props.setProperty("webdriver.chrome.driver", "/Program Files/ChromeDriver/chromedriver");
        driver = new ChromeDriver();
        System.out.println("Инициализация завершена");

    }
    @After
    public void close(){
        System.out.println("Тест завершен");
        driver.quit();
    }
}
