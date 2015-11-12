package com.vince;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerDemo {

	/**
	 * 服务器端的代码
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		// 参数是端口号1024~65535
		try {
			// 端口是否可用
			ServerSocket serverSocket = new ServerSocket(8000);
			System.out.println("服务器已开启，等待连接");
			// 等待连接
			Socket socket = serverSocket.accept();
			// 进行读取
			InputStream inputStream = socket.getInputStream();
			// 转换流,读的数据是客户端那边传送过来的
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String info = reader.readLine();
			// 读取的是客户端的信息
			System.out.println(info);
			// 进行回应，打印流
			OutputStream outputStream = socket.getOutputStream();
			PrintStream printStream = new PrintStream(outputStream);
			printStream.println("你好，客户端我是服务端");
			outputStream.close();
			inputStream.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
