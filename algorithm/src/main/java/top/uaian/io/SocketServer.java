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
                Socket accept = serverSocket.accept();
                executorService.execute(() -> {
                    try {
                        InputStream inputStream = accept.getInputStream();
                        byte[] bytes = new byte[1024];
                        int read = 0;
                        while ((read = inputStream.read(bytes)) != -1) {
                            System.out.println(new String(bytes,0,read));
                        }
                    } catch (IOException e) {
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
