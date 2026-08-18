package com.l7bug.adapter;

/**
 * 目标接口 - 媒体播放器
 *
 * 客户端期望的接口
 */
public interface MediaPlayer {

    /**
     * 播放媒体
     * @param audioType 媒体类型
     * @param fileName  文件名
     */
    void play(String audioType, String fileName);
}
