package kcci.socket;

public class ServerKshExample {
	public static void main(String[] args) {
		ServerKshThread serverKshThread =  new ServerKshThread(); //Thread���Ͽ� �����ڰ� �����Ƿ� default ������ �ڵ� ����
		serverKshThread.start();
	}
}