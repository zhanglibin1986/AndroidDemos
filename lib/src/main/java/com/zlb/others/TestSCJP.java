package com.zlb.others;

public class TestSCJP {
	//3
	public void func(Person person) {
		person.setName("b");
		myFunc(person, 30);
	}
	//3
	public void myFunc(Person person, int age) {
		person.setAge(age);
	}
	//4
	public static void add3(Integer i) {
		int val = i.intValue();
		val += 3;
		i = new Integer(val);
	}
	public static void add4(int i) {
		i += 4;
	}
	public static void main(String[] args) {
		int i = 0xfffffff1;
		int j = ~i;
		System.out.println(j);
		
		Integer a = new Integer(42);
		Long b = new Long(42);
		Double c = new Double(42.0);
		
		System.out.println("------------------------------------------1\n"+a.equals(42));
		System.out.println(a.equals(c));
		
		String s = "a";
		System.out.println("------------------------------------------2\naa "+s.equals(null));
		
		TestSCJP scjp = new TestSCJP();
		Person p = new Person();
		scjp.func(p);
		System.out.println("------------------------------------------3\nname = "+p.name+" , age = "+p.age);
		
		Integer i4 = new Integer(0);
		add3(i4);
		System.out.println("------------------------------------------4\ni4 = "+i4.intValue());
		add4(i4);
		System.out.println("i4 = "+i4.intValue());
	}
}


//3
class Person {
	int age = 20;
	String name = "a";
	public void setAge(int age) {
		this.age = age;
	}
	public void setName(String n) {
		this.name = n;
	}
}
















