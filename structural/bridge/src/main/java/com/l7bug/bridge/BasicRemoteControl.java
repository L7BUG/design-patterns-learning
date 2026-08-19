package com.l7bug.bridge;

/**
 * 基础遥控器（抽象部分的具体实现 - RefinedAbstraction）
 *
 * <p>继承 RemoteControl，直接复用基本操作，不扩展额外功能。
 * 用于演示任意遥控器可以控制任意设备。</p>
 */
public class BasicRemoteControl extends RemoteControl {

    /**
     * 构造函数，注入设备实例（桥接）
     *
     * @param device 要控制的设备
     */
    public BasicRemoteControl(Device device) {
        super(device);
    }
}
