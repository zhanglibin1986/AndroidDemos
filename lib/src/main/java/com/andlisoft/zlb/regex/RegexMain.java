package com.andlisoft.zlb.regex;

import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
  
public class RegexMain {  
  
    //使用java正则表达式获取url地址中的主域名代码如下:  
    /** 
     * 如果要得到 chinajavaworld.com/entry/4545/0/正则表达式最后加上 .* 即可. 
     *如要取完整域名，使用以下代码: 
     *Pattern.compile("[^//]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE); 
     */   
    public static void main(String[] args) {  
//        String url = "http://anotherbug.blog.chinajavaworld.com/entry/4545/0/";  
//        //Pattern p = Pattern.compile("(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)",Pattern.CASE_INSENSITIVE);  
//          
//        //获取完整的域名  
//        Pattern p =Pattern.compile("[^//]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);  
//        Matcher matcher = p.matcher(url);  
//        matcher.find();  
//        System.out.println(matcher.group());  
    	
    	String url = "<a href=\"http://www.baidu.com/gaoji/preferences.html\" name=\"tj_setting\">搜索设置\"http:kk.htm\"</a>|<a hr\"http://123.html\"ef=\"https://passport.baidu.com/v2/?login&tpl=mn&u=http%3A%2F%2Fwww.baidu.com%2F\" name=\"tj_login\" id=\"lb\" onclick=\"return false;\">登录</a><a href=\"https://passport.baidu.com/v2/?reg&regType=1&tpl=mn&u=http%3A%2F%2Fwww.baidu.com%2F\" target=\"_blank\" name=\"tj_reg\" class=\"reg\">";
    	Pattern p = Pattern.compile("\"http[^\"]*[html,htm]\"");
    	Matcher matcher = p.matcher(url);
    	while(matcher.find()) {
    		System.out.println(matcher.group());
    	}
    }  
}  
