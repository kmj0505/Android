package kcci.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientKshThread extends Thread {

	@Override
	public void run() {
		Socket socket = null;
		try {
			socket = new Socket();     //socket ����
			System.out.println("[���� ��û]");      
			socket.connect(new InetSocketAddress("localhost", 5000));     //������ Ŭ���̾�Ʈ�� ���� ȣ��Ʈ�� ����Ǿ�� ��Ű���
			System.out.println("[���� ����]");

			byte[] bytes = null;
			String message = null;

			OutputStream os = socket.getOutputStream();       //OutputStream : ���� server�� ����
			message = "Hello Server"; 
			bytes = message.getBytes("UTF-8");     //���� ����(���ڼ�) UTF-8 ���
			os.write(bytes);           //write�� message ����
			os.flush();                //flush�� ū �����͸� ���� �� ��� (���ۿ� �����ִ� �͵� �� ������)
			System.out.println("[������ ������ ����]"); 

			InputStream is = socket.getInputStream();      //server���� ���� InputStream ����
			bytes = new byte[100];
			int readByteCount = is.read(bytes);      // �����·� �� (������� ������ �� ����)
			message = new String(bytes, 0, readByteCount, "UTF-8");  // ���� ������ string Ÿ������ ��ȯ�ؼ� message�� ����
			System.out.println("[������ �ޱ� ����]: " + message);

			os.close(); // ����ϴ� os ����
			is.close(); // �Է��ϴ� is ����
		} catch (Exception e) {      //���� ���� �߻� ��
			System.out.println("������ �����Ǿ����ϴ�");
		}

		if (!socket.isClosed()) {    //socket�� �����ִٸ�
			try {
				socket.close();      //socket �ݱ�
			} catch (IOException e1) {
			}
		}
		System.out.println("Ŭ���̾�Ʈ�� ����Ǿ����ϴ�");   //Thread ����
	}

}
