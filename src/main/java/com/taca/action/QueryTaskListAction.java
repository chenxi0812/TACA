package com.taca.action;

import com.taca.common.bean.ResultBean;
import com.taca.common.enumstatus.TaskStatus;
import com.taca.common.enumstatus.TransStatus;
import com.taca.common.util.DateUtil;
import com.taca.common.util.ZIPUtil;
import com.taca.model.ReceiveTask;
import com.taca.model.Submissions;
import com.taca.model.TaskInfo;
import com.taca.model.TransRecord;
import com.taca.service.ReceiveTaskService;
import com.taca.service.TaskService;
import com.taca.vo.TaskList;
import org.apache.catalina.connector.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by asus on 2017/9/25.
 */
@Component
public class QueryTaskListAction {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ReceiveTaskService receiveTaskService;

    private static final Logger log = LoggerFactory.getLogger(QueryTaskListAction.class);

    /**
     * 提交用户反馈
     * @param
     * @return
     */
    public int insertUserBack(String text,MultipartFile[] file,Long taskId,String userName,Long Id) throws IOException {
        ReceiveTask receiveTask = new ReceiveTask();
        TransRecord transRecord = new TransRecord();
        log.info("用户的名字是"+userName);
        Submissions submissions = new Submissions();
        ZIPUtil zipUtil = new ZIPUtil();
        String address = zipUtil.ZIPUtile(file,userName);
        Date date = new Date();
        submissions.setReceiveBook(text);
        if(file != null) {
            submissions.setFileAddress(address);
        }else{
            submissions.setFileAddress("null");
        }
        submissions.setReceiveTaskId(taskId);
        submissions.setCreateTime(date);
        submissions.setUpdateTime(date);
        //插入用户任务完成记录
       transRecord.setStatus(TransStatus.PROCEEDING.toString());
        transRecord.setUsername(userName);
        transRecord.setCreateTime(new Date());
        transRecord.setUpdateTime(new Date());
        log.info("用户名字是"+userName+"id是"+Id);
        transRecord.setStarNumber(receiveTaskService.selectReceiveTask(Id).getPreStarNumber());
        receiveTaskService.insertTask(transRecord);
        log.info("======================交易ID"+transRecord.getId());
        //更新用户状态
        receiveTask.setId(Id);
        receiveTask.setUpdateTime(new Date());
        receiveTask.setTransId(transRecord.getId());
        receiveTask.setStatus(TaskStatus.PENDING.toString());
        receiveTaskService.updateTaskStauts(receiveTask);

        return receiveTaskService.insertUserBack(submissions);
    }

    public List<TaskInfo> queryTaskList(String userId){
        return taskService.selectTaskInfoListKey(Long.parseLong(userId));
    }
    public TaskInfo queryTaskDetail(String userId){

        TaskInfo taskInfo = new TaskInfo();
        try{
         taskInfo =    taskService.queryTaskDetail(Long.parseLong(userId));
        }catch (Exception e){
            e.printStackTrace();
        }
        return  taskInfo;
    }
    public int taskSubmit(String gonsis,String photoUrl){
        ReceiveTask receiveTask = new ReceiveTask();
        return taskService.taskRevice(receiveTask);
    }

    /**
     * 返回任务列表
     * @param userName
     * @return
     */
    public   List<TaskList> selectUserTask(String userName){
        List<ReceiveTask> receiveTaskList = null;
        List<TaskList> taskLists = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            receiveTaskList = taskService.selectByUser(userName);
            for(int i=0;i<receiveTaskList.size();i++){
                TaskList taskList = new TaskList();
                Date endDate = receiveTaskList.get(i).getExpectedTime();
                Date startDate =receiveTaskList.get(i).getStartTime();
                taskList.setId(receiveTaskList.get(i).getId());
                taskList.setTaskId(receiveTaskList.get(i).getTaskId());
                taskList.setUserName(receiveTaskList.get(i).getUserName());
                taskList.setTaskName(receiveTaskList.get(i).getName());
                taskList.setContent(receiveTaskList.get(i).getContent());
                taskList.setHadFinished(DateUtil.subDate(startDate,new Date()));
                taskList.setNeedFinish(Integer.parseInt(String.valueOf((endDate.getTime() - new Date().getTime()) / (24 * 3600 * 1000))));
                taskList.setStartTime(sdf.format(startDate));
                taskList.setEndTime(sdf.format(endDate));
                taskList.setStar(Integer.parseInt(String.valueOf(receiveTaskList.get(i).getTaskId())));
                taskList.setPersonNum(receiveTaskService.selectTaskUser(receiveTaskList.get(i).getName()).size()-1);
                taskLists.add(taskList);
            }
        return taskLists;
    }
}
