package com.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by zhanglibin on 2014/8/22.
 */
public class NonOwnerInvocationHandler implements InvocationHandler{
    private Person person;
    public NonOwnerInvocationHandler(Person person) {
        this.person = person;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            if(method.getName().equals("setName")) {
                throw new IllegalAccessException("you cann't set name!");
            } else {
                return method.invoke(person, args);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
