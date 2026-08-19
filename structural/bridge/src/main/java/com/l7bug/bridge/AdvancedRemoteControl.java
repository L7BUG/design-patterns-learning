package com.l7bug.bridge;

/**
 * 高级遥控器（细化抽象部分 - RefinedAbstraction）
 *
 * <p>继承 RemoteControl，扩展了静音（mute）功能。
 * 展示了抽象部分可以独立于实现部分进行扩展。</p>
 */
public class AdvancedRemoteControl extends RemoteControl {

    /**
     * 构造函数，注入设备实例
     *
     * @param device 要控制的设备
     */
    public AdvancedRemoteControl(Device device) {
        super(device);
    }

    /**
     * 静音：将音量设为 0
     */
    public void mute() {
        device.setVolume(0);
    }
}
