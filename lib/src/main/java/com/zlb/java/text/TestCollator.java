package com.zlb.java.text;

import java.text.CollationKey;
import java.text.Collator;
import java.text.RuleBasedCollator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

import com.zlb.util.PublicUtil;
/**
 * 中文api：http://dlc.sun.com.edgesuite.net/jdk/jdk-api-localizations/jdk-api-zh-cn/builds/latest/html/zh_CN/api/index.html?overview-summary.html<p>
 * CollationKey:http://dlc.sun.com.edgesuite.net/jdk/jdk-api-localizations/jdk-api-zh-cn/builds/latest/html/zh_CN/api/index.html?overview-summary.html
 * 
 * @author zhanglibin
 *
 */
public class TestCollator {
	public static void main(String[] args) {
		TestCollator testCollator = new TestCollator();

		// 需求：实现中文排序
		String[] test1 = new String[] { "张三", "李四", "王五", "赵柳" };
		PublicUtil.print(0, test1);
		testCollator.sortChinese(test1);
		PublicUtil.print(0, test1);

		// 第二种情况
		ComparableBean[] nameContent = { new ComparableBean("一切从实际出发"),
				new ComparableBean("立于不败之地"), new ComparableBean("多项式"),
				new ComparableBean("贯彻落实"), new ComparableBean("密切联系群众"),
				new ComparableBean("四项基本原则"), new ComparableBean("咬牙切齿"),
				new ComparableBean("恭恭敬敬"), new ComparableBean("民警"),
				new ComparableBean("经营承包责任制") };

		PublicUtil.print(1, nameContent);

		Arrays.sort(nameContent, new ComparableBeanComparator());

		PublicUtil.print(1, nameContent);
	}

	/**
	 * 需求：实现中文排序
	 * 
	 * @param text
	 *            内容是中文时
	 */
	private void sortChinese(String[] text) {
		Comparator cmp = Collator.getInstance(Locale.CHINA);
		Arrays.sort(text, cmp);
	}
}

/**
 * 第二种情况，比较中文字符
 * 
 * @author zhanglibin
 * 
 */
class ComparableBeanComparator implements Comparator {

	RuleBasedCollator collator; // you can set your rules for the instance
								// "collator"

	public ComparableBeanComparator() {
		collator = (RuleBasedCollator) Collator
				.getInstance(Locale.CHINA);// try testing various
														// locales
	}

	public int compare(Object obj1, Object obj2) {
		String tempname1 = ((ComparableBean) obj1).getName();
		String tempname2 = ((ComparableBean) obj2).getName();
		/**
		 * CollationKey 表示遵守特定 Collator 对象规则的 String。比较两个 CollationKey 将返回它们所表示的
		 * String 的相对顺序。使用 CollationKey 来比较 String 通常比使用 Collator.compare
		 * 更快。因此，当必须多次比较 String 时（例如，对一个 String 列表进行排序），使用 CollationKey 会更高效。
		 * http://www.cjsdn.net/doc/jdk50/java/text/CollationKey.html
		 */
		CollationKey c1 = collator.getCollationKey(tempname1);
		CollationKey c2 = collator.getCollationKey(tempname2);
		// return collator.compare(((CollationKey) c1).getSourceString(),
		// ((CollationKey) c2).getSourceString());
		return collator.compare(((CollationKey) c2).getSourceString(),
				((CollationKey) c1).getSourceString());
	}
}

class ComparableBean {
	private String name;

	public ComparableBean(String name) {

		this.name = name;
	}

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
