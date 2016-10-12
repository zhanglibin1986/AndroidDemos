package com.zlb.java.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class TestHttpUrlConnection {
	public void doPost() throws IOException {
		/*
		 * URL请求的类别分为二类,GET与POST请求。二者的区别在于： 
		 * a:) get请求可以获取静态页面，也可以把参数放在URL字串后面，传递给servlet， 
		 * b:) post与get的不同之处在于post的参数不是放在URL字串里面，而是放在http请求的正文内。
		 */
		
		URL url = new URL("http://localhost:8080/TestHttpURLConnectionPro.do");
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();

		// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
		// http正文内，因此需要设为true, 默认情况下是false;
		urlConn.setDoOutput(true);

		// 设置是否从httpUrlConnection读入，默认情况下是true;
		urlConn.setDoInput(true);

		// Post 请求不能使用缓存
		urlConn.setUseCaches(false);

		// 设定传送的内容类型是可序列化的java对象
		// (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
		urlConn.setRequestProperty("Content-type","application/x-java-serialized-object");

		// 设定请求的方法为"POST"，默认是GET
		urlConn.setRequestMethod("POST");

		// 连接，上面对urlConn的所有配置必须要在connect之前完成，
		urlConn.connect();

		// 此处getOutputStream会隐含的进行connect (即：如同调用上面的connect()方法，
		// 所以在开发中不调用上述的connect()也可以)。
		OutputStream outStrm = urlConn.getOutputStream();
		
		// 现在通过输出流对象构建对象输出流对象，以实现输出可序列化的对象。
		ObjectOutputStream oos = new ObjectOutputStream(outStrm);

		// 向对象输出流写出数据，这些数据将存到内存缓冲区中
		oos.writeObject(new String("我是测试数据"));

		// 刷新对象输出流，将任何字节都写入潜在的流中（些处为ObjectOutputStream）
		oos.flush();

		// 关闭流对象。此时，不能再向对象输出流写入任何数据，先前写入的数据存在于内存缓冲区中,
		// 再调用下边的getInputStream()函数时才把准备好的http请求正式发送到服务器
		oos.close();

		// 调用HttpURLConnection连接对象的getInputStream()函数,
		// 将内存缓冲区中封装好的完整的HTTP请求电文发送到服务端。
		InputStream inStrm = urlConn.getInputStream(); // <===注意，实际发送请求的代码段就在这里
		
	//----------------------------------
		/*
		 * Post传参的方法
		 */
		OutputStream os = urlConn.getOutputStream();
	    String param = new String();
	    param = "CorpID=123&LoginName=qqq&name=" + URLEncoder.encode("汉字","GBK"); ;
	    os.write(param.getBytes());
	    
	//----------------------------------
	    /*
	     * 超时设置，防止 网络异常的情况下，可能会导致程序僵死而不继续往下执行
	     */
	    
	    //JDK 1.5以前的版本，只能通过设置这两个系统属性来控制网络超时:
	    //连接主机的超时时间（单位：毫秒）
	    System.setProperty("sun.net.client.defaultConnectTimeout", "30000"); 
	    //从主机读取数据的超时时间（单位：毫秒）
	    System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 

	    //在JDK 1.5以后可以这样来设置超时时间
	    HttpURLConnection urlCon = (HttpURLConnection)url.openConnection();
	    urlCon.setConnectTimeout(30000);
	    urlCon.setReadTimeout(30000);
	    
	//----------------------------------
	    /*
	     * 总结：
	     * HttpURLConnection的connect()函数，实际上只是建立了一个与服务器的tcp连接，并没有实际发送http请求。
	     * 无论是post还是get，http请求实际上直到HttpURLConnection的getInputStream()这个函数里面才正式发送出去。
	     * 
	     * 对HttpURLConnection对象的一切配置都必须要在connect()函数执行之前完成。
	     * 而对outputStream的写操作，又必须要在inputStream的读操作之前。
	     * 这些顺序实际上是由http请求的格式决定的。
	     * 
	     * 在http头后面紧跟着的是http请求的正文，正文的内容是通过outputStream流写入的，
	     * 实际上outputStream不是一个网络流，充其量是个字符串流，往里面写入的东西不会立即发送到网络，
		 * 而是存在于内存缓冲区中，待outputStream流关闭时，根据输入的内容生成http正文。
		 * 至此，http请求的东西已经全部准备就绪。在getInputStream()函数调用的时候，就会把准备好的http请求
		 * 正式发送到服务器了，然后返回一个输入流，用于读取服务器对于此次http请求的返回信息。由于http
		 * 请求在getInputStream的时候已经发送出去了（包括http头和正文），因此在getInputStream()函数
		 * 之后对connection对象进行设置（对http头的信息进行修改）或者写入outputStream（对正文进行修改）
		 * 都是没有意义的了，执行这些操作会导致异常的发生。
	     * 
	     */
	}
}
