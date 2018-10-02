package com.mmall.util;

<<<<<<< HEAD
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
=======
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
>>>>>>> v1.0

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by geely
 */
<<<<<<< HEAD
public class PropertiesUtil {

    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
=======
@Slf4j
public class PropertiesUtil {

>>>>>>> v1.0

    private static Properties props;

    static {
        String fileName = "mmall.properties";
        props = new Properties();
        try {
            props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName),"UTF-8"));
        } catch (IOException e) {
<<<<<<< HEAD
            logger.error("配置文件读取异常",e);
=======
            log.error("配置文件读取异常",e);
>>>>>>> v1.0
        }
    }

    public static String getProperty(String key){
        String value = props.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            return null;
        }
        return value.trim();
    }

    public static String getProperty(String key,String defaultValue){

        String value = props.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            value = defaultValue;
        }
        return value.trim();
    }



}
