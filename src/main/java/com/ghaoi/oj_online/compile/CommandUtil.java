package com.ghaoi.oj_online.compile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CommandUtil {
    public static int run(String cmd, String stdout, String stderr) {
        try {
            // 创建子进程运行命令
            Process process = Runtime.getRuntime().exec(cmd);
            // 读取标准输出
            if(stdout != null) {
                InputStream stdoutFrom = process.getInputStream();
                FileOutputStream stdoutTo = new FileOutputStream(stdout);
                while (true) {
                    int ch = stdoutFrom.read();
                    if(ch == -1) {
                        break;
                    }
                    stdoutTo.write(ch);
                }
                stdoutTo.flush();
                stdoutFrom.close();
                stdoutTo.close();
            }
            // 读取标准错误
            if(stderr != null) {
                InputStream stderrFrom = process.getErrorStream();
                FileOutputStream stderrTo = new FileOutputStream(stderr);
                while (true) {
                    int ch = stderrFrom.read();
                    if(ch == -1) {
                        break;
                    }
                    stderrTo.write(ch);
                }
                stderrTo.flush();
                stderrFrom.close();
                stderrTo.close();
            }
            // 返回子进程的状态码，0为正常，非0位异常
            int ret = process.waitFor();
            return ret;
        } catch (Exception e) {
            e.getStackTrace();
        }
        // 如果捕获到异常就返回非0,表示任务执行出错
        return 1;
    }
}
