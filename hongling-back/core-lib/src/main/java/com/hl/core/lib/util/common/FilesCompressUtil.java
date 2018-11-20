package com.hl.core.lib.util.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Describe:
 * @Package: com.hl.mobile
 * @Author: liyu
 * @Date: 2018/1/9 0009 下午 17:43
 * @Copyright: hl
 */

public class FilesCompressUtil {


    private int count;
    private int index;
    private CompressCallBackListener listener;
    private static FilesCompressUtil filesCompressTool;
    public static FilesCompressUtil Build(CompressCallBackListener listener){
        synchronized (FilesCompressUtil.class){
            if(filesCompressTool==null){
                filesCompressTool= new FilesCompressUtil(listener);
            }
        }
        return filesCompressTool;
    }


    private FilesCompressUtil(CompressCallBackListener listener){
        this.listener=listener;
    }
    private FilesCompressUtil(){}

    /***
     *
     * @param zipDir 生成压缩包的目录
     * @param zipName 生成压缩包的名称
     * @param files  要压缩的所有文件
     * @throws IOException
     */
    public File compressFiles(String zipDir,String zipName,
                               List<File> files) throws IOException {
        ZipEntry zipEntry;
        count = files.size();

        if(zipDir==null||"".equals(zipDir))
            throw new IOException("没有传递要生成的路径");
        if(zipName==null||"".equals(zipName))
            throw new IOException("没有传递要生成的文件名称");


        File dirFile = new File(zipDir);
        if (!dirFile.exists())
            dirFile.mkdirs();

        String zipFilePath=dirFile+File.separator+zipName;

        File zipFile = new File(zipFilePath);
        if (zipFile.exists()) {
            zipFile.delete();
        }
        // 1.创建文件
        zipFile.createNewFile();
        ZipOutputStream outZip = null;

        index = 0;
        try {
            outZip = new ZipOutputStream(new FileOutputStream(zipFile));

            // 2.打包文件信息
            for (int i = 0; i < files.size(); i++) {
                int compassed = 0;// 已经压缩
                index++;
                File file = files.get(i);
                int compressTotal = (int) file.length(); // 文件总大小

                // 判断是不是文件
                if (file.isFile() && file.exists()) {
                    zipEntry = new ZipEntry(file.getName());
                    FileInputStream inputStream = new FileInputStream(file);
                    outZip.putNextEntry(zipEntry);

                    int len = -1;
                    byte[] buffer = new byte[4096];

                    while ((len = inputStream.read(buffer)) != -1
                               ) {
                        int end = len;
                        if ((compressTotal - compassed) < len) {
                            end = compressTotal - compassed;
                        }
                        outZip.write(buffer, 0, len);
                        compassed += end;// 累加已经上传的数据长度
                        if(listener!=null){
                            listener.compress(compassed,compressTotal);
                        }
                    }

                    outZip.closeEntry();
                }

            }
        } finally {
            if (outZip != null) {
                try {
                    outZip.close();
                } catch (IOException ex) {

                }
            }
        }

        return zipFile;

    }
}
