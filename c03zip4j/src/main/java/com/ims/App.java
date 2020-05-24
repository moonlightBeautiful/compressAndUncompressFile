package com.ims;

import net.lingala.zip4j.exception.ZipException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws ZipException {
      /* FileUtil.zipFile("F:\\zipTest","F:\\zipTest.zip","12345");*/
        FileUtil.unzipFile("F:\\zipTest.zip","F:\\zipTest\\zipTest","12345");
    }
}
