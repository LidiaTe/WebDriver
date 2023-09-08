package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class WebDriverTest {
    private WebDriver driver;
    private final String LOGIN = "kipohe9581@mahmul.com";
    private final String PASSWORD = "@WSX1qaz";
    private Logger log = LogManager.getLogger( WebDriverTest.class );


    @BeforeAll
    public static void webDriverSetup(){
        WebDriverManager.chromedriver().setup();
    }
//    @BeforeEach
//    public void setUp() {
//
//        driver = new ChromeDriver();
//        log.info("Драйвер открыт");
//    }

    @AfterEach
    public void closeAll() {
        if (driver != null){
            driver.close();
            driver.quit();
            log.info("Драйвер закрыт");
        }
    }

    @Test
    public void firstTest() throws InterruptedException {
//1)
//
//    Открыть Chrome в headless режиме
//    Перейти на https://duckduckgo.com/
//    В поисковую строку ввести ОТУС
//    Проверить что в поисковой выдаче первый результат Онлайн‑курсы для профессионалов, дистанционное обучение

        //    Открыть Chrome в headless режиме
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments( "headless" );
        driver = new ChromeDriver(chromeOptions);

        //    Перейти на https://duckduckgo.com/
        driver.get( "https://duckduckgo.com/" );

        //ждем
        //время ожидания появления элементов мс
        // driver.manage().timeouts().implicitlyWait( Duration.ofSeconds( 10 ) );
        //время ожидания загрузки страницы мс
       // driver.manage().timeouts().pageLoadTimeout( Duration.ofSeconds( 10 ) );


        //    В поисковую строку ввести ОТУС
        //#search_form_input_homepage  driver.findElement(by).sendKeys(text);
        driver.findElement( By.xpath( "//*[@id=\"search_form_input_homepage\"]" ) ).sendKeys( "ОТУС" );

        //#search_button_homepage Нажимаем поиск
        driver.findElement( By.cssSelector( "#search_button_homepage" ) ).click();

        // получаем текст из поисковой выдачи
        String text = driver.findElement( By.cssSelector( "#r1-0 > div.ikg2IXiCD14iVX7AdZo1 > h2 > a > span" ) ).getText();

        //ждем
       // sleep( 1500 );
        new WebDriverWait( driver, Duration.ofSeconds( 10 ) ).until( ExpectedConditions.visibilityOf( driver.findElement(  By.cssSelector("#r1-0 > div.ikg2IXiCD14iVX7AdZo1 > h2 > a > span")) ));

        //    Проверить что в поисковой выдаче первый результат Онлайн‑курсы для профессионалов, дистанционное обучение
        String text2 = "Онлайн‑курсы для профессионалов, дистанционное обучение современным ...";
        //String text2 = "Otus: Онлайн-курсы для ррофессионалов";

        Assertions.assertEquals(text, text2);
    }

    @Test
    public void secondTest() throws InterruptedException {

//2)
//
//    Открыть Chrome в режиме киоска
//    Перейти на https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818
//    Нажать на любую картинку
//    Проверить что картинка открылась в модальном окне

        //    Открыть Chrome в режиме киоска
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments( "kiosk" );
        driver = new ChromeDriver(chromeOptions);

        //    Перейти на https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818
        driver.get( "https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818" );

        //    Нажать на любую картинку
        driver.findElement( By.cssSelector( "body > section.w3-gallery.py-5 > div > ul.portfolio-area.clearfix.p-0.m-0 > li:nth-child(1) > span > a > div.content-overlay" ) )
                .click();

        //    Проверить что картинка открылась в модальном окне
        //ждем и смотрим
        sleep( 1500 );


    }

    @Test
    public void thirdTest() {
//3)
//
//    Открыть Chrome в режиме полного экрана
//    Перейти на https://otus.ru
//    Авторизоваться под каким-нибудь тестовым пользователем(можно создать нового)
//    Вывести в лог все cookie

        //    Открыть Chrome в режиме полного экрана
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments( "--start-maximized" );

        driver = new ChromeDriver(chromeOptions);

        driver.get( " https://otus.ru" );
        auth();
        BasicConfigurator.configure();
        log.info( "LOG STARTED" );

        //driver.manage().getCookies();
        //System.out.println(driver.manage().getCookies());
        log.trace( driver.manage().getCookies() );

    }

    private void auth() {
        new WebDriverWait( driver, Duration.ofSeconds( 10 ) ).until( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( ".sc-mrx253-0" ) ) );
        driver.findElement( By.cssSelector( ".sc-mrx253-0" ) ).click();
        driver.findElement( By.cssSelector( "#__PORTAL__ > div > div > div.sc-1alnis6-1.ejcuap > div.sc-1alnis6-4.iVBbVz > div > div.sc-10p60tv-1.eDzhKh > div.sc-10p60tv-2.bQGCmu > div > div.sc-19qj39o-0.iLmCeO > div > div.sc-rq8xzv-1.hGvqzc.sc-11ptd2v-1.liHMCp > div > input" ) ).sendKeys( LOGIN );
        driver.findElement( By.cssSelector( "#__PORTAL__ > div > div > div.sc-1alnis6-1.ejcuap > div.sc-1alnis6-4.iVBbVz > div > div.sc-10p60tv-1.eDzhKh > div.sc-10p60tv-2.bQGCmu > div > div.sc-19qj39o-0.iLmCeO > div > div.sc-rq8xzv-1.hGvqzc.sc-11ptd2v-1-Component.ciraFX > div > input" ) ).sendKeys( PASSWORD );
        driver.findElement( By.cssSelector( "#__PORTAL__ > div > div > div.sc-1alnis6-1.ejcuap > div.sc-1alnis6-4.iVBbVz > div > div.sc-10p60tv-1.eDzhKh > div.sc-10p60tv-2.bQGCmu > div > button > div" ) ).click();
        new WebDriverWait( driver, Duration.ofSeconds( 5 ) ).until( ExpectedConditions.invisibilityOf( driver.findElement(  By.cssSelector("#__PORTAL__ > div")) ) );

    }



}