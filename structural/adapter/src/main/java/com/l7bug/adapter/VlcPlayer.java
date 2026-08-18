package com.l7bug.adapter;

/**
 * 具体适配者 - VLC 播放器
 */
public class VlcPlayer implements AdvancedMediaPlayer {

    @Override
    public void playVlc(String fileName) {
        System.out.println("播放 VLC 视频: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("VLC 不支持 MP4: " + fileName);
    }
}
