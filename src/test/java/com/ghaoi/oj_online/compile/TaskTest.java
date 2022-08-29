package com.ghaoi.oj_online.compile;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    Task task = new Task();

    @Test
    void compileAndRun() {
        Question question = new Question();
        question.setCode("public class Solution {\n" +
                "    public static void main(String[] args) {\n" +
                "        int a = 10;\n" +
                "        System.out.println(a);\n" +
                "    }\n" +
                "}");
        Answer answer = task.compileAndRun(question);
        System.out.println(answer);
        Assertions.assertEquals(0, answer.getError());
    }

    @Test
    void compileAndRun2() {
        Question question = new Question();
        question.setCode("public class Solution {\n" +
                "    public static void main(String[] args) {\n" +
                "        int a = 10;\n" +
                "        Sytem.out.println(a);\n" +
                "    }\n" +
                "}");
        Answer answer = task.compileAndRun(question);
        System.out.println(answer);
        Assertions.assertEquals(1, answer.getError());
    }

    @Test
    void compileAndRun3() {
        Question question = new Question();
        question.setCode("public class Solution {\n" +
                "    public static void main(String[] args) {\n" +
                "        int a = 10 / 0;\n" +
                "        System.out.println(a);\n" +
                "    }\n" +
                "}");
        Answer answer = task.compileAndRun(question);
        System.out.println(answer);
        Assertions.assertEquals(2, answer.getError());
    }
}