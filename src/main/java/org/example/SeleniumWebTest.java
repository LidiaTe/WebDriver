package org.example;

import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumWebTest {
    private WebDriver driver;
    //private Logger log = LogManager.getLogger( Test1.class );


    private final String LOGIN = "kipohe9581@mahmul.com";
    // private final String LOGIN = Properties.getPropertyValue("login");
    private final String PASSWORD = "@WSX1qaz";

//    @Test
//        public void test() {
//            log.fatal("fatal");
//        }

    @BeforeAll
    public static void webDriverSetup(){
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    public void setUp() {

         driver = new ChromeDriver();

        //log.info( "Драйвер запущен" );
    }

    @AfterEach
    public void closeAll() {
        if (driver != null){
            driver.close();
            driver.quit();
        }
       // log.info( "Драйвер закрыт" );
    }

    @Test
    public void firstTest() {

        /* Шаги теста:

Открыть https://otus.ru
Авторизоваться на сайте
Войти в личный кабинет
В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
Нажать сохранить
Открыть https://otus.ru в "чистом браузере"
Авторизоваться на сайе
Войти в личный кабинет
Проверить, что в разделе "О себе" отображаются указанные ранее данные
Домашнее задание принимается в виде ссылки на GitHub репозиторий */



        //Открыть https://otus.ru
        driver.get( "https://otus.ru" );

        //Авторизоваться на сайте
        auth();

        //Войти в личный кабинет
        enterToLK();

        //В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
        clearAndEnter(By.cssSelector("#id_fname"), "Лидия");
        clearAndEnter(By.cssSelector("#id_fname_latin"), "Lidia");
        clearAndEnter(By.cssSelector("#id_lname"), "Тестова");
        clearAndEnter(By.cssSelector("#id_lname_latin"), "Testova");
        clearAndEnter(By.cssSelector("#id_blog_name"), "Testova blog");
        clearAndEnter(By.cssSelector("[name=\"date_of_birth\"]"), "02.02.1992");


        //Нажать сохранить
        driver.findElement(By.cssSelector("[name=\"continue\"]")).click();

        //Открыть https://otus.ru в "чистом браузере"
        driver.quit();
        driver = new ChromeDriver();
        driver.get( "https://otus.ru" );

        //Авторизоваться на сайте
        auth();

        //Войти в личный кабинет
        enterToLK();

        //Проверить, что в разделе "О себе" отображаются указанные ранее данные
        assertThat(By.cssSelector("#id_fname"), "Лидия");
        assertThat(By.cssSelector("#id_fname_latin"), "Lidia");
        assertThat(By.cssSelector("#id_lname"), "Тестова");
        assertThat(By.cssSelector("#id_lname_latin"), "Testova");
        assertThat(By.cssSelector("#id_blog_name"), "Testova blog");
        assertThat(By.cssSelector("[name=\"date_of_birth\"]"), "02.02.1992");




        //Assertions.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям", driver.getTitle());

    }

    private void auth() {
        new WebDriverWait( driver, Duration.ofSeconds( 10 ) ).until( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( ".sc-mrx253-0" ) ) );
        driver.findElement( By.cssSelector( ".sc-mrx253-0" ) ).click();
        //login
        driver.findElement( By.cssSelector( "#__PORTAL__ > div > div > div.sc-1alnis6-1.ejcuap > div.sc-1alnis6-4.iVBbVz > div > div.sc-10p60tv-1.eDzhKh > div.sc-10p60tv-2.bQGCmu > div > div.sc-19qj39o-0.iLmCeO > div > div.sc-rq8xzv-1.hGvqzc.sc-11ptd2v-1.liHMCp > div > input" ) ).sendKeys( LOGIN );
        //.sc-rq8xzv-4.dCdvLX
        //driver.findElement( By.cssSelector( ".sc-rq8xzv-4.dCdvLX" ) ).sendKeys( LOGIN );


        //password
        driver.findElement( By.cssSelector( "#__PORTAL__ > div > div > div.sc-1alnis6-1.ejcuap > div.sc-1alnis6-4.iVBbVz > div > div.sc-10p60tv-1.eDzhKh > div.sc-10p60tv-2.bQGCmu > div > div.sc-19qj39o-0.iLmCeO > div > div.sc-rq8xzv-1.hGvqzc.sc-11ptd2v-1-Component.ciraFX > div > input" ) ).sendKeys( PASSWORD );
       //sc-11ptd2v-1-Component
       // driver.findElement( By.className( "sc-11ptd2v-1-Component" ) ).sendKeys( PASSWORD );

        driver.findElement( By.cssSelector( "#__PORTAL__ > div > div > div.sc-1alnis6-1.ejcuap > div.sc-1alnis6-4.iVBbVz > div > div.sc-10p60tv-1.eDzhKh > div.sc-10p60tv-2.bQGCmu > div > button > div" ) ).click();
        new WebDriverWait( driver, Duration.ofSeconds( 5 ) ).until( ExpectedConditions.invisibilityOf( driver.findElement(  By.cssSelector("#__PORTAL__ > div")) ) );

    }

    private void enterToLK() {
        driver.get( "https://otus.ru/lk/biography/personal/" );

        new WebDriverWait( driver, Duration.ofSeconds( 10 ) ).until( ExpectedConditions.visibilityOf( driver.findElement(  By.cssSelector("#id_lname")) ));

    }

    private void clearAndEnter(By by, String text) {
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(text);
    }

    private void assertThat(By by, String text) {
        Assertions.assertEquals(text, driver.findElement(by).getAttribute("value"));
    }

}