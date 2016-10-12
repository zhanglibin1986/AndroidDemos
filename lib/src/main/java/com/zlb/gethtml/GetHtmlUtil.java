package com.zlb.gethtml;

import com.java.net.download.DownPicture;
import com.zlb.util.PatternUtil;
import static com.zlb.util.PrintUtil.print;
import com.zlb.util.PublicUtil;
import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhanglibin on 2014/8/19.
 */
public class GetHtmlUtil {
    /**
     * 批量下载指定网址的图片至指定目录,耗时！
     * @param url 网址（抓取该网页内的图片）
     * @param encode 网页内容编码
     * @param savePath 下载到指定目录下
     */
    public static void downPic(String url, String encode, String savePath) {
        String htmlContent = getHtmlContent(url, encode);
        List<String> picUrls = getPatternLists(htmlContent, PatternUtil.PATTERN_IMGS);
        for(String pic : picUrls) {
            DownPicture.getPicture(savePath, pic);
        }
    }

    /**
     * 将符合正则的所有数据保存到List中
     * @param contents 原始数据
     * @param pattern 正则 <link>PatternUtil.PATTERN_HTM等
     * @return
     */
    public static List<String> getPatternLists(String contents, String pattern) {
        List<String> list = new ArrayList<String>();
        Pattern pa = Pattern.compile(pattern, Pattern.DOTALL);
        Matcher ma = pa.matcher(contents);
        while (ma.find()) {
            list.add(ma.group());
        }
        Object[] test = list.toArray();
        System.out.println("test = " + Arrays.toString(test));
        return list;
    }

    /**
     * For test
     * @param url
     * @param encode
     * @param pattern
     */
    public static void printWebContent(String url, String encode, String pattern) {
        System.out.println("url = " + url);
        String htmlContent = getHtmlContent(url, encode);
        System.out.println("htmlContent = " + htmlContent);
        List<String> hrefs = getPatternLists(htmlContent, pattern);
        String[] arrays = new String[hrefs.size()];
        hrefs.toArray(arrays);
        System.out.println("hrefs size = " + hrefs.size() + " , " + Arrays.toString(arrays));
    }


    /**
     * 读取网页全部内容
     * @param htmlurl 网址（抓取该网页内容）
     * @param encode  网页内容编码
     * @return 网页内容
     */
    public static String getHtmlContent(String htmlurl, String encode) {
        URL url;
        String temp;
        StringBuffer sb = new StringBuffer();
        try {
            url = new URL(htmlurl);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), encode));// 读取网页全部内容
            while ((temp = in.readLine()) != null) {
                sb.append(temp);
            }
            in.close();
        } catch (final MalformedURLException me) {
            System.out.println("你输入的URL格式有问题!");
            me.getMessage();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String getLocalHtmlContent(File file, String encode) {
        String temp;
        StringBuffer sb = new StringBuffer();
        print("start read file " + file.getAbsolutePath());
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));// 读取网页全部内容
            while ((temp = in.readLine()) != null) {
                sb.append(temp);
            }
            in.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static File[] getLocalHtmFiles(String path) {
        File folder = new File(path);
        if(!folder.exists()) {
            print("file " + path + " does'nt exist!");
            return null;
        }
        File[] files = folder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".htm");
            }
        });

        return files;
    }

    /**
     * 迅雷下载地址解码
     * @return
     */
    public String decode(String thunderUrl) {
        String result = null;
        try {
            result = new String(Base64.decodeBase64(thunderUrl.getBytes("gbk")), "gbk");
        } catch (UnsupportedEncodingException e) {
            System.out.println("迅雷地址解码异常， thunderUrl = " + thunderUrl);
            e.printStackTrace();
        }
        if(!PublicUtil.isEmpty(result)) {
            if(result.startsWith("AA")) {
                result = result.substring(2);
            }
            if(result.endsWith("ZZ")) {
                result = result.substring(0, result.length() - 2);
            }
        }
        return result;
    }

    /**
     * 二进制数据编码为BASE64字符串
     *
     * @param bytes
     * @return
     * @throws Exception
     */
    public static String encode(final byte[] bytes) {
        return new String(Base64.encodeBase64(bytes));
    }
}
