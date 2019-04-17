package com.ealen;

import com.ealen.entity.ConfigBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Created by EalenXie on 2018/6/4 11:00
 */
@SpringBootApplication
//@EnableConfigurationProperties({ConfigBean.class})
public class QuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);
    }

}
