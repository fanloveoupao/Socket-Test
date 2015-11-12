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
	 * �������˵Ĵ���
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		// �����Ƕ˿ں�1024~65535
		try {
			// �˿��Ƿ����
			ServerSocket serverSocket = new ServerSocket(8000);
			System.out.println("�������ѿ������ȴ�����");
			// �ȴ�����
			Socket socket = serverSocket.accept();
			// ���ж�ȡ
			InputStream inputStream = socket.getInputStream();
			// ת����,���������ǿͻ����Ǳߴ��͹�����
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String info = reader.readLine();
			// ��ȡ���ǿͻ��˵���Ϣ
			System.out.println(info);
			// ���л�Ӧ����ӡ��
			OutputStream outputStream = socket.getOutputStream();
			PrintStream printStream = new PrintStream(outputStream);
			printStream.println("��ã��ͻ������Ƿ����");
			outputStream.close();
			inputStream.close();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}

}
