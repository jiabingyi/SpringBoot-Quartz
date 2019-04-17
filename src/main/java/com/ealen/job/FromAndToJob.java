package com.ealen.job;

import com.ealen.datasource_TAarrrUser.FromAndTo;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by EalenXie on 2018/6/4 14:29
 * :@DisallowConcurrentExecution : 此标记用在实现Job的类上面,意思是不允许并发执行.
 * :注意org.quartz.threadPool.threadCount线程池中线程的数量至少要多个,否则@DisallowConcurrentExecution不生效
 * :假如Job的设置时间间隔为3秒,但Job执行时间是5秒,设置@DisallowConcurrentExecution以后程序会等任务执行完毕以后再去执行,否则会在3秒时再启用新的线程执行
 */
@DisallowConcurrentExecution
@Component
public class FromAndToJob implements Job {
    private Logger logger = LoggerFactory.getLogger(FromAndToJob.class);

    /**
     * 核心方法,Quartz Job真正的执行逻辑.
     * @param executorContext executorContext JobExecutionContext中封装有Quartz运行所需要的所有信息
     * @throws JobExecutionException execute()方法只允许抛出JobExecutionException异常
     */
    @Override
    public void execute(JobExecutionContext executorContext) throws JobExecutionException {

        logger.info("------FromAndToJob-------<<<start>>>---");
        long t1=System.currentTimeMillis();

        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        String startDate=df.format(t1-(long)31*24*60*60*1000);
        String endDate=df.format(t1-(long)25*24*60*60*1000);

        FromAndTo.fromAndTo(100,startDate,endDate);

        long t2=System.currentTimeMillis();
        logger.info("------FromAndToJob-------<<<end>>>---,耗时  "+(t2-t1)/1000+"秒");

    }

    //记录Job执行内容
    private void logProcess(InputStream inputStream, InputStream errorStream) throws IOException {
        String inputLine;
        String errorLine;
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream));
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
        while ((inputLine = inputReader.readLine()) != null) logger.info(inputLine);
        while ((errorLine = errorReader.readLine()) != null) logger.error(errorLine);
    }

}
