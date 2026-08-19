package com.l7bug.bridge;

/**
 * 收音机设备（具体实现部分 - ConcreteImplementor）
 *
 * <p>实现 Device 接口，维护收音机特有的内部状态：
 * 开关状态、音量值。音量范围限制在 0-100。
 * 与 Tv 独立实现，互不影响。</p>
 */
public class Radio implements Device {

    private static final String NAME = "收音机";
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
