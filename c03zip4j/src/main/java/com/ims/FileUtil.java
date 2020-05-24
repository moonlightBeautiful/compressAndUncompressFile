package com.ims;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import sun.rmi.runtime.Log;

import java.io.File;
import java.io.IOException;

public class FileUtil {
    /**
     * zip4j压缩
     *
     * @param filePath    要压缩的文件路径（可文件，可目录）
     * @param zipFilePath zip生成的文件路径  。。。。.zip
     * @param password    密码
     */
    public static void zipFile(String filePath, String zipFilePath, String password) throws ZipException {
        File sourceFile = new File(filePath);
        ZipFile zipFile = new ZipFile(new File(zipFilePath));
        zipFile.setFileNameCharset("GBK"); //设置编码格式（支持中文）

        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); //压缩方式
        zipParameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL); // 压缩级别
        if (password != null && password != "") {   //是否要加密(加密会影响压缩速度)
            zipParameters.setEncryptFiles(true);
            zipParameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD); // 加密方式
            zipParameters.setPassword(password.toCharArray());
        }
        //压缩文件
        if (sourceFile.isDirectory()) {
            zipFile.addFolder(sourceFile, zipParameters);
        } else {
            zipFile.addFile(sourceFile, zipParameters);
        }
    }


    /**
     * zip4j解压
     *
     * @param zipFilePath 待解压的zip文件（目录）路径
     * @param filePath    解压到的保存路径
     * @param password    密码
     * @return 状态返回值
     */
    public static void unzipFile(String zipFilePath, String filePath, String password) throws ZipException {
        File sourceFile = new File(filePath);
        ZipFile zipFile = new ZipFile(new File(zipFilePath));
        zipFile.setFileNameCharset("GBK");  //设置编码格式（支持中文）
        if (!zipFile.isValidZipFile()) {     //检查输入的zip文件是否是有效的zip文件
            throw new ZipException("压缩文件不合法,可能被损坏.");
        }
        if (sourceFile.isDirectory() && !sourceFile.exists()) {
            sourceFile.mkdir();
        }
        if (zipFile.isEncrypted()) {
            zipFile.setPassword(password.toCharArray());
        }
        zipFile.extractAll(filePath); //解压
    }

}
