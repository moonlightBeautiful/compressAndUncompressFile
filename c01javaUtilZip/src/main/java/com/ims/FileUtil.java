package com.ims;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileUtil {

    /**
     * @param srcFilePath     文件路径
     * @param destZipFilePath
     * @throws IOException
     */
    public static void zipFile(String srcFilePath, String destZipFilePath) throws IOException {
        System.out.println("系统默认编码:" + Charset.defaultCharset());
        File srcFile = new File(srcFilePath);
        File destZipFile = new File(destZipFilePath);
        InputStream inputStream = new FileInputStream(srcFile);
        ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(destZipFile));
        outputStream.putNextEntry(new ZipEntry(srcFile.getName()));
        int temp = 0;
        while ((temp = inputStream.read()) != -1) {
            outputStream.write(temp);
        }
        inputStream.close();
        outputStream.close();
    }

    /**
     * @param srcFilePath     文件夹路径，里面放文件，不包含文件夹
     * @param destZipFilePath
     * @throws IOException
     */
    public static void zipMultiFile(String srcFilePath, String destZipFilePath) throws IOException {
        File srcFile = new File(srcFilePath);
        File destZipFile = new File(destZipFilePath);
        InputStream inputStream = null;
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(destZipFile),Charset.forName("GBK"));
        if (srcFile.isDirectory()) {
            File[] files = srcFile.listFiles();
            for (int i = 0; i < files.length; i++) {
                inputStream = new FileInputStream(files[i]);
                zipOutputStream.putNextEntry(new ZipEntry(files[i].getName()));
                int temp = 0;
                while ((temp = inputStream.read()) != -1) {
                    zipOutputStream.write(temp);
                }
                inputStream.close();
            }
        }
        zipOutputStream.close();
    }

    /**
     * 解压单个
     *
     * @param ZipFilePath       需要解压的zip文件
     * @param destUnZipFilePath 解压后的路径和文件名
     * @param fileNameInZipFile zip中需要解压的文件名
     * @throws IOException
     */
    public static void unZipFile(String ZipFilePath, String destUnZipFilePath, String fileNameInZipFile) throws IOException {
        File file = new File(ZipFilePath);
        ZipFile zipFile = new ZipFile(file);
        File destUnZipFile = new File(destUnZipFilePath);
        ZipEntry zipEntry = zipFile.getEntry(fileNameInZipFile);
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        OutputStream outputStream = new FileOutputStream(destUnZipFile);
        int temp = 0;
        while ((temp = inputStream.read()) != -1) {
            outputStream.write(temp);
        }
        inputStream.close();
        outputStream.close();
    }

    /**
     * 解压多个文件
     *
     * @param ZipFilePath       需要解压的压缩文件zip
     * @param destUnZipFilePath 解压路劲
     * @throws IOException
     */
    public static void unZipMultiFile(String ZipFilePath, String destUnZipFilePath) throws IOException {
        File file = new File(ZipFilePath);
        ZipFile zipFile = new ZipFile(file);
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file));

        File outFile = null;
        ZipEntry zipEntry = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            System.out.println("解压缩" + zipEntry.getName() + "文件");
            outFile = new File(destUnZipFilePath + File.separator + zipEntry.getName());
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            if (!outFile.exists()) {
                outFile.createNewFile();
            }
            inputStream = zipFile.getInputStream(zipEntry);
            outputStream = new FileOutputStream(outFile);
            int temp = 0;
            while ((temp = inputStream.read()) != -1) {
                outputStream.write(temp);
            }
            inputStream.close();
            outputStream.close();
        }

    }

}
