package com.vince;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MoreClientDemo {

	/**
	 * @param args
	 */
	static boolean flag = true;

	public static void main(String[] args) {
		// ���ӷ�����
		try {
			// �˿ںŵ��쳣
			Socket socket = new Socket("localhost", 8000);
			System.out.println("�ͻ���2���ӳɹ�");
			OutputStream outputStream = socket.getOutputStream();
			InputStream inputStream = socket.getInputStream();
			// ����д
			PrintStream printStream = new PrintStream(outputStream);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			// ����д�뷽����룬��д���

			Scanner scanner = new Scanner(System.in);
			while (flag) {
				System.out.println("������");
				String info = scanner.next();
				printStream.println(info);
				// ��ȡ����˵�
				System.out.println("�ͻ���:" + reader.readLine());
				if (info.equalsIgnoreCase("byebye")) {
					flag = false;
				}
			}
			// �����Ƿ������Ǳ߷�������
			outputStream.close();
			inputStream.close();
		} catch (UnknownHostException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}

}
