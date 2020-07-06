package utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Logger {

    private String fileName;

    public Logger(String fileName) {
        this.fileName = fileName;
    }

    public PrintStream getStream() throws FileNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/log/" + fileName, true);
        PrintStream out = new PrintStream(fileOutputStream);
        return out;
    }

}
