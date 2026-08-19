package com.l7bug.facade;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 外观模式单元测试
 */
class FacadeTest {

    @Test
    void facadeStartExecutesAllSteps() {
        // Arrange
        Cpu cpu = new Cpu();
        Memory memory = new Memory();
        HardDrive hardDrive = new HardDrive();
        ComputerFacade facade = new ComputerFacade(cpu, memory, hardDrive);

        // Act
        facade.start();

        // Assert — CPU 已执行
        assertTrue(cpu.isExecuted(), "CPU 应已被执行");

        // Assert — 内存中已加载启动数据
        byte[] memoryData = memory.getData();
        assertEquals(4, memoryData.length, "内存应加载 4 字节启动数据");
        assertEquals(0x55, memoryData[0] & 0xFF, "第一个字节应为 0x55（启动标志）");
        assertEquals(0xAA, memoryData[1] & 0xFF, "第二个字节应为 0xAA（启动标志）");
    }

    @Test
    void facadeHidesSubsystems() {
        // 客户端只与 ComputerFacade 交互，无需直接操作子系统
        ComputerFacade facade = new ComputerFacade(new Cpu(), new Memory(), new HardDrive());

        // 外观类只暴露 start() —— 客户端唯一的启动入口
        facade.start();

        // 通过 getter 验证子系统确实被协调了
        assertTrue(facade.getCpu().isExecuted());
        assertTrue(facade.getMemory().getData().length > 0);
    }

    @Test
    void subsystemsUsableDirectly() {
        // 子系统可以独立使用（外观只是简化了组合调用）
        Cpu cpu = new Cpu();
        Memory memory = new Memory();
        HardDrive hardDrive = new HardDrive();

        // 直接调用各子系统
        cpu.freeze();
        byte[] data = hardDrive.read(0L, 4);
        memory.load(0x100L, data);
        cpu.jump(0x100L);
        cpu.execute();

        // 验证结果与通过外观启动一致
        assertTrue(cpu.isExecuted());
        assertEquals(4, memory.getData().length);
    }

    @Test
    void startLoadsCorrectBootData() {
        // 验证启动数据内容的正确性
        Cpu cpu = new Cpu();
        Memory memory = new Memory();
        HardDrive hardDrive = new HardDrive();
        ComputerFacade facade = new ComputerFacade(cpu, memory, hardDrive);

        facade.start();

        byte[] data = memory.getData();
        // 预期启动数据：{0x55, 0xAA, 0x0E, 0x31}
        assertArrayEquals(new byte[]{0x55, (byte) 0xAA, 0x0E, 0x31}, data,
                "内存应包含完整的启动数据");
    }
}
