package com.web.core.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Administrator on 2015/8/27.
 */
public class ObjectUtil {
    private static Map supportTypeMap;
    private static Log logger = LogFactory.getLog(ObjectUtil.class);
    static
    {
        supportTypeMap = new HashMap();
        supportTypeMap.put(java.lang.Integer.class, "");
        supportTypeMap.put(java.lang.Long.class, "");
        supportTypeMap.put(java.lang.Double.class, "");
        supportTypeMap.put(java.math.BigDecimal.class, "");
        supportTypeMap.put(java.lang.String.class, "");
        supportTypeMap.put(java.util.Date.class, "");
        supportTypeMap.put(java.lang.Boolean.class, "");
        supportTypeMap.put(java.util.ArrayList.class, "");
    }
    /***
     * 简单对象拷贝方法
     * @param source 源
     * @param target 目标
     */
    public static void copySimpleObject(Object source, Object target)
    {
        copySimpleObject(source, target, true);
    }

    /***
     * 简单对象拷贝方法
     * @param source 源
     * @param target 目标
     * @param isCopyNull 是否拷贝空
     */
    public static void copySimpleObject(Object source, Object target, boolean isCopyNull)
    {
        Map map;
        Iterator iter;
        Method method;
        if(target == null || source == null)
            return;
        List targetMethodList = getSetter(target.getClass());
        List sourceMethodList = getGetter(source.getClass());
        map = new HashMap();
        for(iter = sourceMethodList.iterator(); iter.hasNext(); map.put(method.getName(), method))
            method = (Method)iter.next();

        iter = targetMethodList.iterator();
        while(iter.hasNext()) {
            String fieldName;
            method = (Method) iter.next();
            fieldName = method.getName().substring(3);
            Method sourceMethod;
            sourceMethod = (Method) map.get((new StringBuilder("get")).append(fieldName).toString());
            if (sourceMethod == null){
                sourceMethod = (Method) map.get((new StringBuilder("is")).append(fieldName).toString());
            }
            if (sourceMethod == null){
                continue;
            }
            if (supportTypeMap.containsKey(sourceMethod.getReturnType())) {
                try {
                    Object value = sourceMethod.invoke(source, new Object[0]);
                    if (isCopyNull)
                        method.invoke(target, new Object[]{
                                value
                        });
                    else if (value != null)
                        method.invoke(target, new Object[]{
                                value
                        });
                } catch (Exception e) {
                    e.printStackTrace();
                    if (logger.isDebugEnabled())
                        logger.debug(e);
                }
            }
        }
    }
    public static List getSetter(Class cl) {
        List list = new ArrayList();
        Method methods[] = cl.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            String methodName = method.getName();
            if (methodName.startsWith("set"))
                list.add(method);
        }

        do {
            cl = cl.getSuperclass();
            if (cl != java .lang.Object.class)
                list.addAll(getSetter(cl));
            else
                return list;
        } while (true);
    }
    public static List getGetter(Class cl) {
        List list = new ArrayList();
        Method methods[] = cl.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            String methodName = method.getName();
            if (methodName.startsWith("get") || methodName.startsWith("is"))
                list.add(method);
        }

        do {
            cl = cl.getSuperclass();
            if (cl != java.lang .Object.class)
                list.addAll(getGetter(cl));
            else
                return list;
        } while (true);
    }
}
