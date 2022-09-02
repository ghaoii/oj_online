package com.ghaoi.oj_online.auto_test.web_test;

import com.ghaoi.oj_online.auto_test.common.CommonDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProblemListTest {
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
    }

    // 检测页面元素
    @Test
    @Order(1)
    void checkPageElement() {
        String str = driver.findElement(By.cssSelector("#gtco-who-we-are > div > div > div > h2")).getText();
        Assertions.assertEquals("题目列表", str);
    }

    // 点击题目看能否跳转到题目详情页面
    @Test
    @Order(2)
    void toDetail() {
        driver.findElement(By.cssSelector("#problemTbody > tr:nth-child(2) > td:nth-child(2) > a")).click();
        String str = driver.findElement(By.cssSelector("#gtco-single-content > div >" +
                " div > div > div > h5:nth-child(1)")).getText();
        Assertions.assertEquals("代码编辑区", str);
        str = driver.findElement(By.cssSelector("#gtco-single-content > div > " +
                "div > div > div > h5:nth-child(6)")).getText();
        Assertions.assertEquals("运行结果", str);
    }

    // 点击导航栏的Manage检查能否进入管理员页面
    @Test
    @Order(3)
    void toManage() {
        driver.findElement(By.cssSelector("#gtco-header-navbar > div > div > ul > li:nth-child(2) > a")).click();
        String str = driver.findElement(By.cssSelector("body > div > div > div > h1")).getText();
        Assertions.assertEquals("题目管理", str);
    }

    // 点击导航栏的Logout检查是否退出登录
    @Test
    @Order(4)
    void Logout() {
        driver.findElement(By.cssSelector("#gtco-header-navbar > div > div > ul > li:nth-child(3) > a")).click();
        driver.switchTo().alert().accept();
        String str = driver.findElement(By.cssSelector("body > div > div > div > h1")).getText();
        Assertions.assertEquals("登录", str);
    }

    @AfterAll
    static void close() {
        CommonDriver.getDriver().close();
    }

}
