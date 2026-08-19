package com.l7bug.bridge;

/**
 * 设备接口（实现部分 - Implementor）
 *
 * <p>定义设备的基本操作：开关、音量控制、获取设备名称。
 * 不同的设备（电视、收音机等）各自实现此接口，
 * 与遥控器（抽象部分）独立变化。</p>
 */
public interface Device {

    /**
     * 开启设备
     */
    void enable();

    /**
     * 关闭设备
     */
    void disable();

    /**
     * 设置音量
     *
     * @param volume 音量值（0-100）
     */
    void setVolume(int volume);

    /**
     * 获取当前音量
     *
     * @return 当前音量值
     */
    int getVolume();

    /**
     * 查询设备是否开启
     *
     * @return 设备开启返回 true
     */
    boolean isEnabled();

    /**
     * 获取设备名称（用于显示）
     *
     * @return 设备名称
     */
    String getName();
}
