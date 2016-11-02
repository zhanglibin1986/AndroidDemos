package com.zlb.java.lang.object;

import com.zlb.util.PublicUtil;

public class TestObject {
    public static void main(String[] args) {
        /******************* equals() *******************/
        TestObject test = new TestObject();
        test.testEqual();
    }

    private void testEqual() {
        A a1 = new A();
        A a2 = new A();
        PublicUtil.print(1, a1.equals(a2));
        PublicUtil.print(1, (a1 == a2));

        B b1 = new B("B", 18);
        B b2 = new B("B", 17);

        PublicUtil.print(2, b1.equals(b2));
        PublicUtil.print(2, (b1 == b2));

        String s1 = "s";
        String s2 = "s";
        String s3 = new String("s");

        PublicUtil.print(3, s1 == s2);
        PublicUtil.print(3, s1.equals(s2));
        PublicUtil.print(3, s1 == s3);
        PublicUtil.print(3, s2 == s3);

        Integer i1 = new Integer(0);
        Integer i2 = new Integer(0);

        PublicUtil.print(4, Integer.valueOf(0).equals(Integer.valueOf(0)));
        PublicUtil.print(4, new Integer(0).equals(new Integer(0)));
        PublicUtil.print(4, i1.equals(i2));
    }

    class A {

    }

    class B {
        String name;
        int age;

        public B(String name, int age) {
            this.name = name;
            this.age = age;
        }

        /**
         * 引用类型equals 和 "==" 默认都是比较引用是否指向同一个对象，重写equals方法可以改变比较策略，详见下面类B
         *
         * 重写equals方法需要满足以下规定：
         * 自反性：对于任何引用，只要不为null，那么对其自身使用.equals方法将永远返回True(x.equals.(x) 返回true<p>
         * 对称性：对于任何引用a与b，若都不为null，那么a对b进行equals检查后得到的结果，与b对a进行equals检查得到的结果相同，也就是说a.equals(b) =
         * b.equals(a);<p>
         * 传递性：对于任何非空引用值a和b，在对象上参与equals比较的成员没有被修改的情况下，多次用a对b进行equals检查的结果应当始终相同。<p>
         * 对于任何非空引用值a，对其用null进行equals检查应当始终返回false，也就是说a.equals(null) = false<p>
         * <p>
         * 以上规则不遵守也可通过编译，但在运行的时候可能会得到无法预料的结果，请一定注意。<p>
         *
         * 根据以上规则可总结出重写equals的步骤，如下B类equals方法。
         *
         * 但这样还不行，因为重写了equals方法就必须重写hashCode方法，否则两个等价的对象可能得到不通的哈希码，这时在使用集合框架的时候可能产生严重的问题。
         *
         * @author zhanglibin
         */
        @Override
        public boolean equals(Object obj) {
            // TODO Auto-generated method stub
            if (this == obj) {
                return true;
            }

            if (obj == null) {
                return false;
            }

            if (!(obj instanceof B)) {
                return false;
            }

            B o = (B) obj;
            if (this.name.equals(o.name) && this.age == o.age) {
                return true;
            } else {
                return false;
            }
        }

        /**
         * * 重写hashcode要满足的条件是：
         * java应用程序执行期间，于同一对象上多次调用hashCode方法时，在对象上参与equals比较的成员没有被修改的情况下，必须一致的返回相同的整数。
         * hashCode相同，equals方法比较不一定为true。
         * 但equals方法为true，hashCode一定相同。
         *
         * 参考实现，通常的方法是将成员变量与某一素数相乘，目的是使哈希码技能满足要求有能均匀分布
         */
        @Override
        public int hashCode() {
            // TODO Auto-generated method stub
            return 7 * this.name.hashCode() + 13 * Integer.valueOf(this.age).hashCode();
        }
    }
}
