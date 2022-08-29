package com.ghaoi.oj_online.common;

import java.io.*;

// 封装文件读取和写入的方法
public class FileUtil {
    /**
     * 以字符流的方式读取文件内容，并返回一个字符串
     * @param filePath 被读取文件
     * @return 读取到的文件内容
     */
    public static String readFile(String filePath) {
        StringBuilder ret = new StringBuilder();
        try (FileReader fileReader = new FileReader(filePath)){
            while(true) {
                int ch = fileReader.read();
                if(ch == -1) {
                    break;
                }
                ret.append((char) ch);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ret.toString();
    }

    /**
     * 以字符流的方式将内容写入指定文件
     */
    public static void writeFile(String filePath, String content) {
        try (FileWriter fileWriter = new FileWriter(filePath)){
            fileWriter.write(content);
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
