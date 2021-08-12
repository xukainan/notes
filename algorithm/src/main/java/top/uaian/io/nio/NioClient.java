package top.uaian.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClient {
    public static void main(String[] args) {
        try (SocketChannel socketChannel = SocketChannel.open()){
            //连接服务端socket
            SocketAddress address = new InetSocketAddress("localhost", 8081);
            socketChannel.connect(address);

            int sendCount = 0;
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

            while (sendCount < 10) {
                buffer.clear();
                //向服务端发送消息
                buffer.put(("current time : " + System.currentTimeMillis()).getBytes());
                //读取模式
                buffer.flip();
                socketChannel.write(buffer);
                buffer.clear();

                //从服务端读取消息
                int readLenth = socketChannel.read(buffer);
                //读取模式
                buffer.flip();
                byte[] bytes = new byte[readLenth];
                buffer.get(bytes);
                System.out.println(new String(bytes, "UTF-8"));
                buffer.clear();


                sendCount++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
