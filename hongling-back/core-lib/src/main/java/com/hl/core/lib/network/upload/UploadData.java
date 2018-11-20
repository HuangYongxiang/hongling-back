package com.hl.core.lib.network.upload;

/**
 * @Describe:用于请求文件长度的数据模型
 * @Package: com.hl.core.lib.network.upload
 * @Author: liyu
 * @Date: 2018/1/12 0012 下午 17:58
 * @Copyright: hl
 */

public class UploadData {
    private String md5FileName;
    private String fileTotalSize;
    private String alreadyUpladeLength;

    public String getMd5FileName() {
        return md5FileName;
    }

    public void setMd5FileName(String md5FileName) {
        this.md5FileName = md5FileName;
    }

    public String getFileTotalSize() {
        return fileTotalSize;
    }

    public void setFileTotalSize(String fileTotalSize) {
        this.fileTotalSize = fileTotalSize;
    }

    public String getAlreadyUpladeLength() {
        return alreadyUpladeLength;
    }

    public void setAlreadyUpladeLength(String alreadyUpladeLength) {
        this.alreadyUpladeLength = alreadyUpladeLength;
    }
}
