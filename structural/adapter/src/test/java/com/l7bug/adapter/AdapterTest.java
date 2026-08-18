package com.l7bug.adapter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 适配器模式单元测试
 */
class AdapterTest {

    @Test
    void audioPlayerPlaysMp3() {
        MediaPlayer player = new AudioPlayer();
        player.play("mp3", "test.mp3");
    }

    @Test
    void mediaAdapterPlaysVlc() {
        MediaPlayer adapter = new MediaAdapter("vlc");
        adapter.play("vlc", "test.vlc");
    }

    @Test
    void mediaAdapterPlaysMp4() {
        MediaPlayer adapter = new MediaAdapter("mp4");
        adapter.play("mp4", "test.mp4");
    }

    @Test
    void mediaAdapterInvalidTypeThrows() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MediaAdapter("avi");
        });
    }

    @Test
    void enhancedPlayerSupportsAllFormats() {
        AudioPlayerAdapter player = new AudioPlayerAdapter();
        player.play("mp3", "test.mp3");
        player.play("vlc", "test.vlc");
        player.play("mp4", "test.mp4");
        player.play("avi", "test.avi");
    }

    @Test
    void adapterImplementsTargetInterface() {
        MediaPlayer adapter = new MediaAdapter("vlc");
        assertInstanceOf(MediaPlayer.class, adapter);
    }
}
