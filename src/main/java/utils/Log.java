package utils;

import java.io.PrintStream;

public class Log {

    private static String fileName = "devlog.txt";
    private static PrintStream exceptionHandler = new Logger("deverror.txt").getStream();;

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
