package com.l7bug.adapter;

import lombok.extern.slf4j.Slf4j;

/**
 * 适配器模式演示
 *
 * 场景：媒体播放器
 * - AudioPlayer 只支持 mp3
 * - VlcPlayer 和 Mp4Player 接口不兼容 MediaPlayer
 * - MediaAdapter 将 AdvancedMediaPlayer 适配为 MediaPlayer
 * - AudioPlayerAdapter 通过适配器支持更多格式
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        log.info("=== 适配器模式演示 ===\n");

        // 1. 基础播放器 - 只支持 mp3
        log.info("1. 基础播放器");
        MediaPlayer basicPlayer = new AudioPlayer();
        basicPlayer.play("mp3", "周杰伦-稻香.mp3");
        basicPlayer.play("mp4", "周杰伦-稻香.mp4");

        // 2. 通过适配器直接使用
        log.info("\n2. 通过适配器播放");
        MediaPlayer vlcAdapter = new MediaAdapter("vlc");
        vlcAdapter.play("vlc", "周杰伦-稻香.vlc");

        MediaPlayer mp4Adapter = new MediaAdapter("mp4");
        mp4Adapter.play("mp4", "周杰伦-稻香.mp4");

        // 3. 增强版播放器 - 通过适配器支持多格式
        log.info("\n3. 增强版播放器（适配器模式）");
        AudioPlayerAdapter enhancedPlayer = new AudioPlayerAdapter();
        enhancedPlayer.play("mp3", "周杰伦-稻香.mp3");
        enhancedPlayer.play("vlc", "周杰伦-稻香.vlc");
        enhancedPlayer.play("mp4", "周杰伦-稻香.mp4");
        enhancedPlayer.play("avi", "周杰伦-稻香.avi");

        log.info("\n=== 适配器模式演示完成 ===");
    }
}
