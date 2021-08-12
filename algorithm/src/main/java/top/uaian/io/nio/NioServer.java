package top.uaian.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class NioServer {

    static List<SocketChannel> clients  = new LinkedList<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("nio server starting...");
        server(8081);
    }

    private static void server(int port) throws IOException, InterruptedException {
        /**
         * 前三步
         * socket = xxx
         * bind(xxx,8081)
         * listen(xxx)
         */
        ServerSocketChannel ss = ServerSocketChannel.open();
        ss.bind(new InetSocketAddress(port));

        /**
         * fcntl(xxx, O_NONBLOCK) //设置非阻塞
         */
        ss.configureBlocking(false); //设置服务器为不阻塞

        while (true){
            Thread.sleep(1000);
            SocketChannel client = ss.accept(); //设置客户端为不阻塞


            if(client == null){
                System.out.println(getTime() + " no client found");
            }else {
                client.configureBlocking(false);
                clients.add(client);
                System.out.println(String.format(getTime() +" client %s has added!", client.socket().getInetAddress() + ":" + client.socket().getPort()));
            }

            /**
             * 这个buffer，一般一个client配一个buffer
             * 但是此处是所有的client是串行化的，所以所有client共用一个buffer
             */
            //堆外，效率更高，效率接近于Native IO，也就是所谓的0拷贝
            ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
            //堆内，数据需要从内核拷贝到堆内，有一次拷贝的过程
            //ByteBuffer buffer = ByteBuffer.allocate(1024);
            System.out.print(getTime() +  String.format(" 此时共有 %s 个客户端：", clients.size()));

            for (SocketChannel curClient : clients){
                String cInfo = curClient.socket().getInetAddress() + ":" + curClient.socket().getPort();
                int num = 0;
                /*读取异常处理*/
                try{
                    //读取发生异常直接关闭socket，因为如果客户端强制关闭服务器端还在读取的话会造成异常：
                    //" 远程主机强迫关闭了一个现有的连接 "
                    num = curClient.read(buffer); // >0 -1 0 不会阻塞
                } catch (Exception e){
                    e.printStackTrace();
                    curClient.socket().close();
                    curClient.close();
                    clients.remove(curClient);
                    continue;
                }
                if (num > 0){
                    buffer.flip();
                    byte[] bytes = new byte[buffer.limit()];
                    buffer.get(bytes);
                    String s = new String(bytes);
                    System.out.println("\n-----------------------------");
                    System.out.print(getTime() + " " + cInfo  + " -> " + s);
                    System.out.println("-----------------------------");
                    buffer.clear();
                } else if (num == -1){
                    System.out.println(cInfo+"强制断开连接，连接关闭！");
                    curClient.socket().close();
                    curClient.close();
                    clients.remove(curClient);
                    continue;
                }
            }
            System.out.println();

        }

    }

    public static String getTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
