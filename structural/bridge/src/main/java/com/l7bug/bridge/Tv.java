package com.l7bug.bridge;

/**
 * 电视设备（具体实现部分 - ConcreteImplementor）
 *
 * <p>实现 Device 接口，维护电视特有的内部状态：
 * 开关状态、音量值。音量范围限制在 0-100。</p>
 */
public class Tv implements Device {

    private static final String NAME = "电视";
    private boolean enabled;
    private int volume;

    @Override
    public void enable() {
        enabled = true;
    }

    @Override
    public void disable() {
        enabled = false;
    }

    @Override
    public void setVolume(int volume) {
        this.volume = Math.max(0, Math.min(100, volume));
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
