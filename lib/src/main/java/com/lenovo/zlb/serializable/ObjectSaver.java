package com.lenovo.zlb.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public class ObjectSaver {
	public static void main(String[] args) {
		Date d = new Date();
		System.out.println(d.getTime());
		Customer customer = new Customer("ZhangLibin", 26, d);
		String path = "/home/zhanglibin/testSerializable.obj";
		try {
			ObjectOutputStream oops = new ObjectOutputStream(new FileOutputStream(new File(path)));
			oops.writeObject("Serializable");
			oops.writeObject(d);
			oops.writeObject(customer);
			oops.flush();
			oops.close();
			ObjectInputStream oips = new ObjectInputStream(new FileInputStream(path));
			String title = (String)oips.readObject();
			Date d2 = (Date) oips.readObject();
			Customer c = (Customer) oips.readObject();
			
			System.out.println(title);
			System.out.println(c.getName() + " , age = "+c.getAge());
			Thread.sleep(3000);
//			System.out.println(c.getD().getTime());//Customer中的Date对象因为被标识为transient，所以此处会报java.lang.NullPointerException
			System.out.println("过了3秒，但是传过来的Date对象和之前的Date对象是一致的："+d2.getTime());//传过来的Date对象。被传过来了，且其时间和传过来的时间是一致的，所以证明保存了对象的状态。
			c.setD(new Date());//重新给传过来的Customer对象设置时间
			System.out.println("设置后的时间:"+c.getD().getTime());//设置后的时间
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}

class Customer implements Serializable{
	private String name;
	private int age;
	
	/*
	 * java语言的关键字，变量修饰符，如果用transient声明一个实例变量，当对象存储时，它的值不需要维持。
　　    Java的serialization提供了一种持久化对象实例的机制。当持久化对象时，可能有一个特殊的对象数据成员，
　　    我们不想用serialization机制来保存它。为了在一个特定对象的一个域上关闭serialization，可以在这个
　　    域前加上关键字transient。当一个对象被序列化的时候，transient型变量的值不包括在序列化的表示中，
　　    然而非transient型的变量是被包括进去的。
	 */
	transient private Date d; 
	public Customer() {
		
	}
	public Customer(String name,int age,Date d) {
		this.name = name;
		this.age = age;
		this.d = d;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getD() {
		return d;
	}
	public void setD(Date d) {
		this.d = d;
	}
}