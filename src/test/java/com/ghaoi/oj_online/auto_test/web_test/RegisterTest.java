package com.ghaoi.oj_online.auto_test.web_test;

import com.ghaoi.oj_online.auto_test.common.CommonDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegisterTest {
    private ChromeDriver driver = CommonDriver.getDriver();

    @BeforeEach
    void getUrl() {
        // 直接进入注册页面
        driver.get("http://localhost:8080/register.html");
    }

    // 校验页面元素
    @Test
    void checkPageElement() {
        String str = driver.findElement(By.cssSelector("body > div > div > div > h1")).getText();
        Assertions.assertEquals("注册", str);
        str = driver.findElement(By.cssSelector("#gtco-contact-form > div > div > div.row " +
                "> div > div > div.col-md-12.form-btn.text-center > button")).getText();
        Assertions.assertEquals("Register", str);
    }

    // 什么都不输入直接点击注册按钮
    @Test
    void RegisterNothing() {
        driver.findElement(By.cssSelector("#gtco-contact-form > div > div > div.row > " +
                "div > div > div.col-md-12.form-btn.text-center > button")).click();
        driver.switchTo().alert().accept();
    }

    // 只输入用户名后点击注册按钮
    @Test
    void RegisterNoPassword() {
        driver.findElement(By.cssSelector("#name")).sendKeys("test");
        driver.findElement(By.cssSelector("#gtco-contact-form > div > div > div.row > " +
                "div > div > div.col-md-12.form-btn.text-center > button")).click();
        driver.switchTo().alert().accept();
    }

    // 只输入密码和确认密码后点击注册按钮
    @Test
    void RegisterNoName() {
        driver.findElement(By.cssSelector("#password")).sendKeys("test");
        driver.findElement(By.cssSelector("#passwordConfirm")).sendKeys("test");
        driver.findElement(By.cssSelector("#gtco-contact-form > div > div > div.row > " +
                "div > div > div.col-md-12.form-btn.text-center > button")).click();
        driver.switchTo().alert().accept();
    }

    // 只输入用户名和密码不输入确认密码后点击注册按钮
    @Test
    void RegisterNoConfirm() {
        driver.findElement(By.cssSelector("#name")).sendKeys("test");
        driver.findElement(By.cssSelector("#password")).sendKeys("test");
        driver.findElement(By.cssSelector("#gtco-contact-form > div > div > div.row > " +
                "div > div > div.col-md-12.form-btn.text-center > button")).click();
        driver.switchTo().alert().accept();
    }

    // 输入存在的用户名和密码后点击注册按钮
    @Test
    void RegisterExists() {
        // 该用户名事先已注册
        driver.findElement(By.cssSelector("#name")).sendKeys("Harley");
        driver.findElement(By.cssSelector("#password")).sendKeys("harley");
        driver.findElement(By.cssSelector("#passwordConfirm")).sendKeys("harley");
        driver.findElement(By.cssSelector("#gtco-contact-form > div > div > div.row > " +
                "div > div > div.col-md-12.form-btn.text-center > button")).click();
        driver.switchTo().alert().accept();
    }

    // 输入还未注册的用户名和密码后点击注册按钮
    @Test
    void RegisterRight() {
        // 使用当前时间戳作为用户名和密码进行测试，确保测试的用户名一定不存在
        String name = String.valueOf(System.currentTimeMillis());
        // 输入用户名和密码后点击注册按钮
        driver.findElement(By.cssSelector("#name")).sendKeys(name);
        driver.findElement(By.cssSelector("#password")).sendKeys(name);
        driver.findElement(By.cssSelector("#passwordConfirm")).sendKeys(name);
        driver.findElement(By.cssSelector("#gtco-contact-form > div > div > div.row > " +
                "div > div > div.col-md-12.form-btn.text-center > button")).click();
        // 校验注册成功后是否进入题目列表页
        String str = driver.findElement(By.cssSelector("#gtco-who-we-are > div > div > div > h2")).getText();
        Assertions.assertEquals("题目列表", str);
    }

    // 检验能否从注册页面进入登录页面
    @Test
    void toLogin() {
        driver.findElement(By.cssSelector("#gtco-header-navbar > div > div > ul > li > a")).click();
        String str = driver.findElement(By.cssSelector("body > div > div > div > h1")).getText();
        Assertions.assertEquals("登录", str);
    }

    @AfterAll
    static void close() {
        CommonDriver.getDriver().close();
    }

}
