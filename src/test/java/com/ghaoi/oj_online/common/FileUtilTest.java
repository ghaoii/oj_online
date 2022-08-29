package com.ghaoi.oj_online.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;


class FileUtilTest {
    private String testFile = "src/test/java/com/ghaoi/oj_online/test_file/";

    @Test
    @Order(2)
    void readFile() {
        String ret = FileUtil.readFile(testFile + "fileTest.txt");
        Assertions.assertEquals("hello world", ret);
    }

    @Test
    @Order(1)
    void writeFile() {
        FileUtil.writeFile(testFile + "fileTest.txt", "hello world");
    }
}