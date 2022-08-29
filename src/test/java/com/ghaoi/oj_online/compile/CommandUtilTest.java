package com.ghaoi.oj_online.compile;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandUtilTest {
    private String testFile = "src/test/java/com/ghaoi/oj_online/test_file/";

    @Test
    void run() {
        int ret = CommandUtil.run("javac", testFile + "stdout.txt", testFile + "stderr.txt");
        Assertions.assertNotEquals(0, ret);// 返回0表示编译成功，非零表示编译错误
    }

    @Test
    void run2() {
        String cmd = String.format("javac -encoding utf8 -d %s %s", testFile, testFile + "Solution.java");
        int ret = CommandUtil.run(cmd, testFile + "stdout.txt", testFile + "stderr.txt");
        Assertions.assertEquals(0, ret);
    }
}