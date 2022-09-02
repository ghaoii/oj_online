package com.ghaoi.oj_online.auto_test.web_test;

import com.ghaoi.oj_online.auto_test.common.CommonDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class ProblemDetailTest {
    private ChromeDriver driver = CommonDriver.getDriver();

    @BeforeAll
    // 先登录才能进入题目列表页
    static void login() {
        ChromeDriver driver1 = CommonDriver.getDriver();
        driver1.get("http://localhost:8080/login.html");
        driver1.findElement(By.cssSelector("#name")).sendKeys("admin");
        driver1.findElement(By.cssSelector("#password")).sendKeys("admin");
        driver1.findElement(By.cssSelector("#gtco-contact-form > div > div > " +
                "div.row > div > div > div.col-md-12.form-btn.text-center > button")).click();
    }

    @BeforeEach
    void getUrl() {
        driver.get("http://localhost:8080/index.html");
        driver.findElement(By.cssSelector("#problemTbody > tr:nth-child(2) > td:nth-child(2) > a")).click();
    }

    // 检查页面元素
    @Test
    void checkPageElement() {
        String str = driver.findElement(By.cssSelector("#gtco-single-content > div >" +
                " div > div > div > h5:nth-child(1)")).getText();
        Assertions.assertEquals("代码编辑区", str);
        str = driver.findElement(By.cssSelector("#gtco-single-content > div > " +
                "div > div > div > h5:nth-child(6)")).getText();
        Assertions.assertEquals("运行结果", str);
    }

    @AfterAll
    static void close() {
        CommonDriver.getDriver().close();
    }
}
