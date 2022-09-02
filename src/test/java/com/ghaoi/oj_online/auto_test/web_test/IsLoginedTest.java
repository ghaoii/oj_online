package com.ghaoi.oj_online.auto_test.web_test;

import com.ghaoi.oj_online.auto_test.common.CommonDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class IsLoginedTest {
    private ChromeDriver driver = CommonDriver.getDriver();

    @Test
    void indexPage() throws InterruptedException {
        driver.get("http://localhost:8080/index.html");
        Thread.sleep(1000);
    }

    @Test
    void detailPage() throws InterruptedException {
        driver.get("http://localhost:8080/index.html");
        Thread.sleep(1000);
    }

    @AfterEach
    void checkLoginPage() {
        driver.switchTo().alert().accept();
        String str = driver.findElement(By.cssSelector("body > div > div > div > h1")).getText();
        Assertions.assertEquals("登录", str);
    }

    @AfterAll
    static void close() {
        CommonDriver.getDriver().close();
    }
}
