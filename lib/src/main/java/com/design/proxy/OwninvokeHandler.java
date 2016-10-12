package com.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zhanglibin on 2014/8/22.
 */
public class OwninvokeHandler implements InvocationHandler {

    Person person;
    public OwninvokeHandler(Person person) {
        this.person = person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            if(method.getName().equals("setScore")) {
                throw new IllegalAccessException("you cann't set yourself's score");
            } else {
                return method.invoke(person, args);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
