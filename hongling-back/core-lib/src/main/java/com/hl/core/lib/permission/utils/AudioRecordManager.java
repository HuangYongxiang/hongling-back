//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hl.core.lib.permission.utils;

import android.media.AudioRecord;
import android.os.Process;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Thread.State;

/**
 * @Describe:  录音管理器
 * @Package: com.hl.core.lib.permission.utils;
 * @Author: liyu
 * @Date: 2018/1/4 14:42
 * @Copyright: hl
 */
public class AudioRecordManager {
    public File file;
    private AudioRecord mRecorder;
    private DataOutputStream dos;
    private Thread recordThread;
    private boolean isStart = false;
    private int bufferSize = AudioRecord.getMinBufferSize(8000, 16, 2);
    Runnable recordRunnable = new Runnable() {
        public void run() {
            try {
                Process.setThreadPriority(-19);
                byte[] tempBuffer = new byte[AudioRecordManager.this.bufferSize];
                AudioRecordManager.this.mRecorder.startRecording();

                while(AudioRecordManager.this.isStart) {
                    if(AudioRecordManager.this.mRecorder != null) {
                        int bytesRecord = AudioRecordManager.this.mRecorder.read(tempBuffer, 0, AudioRecordManager.this.bufferSize);
                        if(bytesRecord != -3 && bytesRecord != -2) {
                            if(bytesRecord == 0 || bytesRecord == -1) {
                                break;
                            }

                            AudioRecordManager.this.dos.write(tempBuffer, 0, bytesRecord);
                        }
                    }
                }
            } catch (Exception var3) {
                var3.printStackTrace();
            }

        }
    };
    private long length;

    public AudioRecordManager() {
        this.mRecorder = new AudioRecord(1, 8000, 16, 2, this.bufferSize * 2);
    }

    public boolean getSuccess() {
        return this.length > 0L;
    }

    private void destroyThread() {
        try {
            this.isStart = false;
            if(this.recordThread != null && this.recordThread.getState() != State.TERMINATED) {
                try {
                    this.recordThread.interrupt();
                } catch (Exception var6) {
                    var6.printStackTrace();
                    this.recordThread = null;
                }
            }

            this.recordThread = null;
        } catch (Exception var7) {
            var7.printStackTrace();
        } finally {
            this.recordThread = null;
        }

    }

    private void startThread() {
        this.isStart = true;
        if(this.recordThread == null) {
            this.recordThread = new Thread(this.recordRunnable);
            this.recordThread.start();
        }

    }

    private void setPath(String path) throws IOException {
        this.file = new File(path);
        this.deleteFile();
        this.file.createNewFile();
        this.dos = new DataOutputStream(new FileOutputStream(this.file, true));
    }

    public void startRecord(String path) throws IOException, InterruptedException {
        this.setPath(path);
        this.startThread();
    }

    public void stopRecord() throws IOException, InterruptedException {
        Thread.sleep(250L);
        this.destroyThread();
        if(this.mRecorder != null) {
            if(this.mRecorder.getState() == 1) {
                this.mRecorder.stop();
            }

            if(this.mRecorder != null) {
                this.mRecorder.release();
            }
        }

        if(this.dos != null) {
            this.dos.flush();
            this.dos.close();
        }

        this.length = this.file.length();
        this.deleteFile();
    }

    private void deleteFile() {
        if(this.file.exists()) {
            this.file.delete();
        }

    }
}
