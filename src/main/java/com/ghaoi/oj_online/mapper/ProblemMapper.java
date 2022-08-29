package com.ghaoi.oj_online.mapper;

import com.ghaoi.oj_online.model.Problem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProblemMapper {
    // 新增题目
    int insert(Problem problem);
    // 删除题目
    int delete(int id);
    // 查找所有题目
    List<Problem> selectAll();
    // 查找指定题目
    Problem selectOne(int id);
}
