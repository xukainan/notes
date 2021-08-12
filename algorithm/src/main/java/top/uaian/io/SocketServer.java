package top.uaian.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            while (true){
                Socket accept = serverSocket.accept(); //用于从指定套接字的连接队列中取出第一个连接，并返回一个新的套接字用于与客户端进行通信
                executorService.execute(() -> {
                    try {
                        InputStream inputStream = accept.getInputStream();
                        byte[] bytes = new byte[1024];
                        int read = 0;
                        Thread.sleep(10000);
                        while ((read = inputStream.read(bytes)) != -1) {
                            System.out.println(new String(bytes,0,read));
                        }
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            accept.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
