package com.github.yedp.ez.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 *@author  yedp
 *@date    2021-01-30 17:33:32
 *@comment Bean 工厂类
 *
 **/
public class BeanFactory {
    private static Logger logger = LoggerFactory.getLogger(BeanFactory.class);

    /**
     * bean 容器
     */
    private static ConcurrentHashMap<Class, Object> beanContainer = new ConcurrentHashMap<>();

    public static <T> T getBean(Class<T> aClass) {
        return (T) beanContainer.computeIfAbsent(aClass, aClass1 -> {
            try {
                return aClass1.newInstance();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            return null;
        });
    }
}
