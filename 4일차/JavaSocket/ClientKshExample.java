package kcci.socket;

public class ClientKshExample {
	public static void main(String[] args) {
		ClientKshThread clientKshThread = new ClientKshThread();    //thread��ü ����
		clientKshThread.start();		//Thread�� run()�Լ� ����
	}
}