package android.zlb.java;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;

//import com.thoughtworks.xstream.core.util.Pool;

public class Test {
    public static void main(String[] args) {
        HashMap<Double, Double> map = new HashMap<Double, Double>();
        map.put(0.0, 0.0);
        System.out.println(
                "has " + map.containsKey(new Double(0)) + " , " + map.containsValue(new Double(0)));

        //		String url = "http://sso.letv.com/user/mloginHome?next_action=http%3A%2F%2Fhd.my.letv.com%2Fcheckin%2Findex%23doex";
        //		String nextAction = url.substring(url.indexOf("next_action=") + "next_action=".length(), url.length());
        //		System.out.println(nextAction);
        //		ArrayList<String> ar = new ArrayList<String>();
        //		ar.add("a");
        //		ar.add("b");
        //		ar.add(2, "c");
        //		System.out.println("array ==== " + ar.toString());
        //
        //
        //		String t1 = "a\\b";
        //		if(t1.contains("\\")) {
        //			System.out.println("t1 = " + t1);
        //		}
        //
        //		String t2 = t1.replace("\\", "");
        //
        //		System.out.println("t2 = " + t2);
        //
        //
        //		String s1 = "abc?asdlfk&dsaklf&123";
        //		System.out.println(s1);
        //		String s2 = s1.substring(0, s1.indexOf("?"));
        //		System.out.println(s2);
        //		String[] s3 = s1.split("\\?");
        //		System.out.println(s3[0]);
        //		print(s3);
        //		BlockingDeque<String> deque;
        //
        //		Test t = new Test();
        //		Person p = new Person();
        //		p.setName("zhangsan");
        //		System.out.println(p);
        //		t.changeName(p);
        //		System.out.println(p);
        //		System.out.println(System.currentTimeMillis());
        //		System.out.println(formatDoubleNum(System.currentTimeMillis() / 1000.0, 2));
        String url = "http://breadtrip.com/?city=beijing&next_start=123433&2name=zhang3";
        String url1 = "http://breadtrip.com/?city=beijing&next_start=1234332name=zhang3";
        String url2 = "http://breadtrip.com/?city=beijing&next_start=123433";

        System.out.println(url.replaceAll("next_start=\\d+&?", ""));
        System.out.println(url1.replaceAll("next_start=\\d+&?", ""));
        System.out.println(url2.replaceAll("next_start=\\d+&?", ""));

        HashMap<String, String> hash = new HashMap();
        hash.put("a", "1");
        hash.put("a", "2");
        System.out.println("" + hash.size() + " , " + hash.get("a"));
    }

    /**
     * 格式化价格 两位小数还是一位小数
     */
    public static String formatDoubleNum(double price, int num) {
        DecimalFormat format = null;
        if (num == 1) {
            format = new DecimalFormat("#0.0");
            return format.format(price);
        } else if (num == 2) {
            format = new DecimalFormat("#0.00");
            return format.format(price);
        }

        return String.valueOf(price);
    }

    private void changeName(Person p) {
        p.setName("changed");
    }

    public static void print(String[] array) {
        System.out.println(Arrays.toString(array));
    }
}

class Person {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return name;
    }
}