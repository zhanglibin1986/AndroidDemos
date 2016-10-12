package com.zlb.gethtml;

import com.zlb.util.PatternUtil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * Created by zhanglibin on 2014/8/5.
 */
public class GetHtml {
    public static final String TEST_URL_1 = "http://dyall.2121921.net";
    public static final String TEST_URL_2 = "http://www.baidu.com/";
    public static final String TEST_URL_3 = "http://dyall.2121921.net/2011-1-4.htm";
    public static final String TEST_URL_4 = "http://dyall.2121921.net/2011-1-4.htm";

    public static final String thunderUrl_1 = "QUFodHRwOi8vczMzLmZyZWUtZHZkNTguY29tL2hkZHZkL6G+w7/I1bj80MJwdjk5MC5jb22hv1PP38z1yuzFrsjLxt66wLuq0NSwrsnxy8bP7crcLmF2aVpa";


    public static final String headUrl = "http://dyall.2121921.net";
    public static void main(String[] args) {
        final GetHtml getHtml = new GetHtml();
//        GetHtmlUtil.printWebContent(TEST_URL_1, "utf-8" , PatternUtil.PATTERN_HTM);
//        getHtml.printWebContent(TEST_URL_4, "gbk", PatternUtil.PATTERN_IMGS);
//        getHtml.printWebContent(TEST_URL_4, "gbk", PatternUtil.PATTERN_CHINESE);
//        GetHtmlUtil.printWebContent(TEST_URL_4, "gbk", PatternUtil.PATTERN_THUNDER);
//        getHtml.printWebContent(TEST_URL_4, "gbk", PatternUtil.combinePatterns(PatternUtil.PATTERN_CHINESE, PatternUtil.PATTERN_IMGS, PatternUtil.PATTERN_THUNDER));
        //thunder://QUFodHRwOi8vczMzLmZyZWUtZHZkNTguY29tL2hkZHZkL6G+w7/I1bj80MJwdjk5MC5jb22hv1PP38z1yuzFrsjLxt66wLuq0NSwrsnxy8bP7crcLmF2aVpa
//          System.out.println(decode(thunderUrl_1));
//        getHtml.downPic(TEST_URL_4, "gbk","D:/test");
//        String s = encode("test".getBytes());
//        System.out.println("encode = " + s);
//        System.out.println(decode(s));
//        GetHtmlUtil.getPatternLists(GetHtmlUtil.getHtmlContent())
//        getHtml.saveHtml("D:/test", TEST_URL_4, "test.htm", "gbk");
        getHtml.saveHtml("J:/cache", TEST_URL_1, "main.htm", "utf-8");
        String mainContent = GetHtmlUtil.getHtmlContent(TEST_URL_1, "utf-8");//主页中的内容
        List<String> htmsNoHead = GetHtmlUtil.getPatternLists(mainContent, PatternUtil.PATTERN_HTM);
        final List<String> htms = getHtml.generateDetailPage(htmsNoHead);
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        ExecutorService executorService = E
        BlockingQueue blockingQueue = new ArrayBlockingQueue(30000);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 100, 30, TimeUnit.MILLISECONDS, blockingQueue);
//        for(int i = 0; i < htms.size(); i++) {
//            final int finalI = i;
//            threadPoolExecutor.execute(new Runnable() {
//                @Override
//                public void run() {
//                    String u = htms.get(finalI);
//                    String name = u.substring(u.lastIndexOf("/") + 1, u.length());
//                    getHtml.saveHtml("J:/cache/localHtm", htms.get(finalI), name, "gbk");
//                }
//            });
//        }

//        for(int i =0; i < htms.size(); i++) {
//            picUrlContents.add(GetHtmlUtil.getHtmlContent(htms.get(i), "gbk"));
////            picUrlContents.add(GetHtmlUtil.get)
//            System.out.println("total : " + htms.size() + "get the" + i + " , " + htms.get(i) + " ok !");
//        }

        List<String> picUrlContents = new ArrayList<String>();
        File[] files = GetHtmlUtil.getLocalHtmFiles("J:/cache/localHtm");
        for(int i =0; i < files.length; i++) {
            picUrlContents.add(GetHtmlUtil.getLocalHtmlContent(files[i], "gbk"));
            System.out.println("total : " + files.length + "get the" + i + " , " + files[i].getAbsolutePath() + " ok !");

        }

        for(int i = 0; i < picUrlContents.size(); i++) {
            final List<String> picurls = GetHtmlUtil.getPatternLists(picUrlContents.get(i), PatternUtil.PATTERN_IMGS);
            System.out.println(htms.get(i) + " has " + picurls.size() + " pictures!");
            String u = htms.get(i);
            final String picPath = "J:/cache/localHtm/" + u.substring(u.lastIndexOf("/") + 1, u.lastIndexOf("."));
//            for(int j = 0; j < picurls.size(); j++) {
//                final int finalJ = j;
//                threadPoolExecutor.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        DownPicture.getPicture(picPath, picurls.get(finalJ));
//                    }
//                });
//
//            }
        }

    }

    /**
     * 添加头，生成具体网页地址
     * @param pages
     * @return
     */
    private List<String> generateDetailPage(List<String> pages) {
        for(int i = 0 ; i < pages.size(); i++) {
            pages.set(i, headUrl + pages.get(i));
        }
        return pages;
    }

    /**
     *
     * @param savePath 保存路径
     * @param htmlurl 网页地址
     * @param name 保存的名称
     * @param encode 网页的编码
     * @return
     */
    private boolean saveHtml(String savePath, String htmlurl, String name, String encode) {
        File fileDir = new File(savePath);
        if(!fileDir.exists()) {
            fileDir.mkdirs();
        }
        File html = new File(savePath, name);
        if(html.exists()) {
            System.out.println(html.getAbsolutePath() + "已存在！不保存");
            return false;
        }

        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;

        URL url;
        String temp;
        try {
            fileWriter = new FileWriter(html);
            bufferedWriter = new BufferedWriter(fileWriter);
            url = new URL(htmlurl);
            bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), encode));// 读取网页全部内容
            while ((temp = bufferedReader.readLine()) != null) {
                bufferedWriter.write(temp);
            }
            System.out.println("save web html success! path = " + html.getAbsolutePath());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                if(bufferedReader != null) {
                    bufferedReader.close();
                }
                if(bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if(fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return false;
    }
}
