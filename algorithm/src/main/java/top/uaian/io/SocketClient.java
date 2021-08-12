package top.uaian.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {

    public static void main(String[] args) throws IOException {
        //发送到8888端口
        Socket socket=new Socket("127.0.0.1", 6666);
        //输出流
        OutputStream outputStream=socket.getOutputStream();
        PrintWriter printWriter=new PrintWriter(outputStream);
        printWriter.write("server端你好，我是client。");
        printWriter.flush();
        //关闭资源
        printWriter.close();
        outputStream.close();
        socket.close();
    }
}
