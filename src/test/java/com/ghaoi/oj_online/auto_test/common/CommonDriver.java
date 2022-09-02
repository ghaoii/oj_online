package com.ghaoi.oj_online.auto_test.common;

import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class CommonDriver {
    private static ChromeDriver driver;

    public static ChromeDriver getDriver() {
        if(driver == null) {
            driver = new ChromeDriver();
            // 添加隐式等待，等待3秒
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        }
        return driver;
    }

}
