package com.hl.core.lib.network.download.db;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @Describe: 数据模型
 * @Package: com.hl.core.lib.network.download.db
 * @Author: liyu
 * @Date: 2018/1/29 0029 上午 9:41
 * @Copyright: hl
 */
public class DownLoadEntity implements Parcelable {
    public int dataId; //!!!!!!databaseId
    public String url;
    public long end;
    public long start;
    public long downed;
    public long total;
    public String saveName;
    public List<DownLoadEntity> multiList;
    public String lastModify;
    public boolean isSupportMulti;

    public DownLoadEntity() {
    }

    public DownLoadEntity(String url, String saveName) {
        this.url = url;
        this.saveName = saveName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.dataId);
        dest.writeString(this.url);
        dest.writeLong(this.end);
        dest.writeLong(this.start);
        dest.writeLong(this.downed);
        dest.writeLong(this.total);
        dest.writeString(this.saveName);
        dest.writeTypedList(multiList);
    }

    protected DownLoadEntity(Parcel in) {
        this.dataId = in.readInt();
        this.url = in.readString();
        this.end = in.readLong();
        this.start = in.readLong();
        this.downed = in.readLong();
        this.total = in.readLong();
        this.saveName = in.readString();
        this.multiList = in.createTypedArrayList(DownLoadEntity.CREATOR);
    }

    public static final Creator<DownLoadEntity> CREATOR = new Creator<DownLoadEntity>() {
        @Override
        public DownLoadEntity createFromParcel(Parcel source) {
            return new DownLoadEntity(source);
        }

        @Override
        public DownLoadEntity[] newArray(int size) {
            return new DownLoadEntity[size];
        }
    };
}
