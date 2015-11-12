package com.vince;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MoreServerDemo {

	/**
	 * @param args
	 */
	// 线程池的问题
	static ExecutorService executorService = Executors.newCachedThreadPool();
	static boolean flag = true;

	public static void main(String[] args) {
		try {

			// 端口是否可用
			ServerSocket serverSocket = new ServerSocket(8000);
			System.out.println("服务器已开启，等待连接");
			// 等待连接
			while (true) {
				Socket socket = serverSocket.accept();
				executorService.execute(new ClientThread(socket));
			}
			// 进行读取

		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	// 构造线程使得服务端可以接受多个客户端
	static class ClientThread implements Runnable {
		private Socket socket;

		public ClientThread(Socket socket) {
			// TODO 自动生成的构造函数存根
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				// TODO 自动生成的方法存根
				// IP地址
				System.out.println("客户端的连接地址:"
						+ socket.getInetAddress().getHostAddress());
				InputStream inputStream = socket.getInputStream();
				// 转换流,读的数据是客户端那边传送过来的
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(inputStream));

				OutputStream outputStream = socket.getOutputStream();
				PrintStream printStream = new PrintStream(outputStream);
				while (flag) {
					String info = reader.readLine();
					// 读取的是客户端的信息
					System.out.println(info);
					if (info.equalsIgnoreCase("byebye")) {
						flag = false;
						printStream.println("baibai");
					}
					printStream.println("你好，客户端我是服务端");
				}
				// 进行回应，打印流
				outputStream.close();
				inputStream.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

}
