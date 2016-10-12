package com.design.proxy;

import java.lang.reflect.Proxy;

/**
 * 保护代理
 * Created by zhanglibin on 2014/8/22.
 */
public class PersonTest {
    public static void main(String[] args) {
        PersonTest test = new PersonTest();
        PersonBean bean = new PersonBean();

        Person ownerPerson = test.getOwnerProxy(bean);
        ownerPerson.setName("zhangsan");
        System.out.println(bean.getName());
        ownerPerson.setScore(11);//会抛出异常
        System.out.println(bean.getScore());

        Person nonOwnerPerson = test.getNonOwnerProxy(bean);
        nonOwnerPerson.setScore(21);
        nonOwnerPerson.setName("lisi");//会抛异常
        ownerPerson.setName("zhangsan");
        System.out.println(bean.getScore());
        System.out.println(bean.getName());

    }

    public Person getOwnerProxy(Person person) {
        return (Person) Proxy.newProxyInstance(person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                new OwninvokeHandler(person));
    }

    public Person getNonOwnerProxy(Person person) {
        return (Person) Proxy.newProxyInstance(person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                new NonOwnerInvocationHandler(person));
    }
}
