package com.taca.busservice;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.taca.action.TaskAuditAction;
import com.taca.common.constants.IMResp;
import com.taca.common.enumstatus.TransStatus;
import com.taca.common.exception.BusinessException;
import com.taca.mapper.*;
import com.taca.model.*;
import com.taca.service.HonorTitleService;
import com.taca.service.SendEmailService;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.taca.mapper.ReceiveTaskMapper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;


@Component("auditTaskBusService")
public class AuditTaskBusService{

	@Autowired
	private AuditTaskMapper auditTaskMapper;
	
	@Autowired
	private ReceiveTaskMapper reciveTaskMapper;
	
	@Autowired
	private SubmissionsMapper submissionsMapper;
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private TransRecordMapper transRecordMapper;
	
	@Autowired
	private HonorTitleService honorTitleService;
	@Autowired
	private SendEmailService sendEmailService;


	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	private static final Logger log = LoggerFactory.getLogger(TaskAuditAction.class);


	@Transactional
	public Integer updateForReject(ReceiveTask reciveTask, Submissions submissions) {

		reciveTaskMapper.audit(reciveTask);
		submissionsMapper.updateForReject(submissions);
		
		Long trandId = reciveTaskMapper.getTransIdByReceiveId(reciveTask.getId());
		TransRecord transRecord = new TransRecord();
		transRecord.setId(trandId);
		transRecord.setFinishedTime(new Date());
		transRecord.setUpdateTime(transRecord.getFinishedTime());
		transRecord.setStatus(TransStatus.FAIL.name());
		transRecordMapper.updateForTaskAudit(transRecord);
		//send email
		String userName = reciveTaskMapper.getUserNameByReceiveId(reciveTask.getId());
		ReceiveTask receiveTask = reciveTaskMapper.getById(reciveTask.getId());
		String title="任务审核拒绝";
		String receiver=userName+"@pingan.com.cn";
		//读取邮件模板
		Template template=null;
		String content="";
		try {
			template= freeMarkerConfigurer.getConfiguration().getTemplate("/emailTemplates/taskAuditFail.ftl");
			Map map=new HashMap();
			map.put("reason", submissions.getMessage());
			map.put("name", receiveTask.getName());
			map.put("content", receiveTask.getContent());
			content = FreeMarkerTemplateUtils.processTemplateIntoString(template,map);
		}
		catch (Exception e){
			log.info("邮件读取出错",e);
			throw new BusinessException(IMResp.TEMPLATE_LOAD_ERROR);
		}
		log.info("解析结果："+content);
		sendEmailService.sendEmail(title,content,receiver);
		return 1;
		
	}


	@Transactional
	public Integer updateForAgree(ReceiveTask reciveTask,Submissions submissions) {
		reciveTaskMapper.audit(reciveTask);
		submissionsMapper.updateForAgree(submissions);
		//变更交易状态
		Long trandId = reciveTaskMapper.getTransIdByReceiveId(reciveTask.getId());
		TransRecord transRecord = new TransRecord();
		transRecord.setId(trandId);
		transRecord.setFinishedTime(new Date());
		transRecord.setUpdateTime(transRecord.getFinishedTime());
		transRecord.setStarNumber(reciveTask.getActStarNumber());
		transRecord.setStatus(TransStatus.FINISHED.toString());
		transRecordMapper.updateForTaskAudit(transRecord);

		//变更用户星点数
		String userName = reciveTaskMapper.getUserNameByReceiveId(reciveTask.getId());
		UserInfo user = userInfoMapper.selectByUserName(userName);
		user.setAvailableStar(user.getAvailableStar()+reciveTask.getActStarNumber());
		user.setHistoryStar(user.getHistoryStar()+reciveTask.getActStarNumber());
		user.setUpdateTime(new Date());


		List<HonorTitle> list = honorTitleService.getList();
		Long star = user.getHistoryStar();
		String title = null;
		for(HonorTitle honorTitle:list){
			if(star>honorTitle.getStarNumber()){
				title = honorTitle.getTitle();
				continue;
			}else if (star==honorTitle.getStarNumber()) {
				user.setHonorTitle(honorTitle.toString());
				break;
			}
			user.setHonorTitle(title);
			break;
		}
		userInfoMapper.updateForTaskAudit(user);
		//send email
		ReceiveTask receiveTask = reciveTaskMapper.getById(reciveTask.getId());
		String etitle="任务审核通过";
		String receiver=userName+"@pingan.com.cn";

		//读取邮件模板
		Template template=null;
		String content="";
		try {
			template= freeMarkerConfigurer.getConfiguration().getTemplate("/emailTemplates/taskAuditSuccess.ftl");
			Map map=new HashMap();
			map.put("actStar",reciveTask.getActStarNumber());
			map.put("name", receiveTask.getName());
			map.put("content", receiveTask.getContent());
			content = FreeMarkerTemplateUtils.processTemplateIntoString(template,map);
		}
		catch (Exception e){
			log.info("邮件读取出错",e);
			throw new BusinessException(IMResp.TEMPLATE_LOAD_ERROR);
		}
		log.info("解析结果："+content);
		sendEmailService.sendEmail(etitle,content,receiver);
		return (Integer) 1;
	}
}
