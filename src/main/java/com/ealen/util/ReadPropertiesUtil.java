package com.ealen.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 读取文件的工具类
 * @author jiangzhengquan
 *
 */
public class ReadPropertiesUtil {

  private static Logger logger = LoggerFactory.getLogger(ReadPropertiesUtil.class);
  
  //根据系统的自定义语言类型加载配置文件
  public static Map<String,Properties> languageProperties ;
  //配置文件信息
  public static Map<String,Properties> sysProperties = new HashMap<String,Properties>();
  
  /**
   * 读取配置文件定义的信息
   * @param key 关键字
   * @param fileName 文件名称
   * @return
   */
  public static  String getProperties(String key,String fileName){
      Properties props = null;
      try {
          props = sysProperties.get(fileName);
          if(props == null){
              props = new Properties();
              ClassLoader cl = new ReadPropertiesUtil().getClass().getClassLoader();
              InputStream is = cl.getResourceAsStream(fileName);
              props.load(is);
              sysProperties.put(fileName, props);
          }
          return props.getProperty(key);
          
      } catch (IOException e) {
          logger.error("not properties file found @"+fileName,e);
      }
      return null;
  }
  
  /**
   * 
   * @param key
   * @return
   */
  public static String get(String key){
      return getProperties(key, "quartz-datasource.properties");
  }
 
}
