package com.l7bug.adapter;

/**
 * 具体适配者 - MP4 播放器
 */
public class Mp4Player implements AdvancedMediaPlayer {

    @Override
    public void playVlc(String fileName) {
        System.out.println("MP4 播放器不支持 VLC: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("播放 MP4 视频: " + fileName);
    }
}
