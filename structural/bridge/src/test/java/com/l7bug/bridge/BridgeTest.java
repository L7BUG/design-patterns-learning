package com.l7bug.bridge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 桥接模式单元测试
 */
class BridgeTest {

    @Test
    void tvTogglesPower() {
        Device tv = new Tv();
        assertFalse(tv.isEnabled());

        RemoteControl remote = new BasicRemoteControl(tv);
        remote.togglePower();
        assertTrue(tv.isEnabled());

        remote.togglePower();
        assertFalse(tv.isEnabled());
    }

    @Test
    void radioVolumeUpAndDown() {
        Device radio = new Radio();
        RemoteControl remote = new BasicRemoteControl(radio);

        remote.volumeUp();
        assertEquals(10, radio.getVolume());

        remote.volumeUp();
        assertEquals(20, radio.getVolume());

        remote.volumeDown();
        assertEquals(10, radio.getVolume());
    }

    @Test
    void advancedRemoteMutesTv() {
        Device tv = new Tv();
        AdvancedRemoteControl remote = new AdvancedRemoteControl(tv);

        remote.togglePower();
        remote.volumeUp();
        remote.volumeUp();
        remote.volumeUp();
        assertEquals(30, tv.getVolume());

        remote.mute();
        assertEquals(0, tv.getVolume());
    }

    @Test
    void tvAndRadioDoNotInterfere() {
        Device tv = new Tv();
        Device radio = new Radio();
        RemoteControl tvRemote = new BasicRemoteControl(tv);
        RemoteControl radioRemote = new BasicRemoteControl(radio);

        tvRemote.togglePower();
        tvRemote.volumeUp();
        tvRemote.volumeUp();

        radioRemote.togglePower();
        radioRemote.volumeUp();
        radioRemote.volumeUp();
        radioRemote.volumeUp();

        // 电视状态
        assertTrue(tv.isEnabled());
        assertEquals(20, tv.getVolume());

        // 收音机状态独立
        assertTrue(radio.isEnabled());
        assertEquals(30, radio.getVolume());
    }

    @Test
    void remoteWorksWithBothDevices() {
        Device tv = new Tv();
        Device radio = new Radio();

        // 同一个遥控器（基础遥控器）控制不同设备
        RemoteControl remote1 = new BasicRemoteControl(tv);
        remote1.togglePower();
        remote1.volumeUp();
        assertTrue(tv.isEnabled());
        assertEquals(10, tv.getVolume());

        RemoteControl remote2 = new BasicRemoteControl(radio);
        remote2.togglePower();
        remote2.volumeUp();
        assertTrue(radio.isEnabled());
        assertEquals(10, radio.getVolume());
    }

    @Test
    void volumeClampedToRange() {
        Device tv = new Tv();
        RemoteControl remote = new BasicRemoteControl(tv);

        // 音量不能低于 0
        remote.volumeDown();
        assertEquals(0, tv.getVolume());

        remote.volumeDown();
        assertEquals(0, tv.getVolume());

        // 音量不能超过 100
        for (int i = 0; i < 12; i++) {
            remote.volumeUp();
        }
        assertEquals(100, tv.getVolume());
    }
}
