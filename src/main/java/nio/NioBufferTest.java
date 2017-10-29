package nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by dongliang on 17/10/15.
 */
public class NioBufferTest {
    public static void main(String[] args) throws Exception {
        RandomAccessFile aFile = new RandomAccessFile("/Users/dongliang/lock.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf);

        while (bytesRead != -1) {

            buf.flip();

            while (buf.hasRemaining()) {

                System.out.println((char) buf.get());

            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }

        aFile.close();

    }
}
