package com.java.net.download;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import static com.zlb.util.PrintUtil.print;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DownPicture {
	public static void main(String[] args) {
		DownPicture dp = new DownPicture();
		dp.getPicture("D:/1/2/", "http://fmn.rrfmn.com/fmn038/20100602/2025/p_large_36d9_21cb00038f222d13.jpg");
        //http://dyall.2121921.net/2011-1-12.htm
//		List<String> imgList = dp.getImageUrl(dp.getWebContent("http://www.ijjnews.com/channel/2012523/n538990167.html"));
//		for(int i = 0; i < imgList.size(); i ++) {
//			dp.getPicture("D:/1/2/", imgList.get(i));
//		}
//		getMp3("http://mplay.tingchina.com/yousheng/玄幻奇幻/召唤万岁_章鱼/004剑灵御姐.mp3", "D:/2/2/");
	}
	
	public static void getMp3(String srcUrl, String savePath) {
		URL url = null;
		URLConnection conn = null;
		FileOutputStream outfile = null;
		InputStream infile = null;
		File filePath = new File(savePath);
		
		try {
			url = new URL(srcUrl);
			conn = url.openConnection();
			infile = conn.getInputStream();
			outfile = new FileOutputStream(savePath + "a.mp3");
			byte[] buffer = new byte[1024];
			int b = 0;
			while((b = infile.read(buffer)) != -1) {
				outfile.write(buffer,0,b);
			}
			System.out.println("image is saved!");
			infile.close();
			outfile.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getPicture(String savePath, String imageUrl) {
		URL url = null;
		URLConnection conn = null;
		FileOutputStream outfile = null;
		InputStream infile = null;
		
		if(!savePath.substring(savePath.length()-1, savePath.length()-1).equals("/")) {
			savePath = savePath + "/";
		}
		File filePath = new File(savePath);
		if(!filePath.exists()) {
			filePath.mkdirs();
		}
		//图片的命名规则为：第一部分：1000以内的随机数，第二部分：当前时间，精确到毫秒
//		String name = "" + (new Random().nextInt(1000)) + "_"
//				+ Calendar.getInstance().get(Calendar.YEAR)
//				+ (Calendar.getInstance().get(Calendar.MONTH) + 1)
//				+ Calendar.getInstance().get(Calendar.DATE)
//				+ Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
//				+ Calendar.getInstance().get(Calendar.MINUTE)
//				+ Calendar.getInstance().get(Calendar.SECOND)
//				+ Calendar.getInstance().get(Calendar.MILLISECOND)
//				+ ".jpg";

        String name = imageUrl.substring(imageUrl.lastIndexOf("/") + 1, imageUrl.length());
        File f = new File(savePath);
        if(!f.exists()) {
            f.exists();
        }
        File p = new File(savePath + name);
        print("start down " + p.getAbsolutePath());
        if(p.exists()) {
            System.out.println(p.getAbsolutePath() + " has exist , don't download!");
            return;
        }

		try {
			url = new URL(imageUrl);
			conn = url.openConnection();
			infile = conn.getInputStream();
			outfile = new FileOutputStream(savePath + name);
			byte[] buffer = new byte[1024];
			int b = 0;
			while((b = infile.read(buffer)) != -1) {
				outfile.write(buffer,0,b);
			}
			System.out.println("------------------------------image " +p.getAbsolutePath() + " is saved!");
			infile.close();
			outfile.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getWebContent(String webUrl) {
		URL url = null;
		URLConnection conn = null;
		InputStream infile = null;
		BufferedReader bufferedReader = null; 
		StringBuffer result = null;
		try {
			url = new URL(webUrl);
			conn = url.openConnection();
			infile = conn.getInputStream();
			result = new StringBuffer();
			bufferedReader = new BufferedReader(new InputStreamReader(infile, "GBK"));
			String line = null;
			while((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
				result.append(line);
			}
			infile.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result.toString();
	}
	
	
	public static List<String> getWebUrl(String webContent) {
		ArrayList<String> list = new ArrayList<String>();
		Pattern p = Pattern.compile("\"http[^\"]*[html,htm]\"");
    	Matcher matcher = p.matcher(webContent);
    	while(matcher.find()) {
    		String temp = matcher.group();
    		list.add(temp.substring(1, temp.length()-1));
    	}
		return list;
	}
	

//    http://dyall.2121921.net/2011-1-12.htm
}



//http://fmn.rrfmn.com/fmn038/20100602/2025/p_large_36d9_21cb00038f222d13.jpg
