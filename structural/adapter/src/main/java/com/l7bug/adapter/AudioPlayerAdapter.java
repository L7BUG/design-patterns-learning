package com.l7bug.adapter;

/**
 * 增强版音频播放器
 *
 * 继承 AudioPlayer（支持 mp3），
 * 通过 MediaAdapter 适配其他格式（vlc、mp4）
 */
public class AudioPlayerAdapter extends AudioPlayer {

    private MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        // mp3 直接播放
        if (audioType.equalsIgnoreCase("mp3")) {
            super.play(audioType, fileName);
        }
        // vlc 和 mp4 通过适配器播放
        else if (audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        }
        // 其他格式不支持
        else {
            System.out.println("不支持的格式: " + audioType + "，文件名: " + fileName);
        }
    }
}
