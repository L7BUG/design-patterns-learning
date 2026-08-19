package com.l7bug.facade;

import lombok.extern.slf4j.Slf4j;

/**
 * 外观模式演示
 *
 * <p>场景：计算机启动
 * - ComputerFacade 是外观类（Facade），提供 start() 一键启动
 * - Cpu、Memory、HardDrive 是子系统（Subsystem），各自负责底层操作
 * <p>客户端（App）只与 ComputerFacade 交互，无需了解子系统的调用顺序和细节。</p>
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        log.info("=== 外观模式演示 ===\n");

        // 1. 创建子系统实例
        log.info("1. 创建子系统：CPU、内存、硬盘");
        Cpu cpu = new Cpu();
        Memory memory = new Memory();
        HardDrive hardDrive = new HardDrive();

        // 2. 通过外观类启动计算机（隐藏子系统协调细节）
        log.info("\n2. 通过 ComputerFacade 启动计算机");
        ComputerFacade computer = new ComputerFacade(cpu, memory, hardDrive);
        computer.start();

        // 3. 验证启动结果
        log.info("\n3. 验证启动结果");
        log.info("   CPU 已执行: {}", cpu.isExecuted() ? "是" : "否");
        log.info("   内存已加载: {} 字节", memory.getData().length);

        log.info("\n=== 外观模式演示完成 ===");
    }
}
