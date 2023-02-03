package kcci.socket;

public class ClientKshExample {
	public static void main(String[] args) {
		ClientKshThread clientKshThread = new ClientKshThread();    //thread객체 생성
		clientKshThread.start();		//Thread의 run()함수 실행
	}
}