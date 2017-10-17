package com.taca.mapper;


import com.taca.model.TaskMutex;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskMutexMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TaskMutex record);

    int insertSelective(TaskMutex record);

    TaskMutex selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaskMutex record);

    int updateByPrimaryKey(TaskMutex record);

    List<Long> getMutexFirstTaskIdList(Long id);

    List<Long> getMutexSecondTaskIdList(Long id);

    List<TaskMutex> queryList(TaskMutex taskMutex);
}