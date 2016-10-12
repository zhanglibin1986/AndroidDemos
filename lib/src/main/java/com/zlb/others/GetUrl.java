package com.zlb.others;
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
  
public class GetUrl {  
     
  
    public static void main(String[] args) {  
    
//        Pattern p = Pattern.compile("^(http|www|ftp|)?(://)?(//w+(-//w+)*)(//.(//w+(-//w+)*))*((://d+)?)(/(//w+(-//w+)*))*(//.?(//w)*)(//?)?(((//w*%)*(//w*//?)*(//w*:)*(//w*//+)*(//w*//.)*(//w*&)*(//w*-)*(//w*=)*(//w*%)*(//w*//?)*(//w*:)*(//w*//+)*(//w*//.)*(//w*&)*(//w*-)*(//w*=)*)*(//w*)*)$",Pattern.CASE_INSENSITIVE );   
//    	/^[h][t]{2}[p][:][\/][\/][w]{3}[\.][0-9A-Za-z]+[\.][a-z]{2,3}([\/][0-9A-Za-z]+)+([\/][0-9A-Za-z]+[.][a-z]+)?$/
//    	Pattern p = Pattern.compile("\\b(https?|ftp|file)://[-A-Z0-9+&@#/%?=~_|!:,.;]*[-A-Z0-9+&@#/%=~_|]",Pattern.CASE_INSENSITIVE);
    	Pattern p = Pattern.compile("(http://)?[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?",Pattern.CASE_INSENSITIVE);
//    	Patterns ps ;
//    	Pattern p = Pattern.compile(Pattern.,Pattern.CASE_INSENSITIVE);
    	
//    	p.
        Matcher m = p.matcher("我正在#PPTV Android 手机客户端#观看#风和日丽 第4集#一起来看吧！http://t.cn/zWPu6am");    
//          System.out.println(m.find());
          if(m.find()){  
              System.out.println(m.group());  
          }  
          
          m = p.matcher("http://baike.baidu.com/view/230199.htm?fr=ala0_1阿拉丁所开发叫阿的思路付款 http://www.hello.com sfg");  
          
          if(m.find()){  
//              System.out.println(m.group());  
        	  System.out.println(m.group());
          }  
            
          m = p.matcher("http://www.google.cn/gwt/x?u=http%3A%2F%2Fanotherbug.blog.chinajavaworld.com%2Fentry%2F4550%2F0%2F&btnGo=Go&source=wax&ie=UTF-8&oe=UTF-8");  
              
          if(m.find()){  
              System.out.println(m.group());  
          }  
            
          m = p.matcher("http://zh.wikipedia.org:80/wiki/Special:Search?search=tielu&go=Go");  
          
          if(m.find()){  
              System.out.println(m.group());  
              String ss = "al;dkjf";
              System.out.println(ss.lastIndexOf("d")+" , " + ss.indexOf("d"));
          }         
            
    }  
    
    public static String concatGroups(Matcher matcher) {
        StringBuilder b = new StringBuilder();
        final int numGroups = matcher.groupCount();

        for (int i = 1; i <= numGroups; i++) {
            String s = matcher.group(i);

            System.err.println("Group(" + i + ") : " + s);

            if (s != null) {
                b.append(s);
            }
        }

        return b.toString();
    }
  
}  