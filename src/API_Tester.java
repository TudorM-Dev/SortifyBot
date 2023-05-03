import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;


public class API_Tester {
    public static void main(String[] args) {
        try (RandomAccessFile file = new RandomAccessFile("servoSliderValue.txt", "rw")) {
            FileChannel channel = file.getChannel();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);
            buffer.put("Hello".getBytes(StandardCharsets.UTF_8));
            channel.close();
            file.close();
        } catch (IOException e) {
//            e.printStackTrace();
        }
    }
}