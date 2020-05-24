package com.ims;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
       /* FileUtil.zipFile("F:\\zipTest\\test01.txt", "F:\\zipTest\\test01.zip");*/
        /* FileUtil.zipMultiFile("F:\\zipTest", "F:\\test01.zip");*/
        /*FileUtil.unZipFile("F:\\zipTest\\test01.zip","F:\\zipTest.txt","test01.txt");*/
        FileUtil.unZipMultiFile("F:\\zipTest\\zipTest.zip","F:");
       /* System.out.println("系统默认编码:" + Charset.defaultCharset());*/
    }
}
