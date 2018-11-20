package com.hl.core.lib.network.upload;

import java.util.List;

/**
 * @Describe:获得长度的请求DTO
 * @Package: com.hl.core.lib.network.upload
 * @Author: liyu
 * @Date: 2018/1/12 0012 下午 17:58
 * @Copyright: hl
 */
public class UploadRequestDTO {
    private List<UploadData> fileDataList;

    public List<UploadData> getFileDataList() {
        return fileDataList;
    }

    public void setFileDataList(List<UploadData> fileDataList) {
        this.fileDataList = fileDataList;
    }
}
