package utils;

import java.io.*;

public class Logger {

    private String fileName;

    /**
     *
     * @param fileName 打开流的文件名
     */
    public Logger(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 得到文件输出流
     * @return 流
     */
    public PrintStream getStream() {
        String pwd = System.getProperty("user.dir") + "/log";
        File dir = new File(pwd);

        /**
         * 不存在log文件夹，则新建
         */
        if
        (!dir.exists()){
            dir.mkdir();
        }

        File file = new File(pwd + "/" + this.fileName);

        /**
         * 不存在传入的文件 新建
         */
        try {
            if (!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            PrintStream out = new PrintStream(fileOutputStream);
            return out;
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }

}
