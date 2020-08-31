package com.geeklin.util;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author Lin
 * @date 2020/7/30 16:51
 */
public class WebUtils {

    /**
     * 将字符串转换成int类型
     * @param intStr
     * @param defaultValue 如果转换失败就返回默认值
     * @return
     */
    public static Integer parseInt(String intStr , Integer defaultValue){

        try {
            return  Integer.parseInt(intStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return defaultValue;
    }

    /**
     *  编写一个方法去将所有参数都一次性注入到JavaBean属性中 <br/>
     */
    public static <T> T copyParamToBean(T bean , Map map){
        /**
         * populate() 是把指定数据源中的值一次性注入到Bean的属性中 <br>
         *     第一个参数是要赋值的Bean对象 <br>
         *     第二个参数是数据源 <br>
         */
        try {
            BeanUtils.populate(bean,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;

    }
}
