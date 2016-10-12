package com.lenovo.zlb.XStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//import com.thoughtworks.xstream.XStream;
//import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

/*
 * xStream性能不高，参考
 * https://github.com/eishay/jvm-serializers/wiki
如果纯java场景应用，可以参考kryo
如果需要跨语言，可以参考ProtoBuf
 */
/*
 * xStream可以轻易的将Java对象和xml文档相互转换。使用起来非常简单，见官方教程：http://xstream.codehaus.org/tutorial.html
 * 要引入包
 * 总结起来就是关键的三步而已：
 * 1.XStream xstream = new XStream();
 * 2.String xml = xstream.toXML(new MyObject());
 * 3.MyObject mo = (MyObject)xstream.fromXML(xml);
 */

public class XStreamTest {
//	public static void main(String[] args) {
//		Info info = new Info("Beijing");
//		Person me = new Person("zhanglibin",26,info);
//		
//		XStream xs = new XStream();
//		xs.alias("MyAliasName", Person.class);//对类重命名，可打开对比
//		xs.aliasField("mingzi", Person.class, "name");//对属性重命名
//		
//		//将对象转化为xml
//		
//		String result = xs.toXML(me);
//		System.out.println(result);
//		
//		//将xml转化为对象
//		Person p = (Person) xs.fromXML(result);
//		System.out.println(p);
//		
//		/*
//		 * xStream对JSON也有非常好的支持，它提供了2个模型驱动。用这2个驱动可以完成Java对象到JSON的相互转换。
//		 * 使用JettisonMappedXmlDriver驱动，将Java对象转换成json，需要添加jettison.jar 
//		 */
//	    xs = new XStream(new JettisonMappedXmlDriver());
//
//	    xs.setMode(XStream.NO_REFERENCES);
//		
//		
//		/*
//		 * JSON的转换和XML的转换用法一样，只是创建XStream需要传递一个参数，这个参数就是xml到JSON映射转换的驱动。这里会降到两个驱动，分别是
//		 * JettisonMappedXmlDriver、JsonHierarchicalStreamDriver。
//		 * JsonHierarchicalStreamDriver完成Java对象到JSON的转换 
//		 */
//
//		
//	}
}

class Person {
	private String name;
	private int age;
	private Info info;
	
	public Person() {
		
	}
	
	public Person(String name,int age,Info info) {
		this.name = name;
		this.age = age;
		this.info = info;
	}
	public String toString() {
		return "Name = "+name+" , age = "+age+" , address : "+info.getAdd();
	}
}

class Info {
	String address;
	public Info() {
		
	}
	public Info(String address) {
		this.address = address;
	}
	public String getAdd() {
		return address;
	}
}











