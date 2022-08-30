package com.ghaoi.oj_online.compile;

import com.ghaoi.oj_online.common.FileUtil;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class Task {
    private String WORKDIR;// 用于保存用户代码的编译运行结果
    private String CLASS;// 约定用户保存用户代码的类
    private String CODE;// 保存用户代码的.java文件
    private String COMPILE_ERROR;// 保存用户javac命令的标准错误的文件
    private String STDOUT;// 保存用户java命令的标准输出的文件
    private String STDERR;// 保存用户java命令的标准错误的文件

    public Task() {
        WORKDIR = "./tmp/" + UUID.randomUUID() + File.separator;
        CLASS = "Solution";// 约定使用Solution类保存用户的代码
        CODE = WORKDIR + "Solution.java";
        COMPILE_ERROR = WORKDIR + "compileError.txt";
        STDOUT = WORKDIR + "stdout.txt";
        STDERR = WORKDIR + "stderr.txt";
    }

    /**
     * 编译并运行用户代码
     * @param question 传入用户代码
     * @return 返回编译和运行的结果
     */
    public Answer compileAndRun(Question question) {
        Answer answer = new Answer();
        File file = new File(WORKDIR);
        if (!file.exists()) {
            // 如果保存输出结果的目录不存在，则先创建目录
            file.mkdirs();
        }
        if(!codeIsSafe(question.getCode())) {
            // 代码不安全
            System.out.println("用户提交了不安全的代码");
            answer.setError(3);
            answer.setReason("您提交的代码可能危害到服务器，禁止运行!");
            return answer;
        }
        FileUtil.writeFile(CODE, question.getCode());
        // 构建编译命令，并将生成的类文件放到WORKDIR中
        String compileCmd = String.format("javac -encoding utf8 %s -d %s", CODE, WORKDIR);
        System.out.println("编译命令: " + compileCmd);
        // 我们只关心编译错误的情况
        CommandUtil.run(compileCmd, null, COMPILE_ERROR);
        // 读取标准错误内容
        String compileError = FileUtil.readFile(COMPILE_ERROR);
        // 如果标准错误内容为空，则说明编译通过该了，否则说明编译未通过
        if(!"".equals(compileError)) {
            // 设置返回值，并返回
            answer.setError(1);
            answer.setReason(compileError);
            return answer;
        }

        // 构建运行命令
        String runCmd = String.format("java -classpath %s %s", WORKDIR, CLASS);
        System.out.println("运行命令: " + runCmd);
        CommandUtil.run(runCmd, STDOUT, STDERR);
        // 读取标准错误内容
        String runError = FileUtil.readFile(STDERR);
        if(!"".equals(runError)) {
            answer.setError(2);
            answer.setReason(runError);
            return answer;
        }

        // 如果走到这里，说明编译运行都通过了
        answer.setError(0);
        answer.setStdout(FileUtil.readFile(STDOUT));
        return answer;
    }

    private boolean codeIsSafe(String code) {
        List<String> blackList = new ArrayList<>();
        blackList.add("Runtime");
        blackList.add("exec");
        blackList.add("java.io");
        blackList.add("java.net");
        for(String str : blackList) {
            int i = code.indexOf("str");
            if(i >= 0) {
                // 如果在代码中识别到该命令，则代码不安全
                return false;
            }
        }
        // 代码中没有识别到危险操作
        return true;
    }
}
