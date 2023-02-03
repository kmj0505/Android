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
			socket = new Socket();     //socket 생성
			System.out.println("[연결 요청]");      
			socket.connect(new InetSocketAddress("localhost", 5000));     //서버와 클라이언트가 같은 호스트로 연결되어야 통신가능
			System.out.println("[연결 성공]");

			byte[] bytes = null;
			String message = null;

			OutputStream os = socket.getOutputStream();       //OutputStream : 먼저 server에 보냄
			message = "Hello Server"; 
			bytes = message.getBytes("UTF-8");     //문자 포맷(문자셋) UTF-8 사용
			os.write(bytes);           //write로 message 전송
			os.flush();                //flush는 큰 데이터를 보낼 때 사용 (버퍼에 남아있는 것도 다 내보냄)
			System.out.println("[데이터 보내기 성공]"); 

			InputStream is = socket.getInputStream();      //server에서 받을 InputStream 생성
			bytes = new byte[100];
			int readByteCount = is.read(bytes);      // 대기상태로 감 (깨어나야지 실행할 수 있음)
			message = new String(bytes, 0, readByteCount, "UTF-8");  // 받은 데이터 string 타입으로 변환해서 message에 저장
			System.out.println("[데이터 받기 성공]: " + message);

			os.close(); // 출력하는 os 닫음
			is.close(); // 입력하는 is 닫음
		} catch (Exception e) {      //예외 상태 발생 시
			System.out.println("서버가 중지되었습니다");
		}

		if (!socket.isClosed()) {    //socket이 열려있다면
			try {
				socket.close();      //socket 닫기
			} catch (IOException e1) {
			}
		}
		System.out.println("클라이언트가 종료되었습니다");   //Thread 종료
	}

}
