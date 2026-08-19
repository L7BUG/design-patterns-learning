package com.l7bug.bridge;

import lombok.extern.slf4j.Slf4j;

/**
 * 桥接模式演示
 *
 * <p>场景：遥控器 & 设备
 * - RemoteControl 是抽象部分（Abstraction）
 * - AdvancedRemoteControl 是细化抽象（RefinedAbstraction）
 * - Device 是实现部分接口（Implementor）
 * - Tv 和 Radio 是具体实现（ConcreteImplementor）
 * <p>抽象（遥控器）和实现（设备）可以独立变化，
 * 任意遥控器可以控制任意设备。</p>
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        log.info("=== 桥接模式演示 ===\n");

        // 1. 基础遥控器控制电视
        log.info("1. 基础遥控器 + 电视");
        Device tv = new Tv();
        RemoteControl basicRemote = new BasicRemoteControl(tv);
        basicRemote.togglePower();
        basicRemote.volumeUp();
        basicRemote.volumeUp();
        basicRemote.volumeUp();
        log.info("   {} 音量: {}, 状态: {}",
                tv.getName(), tv.getVolume(), tv.isEnabled() ? "开启" : "关闭");

        // 2. 高级遥控器控制电视（演示静音功能）
        log.info("\n2. 高级遥控器 + 电视（静音）");
        AdvancedRemoteControl advancedRemote = new AdvancedRemoteControl(tv);
        advancedRemote.mute();
        log.info("   {} 音量: {}（静音后）", tv.getName(), tv.getVolume());
        advancedRemote.togglePower();
        log.info("   {} 状态: {}", tv.getName(), tv.isEnabled() ? "开启" : "关闭");

        // 3. 基础遥控器控制收音机（演示不同设备独立工作）
        log.info("\n3. 基础遥控器 + 收音机");
        Device radio = new Radio();
        RemoteControl radioRemote = new BasicRemoteControl(radio);
        radioRemote.togglePower();
        radioRemote.volumeUp();
        radioRemote.volumeUp();
        radioRemote.volumeUp();
        radioRemote.volumeUp();
        radioRemote.volumeUp();
        log.info("   {} 音量: {}, 状态: {}",
                radio.getName(), radio.getVolume(), radio.isEnabled() ? "开启" : "关闭");

        // 4. 展示设备状态独立性
        log.info("\n4. 设备状态独立性验证");
        radioRemote.volumeDown();
        radioRemote.volumeDown();
        log.info("   {} 音量: {}（降低后）", radio.getName(), radio.getVolume());
        log.info("   {} 音量: {}（互不影响）", tv.getName(), tv.getVolume());

        log.info("\n=== 桥接模式演示完成 ===");
    }
}
