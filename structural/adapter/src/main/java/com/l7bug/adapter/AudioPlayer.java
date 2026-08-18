package com.l7bug.adapter;

/**
 * 抽象辅助类 - 基础媒体播放器
 *
 * 只支持播放 mp3 格式
 */
public class AudioPlayer implements MediaPlayer {

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("播放 MP3: " + fileName);
        } else {
            System.out.println("不支持的格式: " + audioType + "，文件名: " + fileName);
        }
    }
}
