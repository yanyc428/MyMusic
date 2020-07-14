package utils;

import java.io.PrintStream;

public class Log {

    public static String fileName = "devlog.txt";
    public static String exception = "deverror.txt";
    private static PrintStream exceptionHandler = new Logger(exception).getStream();;

    static {
        PrintStream out = new Logger(fileName).getStream();
        MultiOutputStream outputStream = new MultiOutputStream(out, System.out);
        System.setOut(new PrintStream(outputStream));
    }

    public static void log(String string){
        System.out.println(DateUtil.now() + Thread.currentThread().toString() + " " + string);
    }

    public static void error(String string){
        exceptionHandler.println(DateUtil.now() + Thread.currentThread().toString() + " " + string);
    }
}
