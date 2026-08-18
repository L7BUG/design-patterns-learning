package com.l7bug.adapter;

/**
 * 先进媒体播放器接口 - 适配者（Adaptee）
 *
 * 提供更高级的播放功能，但接口与 MediaPlayer 不兼容
 */
public interface AdvancedMediaPlayer {

    /**
     * 播放 VLC 格式
     * @param fileName 文件名
     */
    void playVlc(String fileName);

    /**
     * 播放 MP4 格式
     * @param fileName 文件名
     */
    void playMp4(String fileName);
}
