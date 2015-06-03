package com.zkt.find.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;


public class BeanUtil {

	public static void sqlfilter(Object o){
		Field[] field = o.getClass().getDeclaredFields();
		try {
			for(int j=0 ; j<field.length ; j++){
				String name = field[j].getName();    //获取属性的名字
				name = name.substring(0,1).toUpperCase()+name.substring(1); //将属性的首字符大写，方便构造get，set方法
				String type = field[j].getGenericType().toString();    //获取属性的类型
			    if(type.equals("class java.lang.String")){
			    	Method m1 = o.getClass().getMethod("get"+name);
			    	String value = (String) m1.invoke(o);//调用getter方法获取属性值
			    	if(value!=null && !"".equals(value))
			    	{
			    		String str = value.replace("'", "''");
				    	Method m2 = o.getClass().getMethod("set"+name,String.class);
				        m2.invoke(o,new Object[]{str});    //调用setter方法设置属性值
			    	}
			    }
			    if(type.equals("class java.util.List") || type.equals("class java.util.ArrayList") || type.startsWith("java.util.List")){
			    	Method m1 = o.getClass().getMethod("get"+name);
			    	List list = (List) m1.invoke(o);//调用getter方法获取属性值
			    	for(int i=0;i<list.size();i++){
			    		//
			    		Object obj = list.get(i);
			    		BeanUtil.sqlfilter(obj);
			    	}
			    }
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
