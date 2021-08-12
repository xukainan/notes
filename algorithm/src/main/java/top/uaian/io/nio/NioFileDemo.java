package top.uaian.io.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioFileDemo {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream(new File("C:\\test\\test.txt"));
        FileChannel channel = inputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true){
            buffer.clear();
            int read = channel.read(buffer);
            if (read == -1){
                break;
            }
            buffer.flip(); //Buffer有两种模式，写模式和读模式。在写模式下调用flip()之后，Buffer从写模式变成读模式。
            System.out.println(new String(buffer.array()));
        }
        channel.close();
    }
}
