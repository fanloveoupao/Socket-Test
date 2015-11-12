# Socket-Test
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



public static void main(String[] args) {
		// 连接服务器
		try {
			// 端口号的异常
			Socket socket = new Socket("localhost", 8000);
			System.out.println("客户端2连接成功");
			OutputStream outputStream = socket.getOutputStream();
			InputStream inputStream = socket.getInputStream();
			// 进行写
			PrintStream printStream = new PrintStream(outputStream);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			// 进行写入方便读入，先写后读

			Scanner scanner = new Scanner(System.in);
			while (flag) {
				System.out.println("请输入");
				String info = scanner.next();
				printStream.println(info);
				// 读取服务端的
				System.out.println("客户端:" + reader.readLine());
				if (info.equalsIgnoreCase("byebye")) {
					flag = false;
				}
			}
			// 读的是服务器那边返回来的
			outputStream.close();
			inputStream.close();
		} catch (UnknownHostException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
