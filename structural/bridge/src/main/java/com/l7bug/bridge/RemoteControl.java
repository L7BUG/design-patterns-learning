package com.l7bug.bridge;

/**
 * 遥控器抽象类（抽象部分 - Abstraction）
 *
 * <p>持有 Device 引用（桥接），通过构造函数注入。
 * 定义遥控器的基本操作：切换电源、调节音量。
 * 具体遥控器（如 AdvancedRemoteControl）可以扩展更多功能。</p>
 */
public abstract class RemoteControl {

    protected final Device device;

    /**
     * 构造函数，注入设备实例（桥接）
     *
     * @param device 要控制的设备
     */
    protected RemoteControl(Device device) {
        this.device = device;
    }

    /**
     * 切换电源开关
     */
    public void togglePower() {
        if (device.isEnabled()) {
            device.disable();
        } else {
            device.enable();
        }
    }

    /**
     * 音量增加
     */
    public void volumeUp() {
        device.setVolume(device.getVolume() + 10);
    }

    /**
     * 音量降低
     */
    public void volumeDown() {
        device.setVolume(device.getVolume() - 10);
    }
}
