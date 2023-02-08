package com.kcci.socketclient;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientThread extends Thread{
    String serverIp;
    int serverPort = 5000;
    Socket socket = null;
    ClientThread(String strIp, int intPort){
        serverIp = strIp;  //MainActivity에서 저장한 strIp를 serverIp에 저장
        serverPort = intPort;  //MainActivity에서 저장한 strId를 serverId에 저장
    }
    @Override
    public void run() {       //MainActivity에서 clientThread.start()해서 run 실행
        try {
            socket = new Socket();   //socket 생성
            displayText("[연결 요청]");    //[연결 요청] 써짐
            Log.d("run()", "ip: " + serverIp + ", port: " + serverPort);
            socket.connect(new InetSocketAddress(serverIp, serverPort));  // socket을 인터넷 소켓주소인(serverIp, serverPort)에 연결
            displayText("[연결 성공]");    //[연결 성공] 써짐

            byte[] bytes = new byte[100];   //byte배열 byte[100] 선언
            String message = null;
            InputStream is = socket.getInputStream();    //외부에서 받은 데이터를 읽는 역할을 수행하는 스트림 is 선언

            while(true){
                int readByteCount = is.read(bytes);      //입력받은 바이트배열 읽음, 입력받지 못하면(블로킹 상태로 while문을 계속 돈다)
                if(readByteCount <= 0)
                {
                    break;
                }
                message = new String(bytes, 0, readByteCount, "UTF-8");   //입력 받은 데이터를 string으로 변환해서 message에 저장
                displayText("[데이터 받기 성공]: " + message);   //[데이터 받기 성공]: 받은 메세지
                sendMainActivity(message);             //아래의 sendMainActivity()로 간다
            }
            is.close();         //입력 중단
        } catch (Exception e) {
            displayText("서버가 중지되었습니다");
        }
        if (!socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e1) {
            }
        }
    }
    void stopClient(){
        if(socket != null && !socket.isClosed()){    //소켓이 null이거나 소켓이 닫히지 않으면

            displayText("클라이언트 중지");            //클라이언트 중지
            try {
                socket.close();                     //소켓 닫음
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    synchronized void sendData(String data) { // final data
        Thread sendThread = new Thread() {
            @Override
            public void run() {
                try {
                    byte[] bytes = data.getBytes("UTF-8");   //data를 byte배열로 받아서 저장
                    OutputStream os = socket.getOutputStream();      //출력 스트림 os 생성
                    os.write(bytes);           //입력한 strSend 데이터 출력
                    os.flush();                //출력하고 버퍼에 남은 데이터 출력
                    displayText("데이터 보내기 성공");    //데이터 보내기 성공
                } catch (Exception e) {
                    displayText("서버를 확인하세요");
                }
            }
        };
        sendThread.start();     //sendThread 시작 -> run함수 시작
    }
    synchronized void sendMainActivity(String text) {
        Message message = MainActivity.mainHandler.obtainMessage();  //호출의
        Bundle bundle = new Bundle();   //bundle 객체 생성
        bundle.putString("msg",text);   //"msg"에 text 문자열 넣기
        message.setData(bundle);      //
        MainActivity.mainHandler.sendMessage(message);
    }
    synchronized void displayText(String text) {
        Log.d("displayText", text);
    }
}