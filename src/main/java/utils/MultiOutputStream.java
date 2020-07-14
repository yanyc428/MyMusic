package utils;

import java.io.IOException;
import java.io.OutputStream;

public class MultiOutputStream extends OutputStream {

    OutputStream stream1;
    OutputStream stream2;

    /**
     * 传入两个文件输出流
     * @param stream1 第一个流
     * @param stream2 第二个流
     */
    public MultiOutputStream(OutputStream stream1, OutputStream stream2) {
        this.stream1 = stream1;
        this.stream2 = stream2;
    }

    /**
     * 重写方法 将输出分别输出到两个流
     * @param b 输出文字
     * @throws IOException
     */
    @Override
    public void write(int b) throws IOException {
        stream1.write(b);
        stream2.write(b);
    }
}
