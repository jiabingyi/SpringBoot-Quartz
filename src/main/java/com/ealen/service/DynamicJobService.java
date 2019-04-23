package com.ealen.service;

import com.ealen.dao.JobEntityRepository;
import com.ealen.entity.JobEntity;
import com.ealen.job.DynamicJob;
import com.ealen.job.FromAndToJob;
import com.ealen.util.ReadPropertiesUtil;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EalenXie on 2018/6/4 14:25
 */
@Service
public class DynamicJobService {

    @Autowired
    private JobEntityRepository repository;

    //通过Id获取Job
    public JobEntity getJobEntityById(Integer id) {
        JobEntity job=new JobEntity();
        job.setName(ReadPropertiesUtil.get("quartz.job_entity.name"));
        job.setGroup(ReadPropertiesUtil.get("quartz.job_entity.group"));
        job.setCron(ReadPropertiesUtil.get("quartz.job_entity.cron"));
        job.setParameter(ReadPropertiesUtil.get("quartz.job_entity.parameter"));
        job.setDescription(ReadPropertiesUtil.get("quartz.job_entity.description"));
        job.setDescription(ReadPropertiesUtil.get("quartz.job_entity.vm_param"));
        job.setDescription(ReadPropertiesUtil.get("quartz.job_entity.jar_path"));
        job.setStatus(ReadPropertiesUtil.get("quartz.job_entity.status"));
        return  job;
//        return repository.getById(id);
    }

    //从数据库中加载获取到所有Job
    public List<JobEntity> loadJobs() {
        List<JobEntity> list = new ArrayList<>();
        JobEntity job=new JobEntity();
        job.setName(ReadPropertiesUtil.get("quartz.job_entity.name"));
        job.setGroup(ReadPropertiesUtil.get("quartz.job_entity.group"));
        job.setCron(ReadPropertiesUtil.get("quartz.job_entity.cron"));
        job.setParameter(ReadPropertiesUtil.get("quartz.job_entity.parameter"));
        job.setDescription(ReadPropertiesUtil.get("quartz.job_entity.description"));
        job.setDescription(ReadPropertiesUtil.get("quartz.job_entity.vm_param"));
        job.setDescription(ReadPropertiesUtil.get("quartz.job_entity.jar_path"));
        job.setStatus(ReadPropertiesUtil.get("quartz.job_entity.status"));
        list.add(job);
//        repository.findAll().forEach(list::add);
        return list;
    }

    //获取JobDataMap.(Job参数对象)
    public JobDataMap getJobDataMap(JobEntity job) {
        JobDataMap map = new JobDataMap();
        map.put("name", job.getName());
        map.put("group", job.getGroup());
        map.put("cronExpression", job.getCron());
        map.put("parameter", job.getParameter());
        map.put("JobDescription", job.getDescription());
        map.put("vmParam", job.getVmParam());
        map.put("jarPath", job.getJarPath());
        map.put("status", job.getStatus());
        return map;
    }

    //获取JobDetail,JobDetail是任务的定义,而Job是任务的执行逻辑,JobDetail里会引用一个Job Class来定义
    public JobDetail geJobDetail(JobKey jobKey, String description, JobDataMap map) {
        return JobBuilder.newJob(FromAndToJob.class)
                .withIdentity(jobKey)
                .withDescription(description)
                .setJobData(map)
                .storeDurably()
                .build();
//        return JobBuilder.newJob(DynamicJob.class)
//                .withIdentity(jobKey)
//                .withDescription(description)
//                .setJobData(map)
//                .storeDurably()
//                .build();
    }

    //获取Trigger (Job的触发器,执行规则)
    public Trigger getTrigger(JobEntity job) {
        return TriggerBuilder.newTrigger()
                .withIdentity(job.getName(), job.getGroup())
                .withSchedule(CronScheduleBuilder.cronSchedule(job.getCron()))
                .build();
    }

    //获取JobKey,包含Name和Group
    public JobKey getJobKey(JobEntity job) {
        return JobKey.jobKey(job.getName(), job.getGroup());
    }
}
