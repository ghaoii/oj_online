package com.ghaoi.oj_online.controller;

import com.ghaoi.oj_online.common.ProblemNotFoundException;
import com.ghaoi.oj_online.common.UserCodeEmptyException;
import com.ghaoi.oj_online.compile.Answer;
import com.ghaoi.oj_online.compile.CommandUtil;
import com.ghaoi.oj_online.compile.Question;
import com.ghaoi.oj_online.compile.Task;
import com.ghaoi.oj_online.mapper.ProblemMapper;
import com.ghaoi.oj_online.model.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/problem")
public class ProblemController {
    @Autowired
    private ProblemMapper problemMapper;

    @Autowired
    private Task task;

    @RequestMapping("/search")
    public Problem search(Integer id){
        return problemMapper.selectOne(id);
    }

    @RequestMapping("/insert")
    public int addProblem(Problem problem) {
        return problemMapper.insert(problem);
    }

    @RequestMapping("/delete")
    public int removeProblem(Integer id) {
        return problemMapper.delete(id);
    }

    @RequestMapping("/list")
    public List<Problem> getProblems() {
        return problemMapper.selectAll();
    }

    @RequestMapping("/detail")
    public Problem getProblem(Integer id) {
        return problemMapper.selectOne(id);
    }

    @RequestMapping("/compile")
    public Object compile(Integer id, String userCode) {
        Map<String, Object> ret = new HashMap<>();
        int error = 0;
        String reason = "";
        String stdout = "";
        try {
            //根据题号查找题目详情
            Problem problem = problemMapper.selectOne(id);
            if(problem == null) {
                // id不合法
                throw new ProblemNotFoundException();
            }
            if(userCode == null || userCode.equals("")) {
                // 用户输入代码为空
                throw new UserCodeEmptyException();
            }
            // 合并用户代码和测试代码
            String code = mergeCode(userCode, problem.getTestCode());
            Question question = new Question();
            question.setCode(code);
            Answer answer = task.compileAndRun(question);
            error = answer.getError();
            if(error == 0) {
                // 编译运行没有发生错误,读取标准输出
                stdout = answer.getStdout();
            }else {
                // 编译运行出现错误，读取标准错误
                reason = answer.getReason();
            }
        } catch (ProblemNotFoundException e) {
            error = 3;
            reason = "未找到题目!";
        } catch (UserCodeEmptyException e) {
            error = 3;
            reason = "未输入代码!";
        }
        ret.put("error", error);
        ret.put("reason", reason);
        ret.put("stdout", stdout);
        return ret;
    }

    private String mergeCode(String userCode, String testCode) {
        int index = userCode.lastIndexOf('}');
        if(index == -1) {
            // 如果没有右括号，代码一定是有问题的
            return null;
        }
        return userCode.substring(0, index) + "\n" + testCode + "\n}";
    }
}
