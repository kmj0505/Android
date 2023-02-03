package kcci.socket;

public class ServerKshExample {
	public static void main(String[] args) {
		ServerKshThread serverKshThread =  new ServerKshThread(); //Thread파일에 생성자가 없으므로 default 생성자 자동 생성
		serverKshThread.start();
	}
}