package utils;

import java.io.IOException;
import java.io.OutputStream;

public class MultiOutputStream extends OutputStream {

    OutputStream stream1;
    OutputStream stream2;

    public MultiOutputStream(OutputStream stream1, OutputStream stream2) {
        this.stream1 = stream1;
        this.stream2 = stream2;
    }

    @Override
    public void write(int b) throws IOException {
        stream1.write(b);
        stream2.write(b);
    }
}
