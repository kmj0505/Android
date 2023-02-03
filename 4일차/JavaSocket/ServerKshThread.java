package kcci.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerKshThread extends Thread {

	@Override
	public void run() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("localhost", 5000));    //��Ʈ��ũ ���α׷��� �����(ȣ��Ʈ, ȣ��Ʈ �ּ�)�� �ʿ��� 
			while (true) {   //listen�� ������(��� ť : �� �� �����ؼ� �������� ���� ���� ����ȴ�)
				System.out.println("[���� ��ٸ�]");     
				Socket socket = serverSocket.accept();   //accept�� ���ؼ� Ŭ���̾�Ʈ�� ������ �� ������ ����(ȣ��Ʈ, ȣ��Ʈ �ּҷ� ����� ��� Ǯ��)
				InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();  //����� client�� socket �ּҸ� return
				System.out.println("[���� ������] " + isa.getHostName());     //[���� ������] + client �ּ�

				byte[] bytes = null;  //byte �迭
				String message = null;

				InputStream is = socket.getInputStream();      //InputStream ��ü ����
				bytes = new byte[100];
				int readByteCount = is.read(bytes);       //���ŷ �Լ� read �����ؼ� ����
				message = new String(bytes, 0, readByteCount, "UTF-8");    //Client���� message�� ������ ���  (���� ������ ��ü�� stringŸ������ ��ȯ�ؼ� message�� ����)
				System.out.println("[������ �ޱ� ����]: " + message);  

				OutputStream os = socket.getOutputStream();    // ������ �����͸� ������ ���� OutputStream ����
				message = "Hello Client";        //�����޼��� ����
				bytes = message.getBytes("UTF-8");
				os.write(bytes);     //�޼��� byte�� ��ȯ�ؼ� ����
				os.flush();
				System.out.println("[������ ������ ����]");

				is.close();     //�Է��ϴ� ��ü is ����
				os.close();     //�Է��ϴ� ��ü os ����
				socket.close();  // ���� ����   -> while���̹Ƿ� ����ؼ� ���ư�
			}
		} catch (Exception e) {
		}

		if (!serverSocket.isClosed()) {
			try {
				serverSocket.close();
			} catch (IOException e1) {
			}
		}
	}

}
