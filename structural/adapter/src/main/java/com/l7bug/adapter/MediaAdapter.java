package com.l7bug.adapter;

/**
 * 媒体适配器 - 核心适配类
 *
 * 实现 MediaPlayer 接口，内部使用 AdvancedMediaPlayer
 * 将 MediaPlayer 的 play() 调用转换为 AdvancedMediaPlayer 的具体方法
 */
public class MediaAdapter implements MediaPlayer {

    private final AdvancedMediaPlayer advancedPlayer;

    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedPlayer = new Mp4Player();
        } else {
            throw new IllegalArgumentException("不支持的格式: " + audioType);
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedPlayer.playMp4(fileName);
        }
    }
}
