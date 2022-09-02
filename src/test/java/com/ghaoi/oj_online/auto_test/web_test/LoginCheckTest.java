package com.ghaoi.oj_online.auto_test.web_test;

import com.ghaoi.oj_online.auto_test.common.CommonDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginCheckTest {
    private ChromeDriver driver = CommonDriver.getDriver();

    @BeforeEach
    void getUrl() {
        driver.get("http://localhost:8080/login.html");
    }

    // 通过检测页面元素，来确定是否进入页面
    @Test
    void loginPageCheck() {
        String str = driver.findElement(By.cssSelector("body > div > div > div > h1")).getText();
        Assertions.assertEquals("登录", str);
    }

    // 什么都不输入，直接登录
    @Test
    void loginNothing() {
        driver.findElement(By.cssSelector("#gtco-contact-form > div > div > div.row > " +
                "div > div > div.col-md-12.form-btn.text-center > button")).click();
        driver.switchTo().alert().accept();
    }

    // 不输入用户名，输入密码
    @Test
    void loginNoName() {
        driver.findElement(By.cssSelector("#password")).sendKeys("admin");
        driver.findElement(By.cssSelector("#gtco-contact-form > div > div > div.row > " +
                "div > div > div.col-md-12.form-btn.text-center > button")).click();
        driver.switchTo().alert().accept();
    }

    // 输入用户名，不输入密码
    @Test
    void loginNoPassword() {
        driver.findElement(By.cssSelector("#name")).sendKeys("admin");
        driver.findElement(By.cssSelector("#gtco-contact-form > div > div > div.row > " +
                "div > div > div.col-md-12.form-btn.text-center > button")).click();
        driver.switchTo().alert().accept();
    }

    // 输入错误的用户名
    @Test
    void loginWrongName() {
        // 输入不存在的名字
        driver.findElement(By.cssSelector("#name")).sendKeys("123");
        // 输入密码
        driver.findElement(By.cssSelector("#password")).sendKeys("admin");
        driver.findElement(By.cssSelector("#gtco-contact-form > div > div > div.row > " +
                "div > div > div.col-md-12.form-btn.text-center > button")).click();
        driver.switchTo().alert().accept();
    }

    // 输入正确的用户名，错误的密码
    @Test
    void loginWrongPassword() {
        // 输入正确的名字
        driver.findElement(By.cssSelector("#name")).sendKeys("admin");
        // 输入错误的密码
        driver.findElement(By.cssSelector("#password")).sendKeys("123");
        driver.findElement(By.cssSelector("#gtco-contact-form > div > div > div.row > " +
                "div > div > div.col-md-12.form-btn.text-center > button")).click();
        driver.switchTo().alert().accept();
    }

    // 输入正确的用户名和密码
    @Test
    void loginRight() {
        // 使用管理员账户登录
        driver.findElement(By.cssSelector("#name")).sendKeys("admin");
        driver.findElement(By.cssSelector("#password")).sendKeys("admin");
        driver.findElement(By.cssSelector("#gtco-contact-form > div > div > div.row > " +
                "div > div > div.col-md-12.form-btn.text-center > button")).click();
        // 验证登录后的页面
        String str = driver.findElement(By.cssSelector("#gtco-who-we-are > div > div > div > h2")).getText();
        Assertions.assertEquals("题目列表", str);

    }

    // 测试能否从登录页面进入注册页面
    @Test
    void toRegister() {
        driver.findElement(By.cssSelector("#gtco-header-navbar > div > div > ul > li > a")).click();
        String str = driver.findElement(By.cssSelector("body > div > div > div > h1")).getText();
        Assertions.assertEquals("注册", str);
    }

    @AfterAll
    static void close() {
        CommonDriver.getDriver().close();
    }

}
