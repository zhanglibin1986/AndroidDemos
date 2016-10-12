package com.zlb.testIO;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * RandomAccessFile的绝大多数功能，但不是全部，已经被JDK
 * 1.4的nio的"内存映射文件(memory-mapped files)"给取代了
 * ，你该考虑一下是不是用"内存映射文件"来代替RandomAccessFile了。
 * 内存映射文件能让你创建和修改那些因为太大而无法放入内存的文件。有了内存映射文件
 * ，你就可以认为文件已经全部读进了内存，然后把它当成一个非常大的数组来访问。这种解决办法能大大简化修改文件的代码。
 * fileChannel.map(FileChannel.MapMode mode, long position, long
 * size)将此通道的文件区域直接映射到内存中
 * 。注意，你必须指明，它是从文件的哪个位置开始映射的，映射的范围又有多大；也就是说，它还可以映射一个大文件的某个小片断。
 * 
 * MappedByteBuffer是ByteBuffer的子类，因此它具备了ByteBuffer的所有方法，但新添了force()
 * 将缓冲区的内容强制刷新到存储设备中去
 * 、load()将存储设备中的数据加载到内存中、isLoaded()位置内存中的数据是否与存储设置上同步。这里只简单地演示了一下put
 * ()和get()方法，除此之外，你还可以使用asCharBuffer( )之类的方法得到相应基本类型数据的缓冲视图后，可以方便的读写基本类型数据。
 */
public class TestMappedByteBuffer {
	// 该程序创建了一个128Mb的文件，如果一次性读到内存可能导致内存溢出，但这里访问好像只是一瞬间的事，这是因为，真正调入内存的只是其中的一小部分，其余部分则被放在交换文件上。这样你就可以很方便地修改超大型的文件了(最大可以到2
	// GB)。注意，Java是调用操作系统的"文件映射机制"来提升性能的。
	static int length = 0x8000000; // 128 Mb

	public static void main(String[] args) throws Exception {
		// 为了以可读可写的方式打开文件，这里使用RandomAccessFile来创建文件。
		FileChannel fc = new RandomAccessFile("test.dat", "rw").getChannel();
		// 注意，文件通道的可读可写要建立在文件流本身可读写的基础之上
		MappedByteBuffer out = fc
				.map(FileChannel.MapMode.READ_WRITE, 0, length);
		// 写128M的内容
		for (int i = 0; i < length; i++) {
			out.put((byte) 'x');
		}
		System.out.println("Finished writing");
		// 读取文件中间6个字节内容
		for (int i = length / 2; i < length / 2 + 6; i++) {
			System.out.print((char) out.get(i));
		}
		fc.close();
	}
}
