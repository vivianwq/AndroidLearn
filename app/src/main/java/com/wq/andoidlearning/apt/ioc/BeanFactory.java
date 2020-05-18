package com.wq.andoidlearning.apt.ioc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BeanFactory {
    private List<BeanDefined> beanDefinedList;

    public List<BeanDefined> getBeanDefinedList() {
        return beanDefinedList;
    }

    public void setBeanDefinedList(List<BeanDefined> beanDefinedList) {
        this.beanDefinedList = beanDefinedList;
    }

    /**
     * 获取bean实例
     *
     * @param beanId
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public Object getBean(String beanId) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException, InvocationTargetException {
        Object instance;
        for (BeanDefined beanDefined : beanDefinedList) {
            if (beanId.equals(beanDefined.getBeanId())) {
                String classPath = beanDefined.getClassPath();
                Class classFile = Class.forName(classPath);
                instance = classFile.newInstance();
                Map<String, String> propertyMap = beanDefined.getPropertyMap();
                if (propertyMap != null) {
                    setValue(instance, classFile, propertyMap);
                }
                return instance;
            }
        }
        return null;
    }

    //依赖注入的方法
    public void setValue(Object instance, Class classFile, Map<String, String> propertyMap) throws NoSuchFieldException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException {
        if (propertyMap != null) {
            //获取map的所有属性配置
            Set<String> strings = propertyMap.keySet();
            for (String string : strings) {
                //通过字符串拼接,拼出set方法名
                char c = string.toUpperCase().charAt(0);
                String s = "set" + c + string.substring(1);
                //获取当前属性的类型
                Field field = classFile.getDeclaredField(string);
                //根据属性的类型进行调用
                Method m = instance.getClass().getMethod(s, field.getType());
                try {
                    //直接try注入普通类型,或者catch注入bean工厂中的其他类型
                    m.invoke(instance, propertyMap.get(string));
                } catch (Exception e) {
                    m.invoke(instance, getBean(propertyMap.get(string)));
                }
            }
        }
    }
}
