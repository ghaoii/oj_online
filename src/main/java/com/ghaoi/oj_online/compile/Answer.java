package com.ghaoi.oj_online.compile;

import lombok.Data;

@Data
public class Answer {
    // 用户代码编译运行后的结果
    // 约定0为正常，1为编译错误，2为运行错误(抛异常)，3为其他错误
    private int error;
    // 用于代码编译运行正常时的标准输出
    private String stdout;
    // 用户代码编译运行异常时的错误原因
    private String reason;
}
