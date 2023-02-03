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
			serverSocket.bind(new InetSocketAddress("localhost", 5000));    //네트워크 프로그램은 사용할(호스트, 호스트 주소)가 필요함 
			while (true) {   //listen이 감춰짐(대기 큐 : 한 개 연결해서 끊어지면 다음 것이 연결된다)
				System.out.println("[연결 기다림]");     
				Socket socket = serverSocket.accept();   //accept에 의해서 클라이언트가 접속을 할 때까지 잠든다(호스트, 호스트 주소로 연결될 경우 풀림)
				InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();  //연결된 client의 socket 주소를 return
				System.out.println("[연결 수락함] " + isa.getHostName());     //[연결 수락함] + client 주소

				byte[] bytes = null;  //byte 배열
				String message = null;

				InputStream is = socket.getInputStream();      //InputStream 객체 생성
				bytes = new byte[100];
				int readByteCount = is.read(bytes);       //블로킹 함수 read 생성해서 멈춤
				message = new String(bytes, 0, readByteCount, "UTF-8");    //Client에서 message를 보내서 깨어남  (읽은 데이터 전체를 string타입으로 변환해서 message에 저장)
				System.out.println("[데이터 받기 성공]: " + message);  

				OutputStream os = socket.getOutputStream();    // 전송할 데이터를 보내기 위해 OutputStream 생성
				message = "Hello Client";        //보낼메세지 생성
				bytes = message.getBytes("UTF-8");
				os.write(bytes);     //메세지 byte로 변환해서 보냄
				os.flush();
				System.out.println("[데이터 보내기 성공]");

				is.close();     //입력하는 객체 is 닫음
				os.close();     //입력하는 객체 os 닫음
				socket.close();  // 서버 닫음   -> while문이므로 계속해서 돌아감
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
