package com.l7bug.builder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 建造者模式单元测试
 */
class BuilderTest {

    @Test
    void buildWithRequiredParamsOnly() {
        Computer computer = new Computer.Builder("Intel i5", "16GB").build();

        assertNotNull(computer);
        assertEquals("Intel i5", computer.getCpu());
        assertEquals("16GB", computer.getRam());
        // 可选参数有默认值
        assertEquals("256GB SSD", computer.getStorage());
        assertEquals("集成显卡", computer.getGpu());
        assertTrue(computer.isHasWifi());
        assertFalse(computer.isHasBluetooth());
    }

    @Test
    void buildWithAllParams() {
        Computer computer = new Computer.Builder("AMD Ryzen 9", "64GB")
                .storage("2TB NVMe SSD")
                .gpu("NVIDIA RTX 4090")
                .motherboard("华硕 ROG")
                .powerSupply("850W 金牌")
                .hasWifi(true)
                .hasBluetooth(true)
                .build();

        assertNotNull(computer);
        assertEquals("AMD Ryzen 9", computer.getCpu());
        assertEquals("64GB", computer.getRam());
        assertEquals("2TB NVMe SSD", computer.getStorage());
        assertEquals("NVIDIA RTX 4090", computer.getGpu());
        assertEquals("华硕 ROG", computer.getMotherboard());
        assertEquals("850W 金牌", computer.getPowerSupply());
        assertTrue(computer.isHasWifi());
        assertTrue(computer.isHasBluetooth());
    }

    @Test
    void buildWithPartialParams() {
        Computer computer = new Computer.Builder("Intel i7", "32GB")
                .storage("1TB SSD")
                .gpu("NVIDIA RTX 3060")
                .build();

        assertNotNull(computer);
        assertEquals("Intel i7", computer.getCpu());
        assertEquals("32GB", computer.getRam());
        assertEquals("1TB SSD", computer.getStorage());
        assertEquals("NVIDIA RTX 3060", computer.getGpu());
        // 未设置的可选参数使用默认值
        assertEquals("标准主板", computer.getMotherboard());
        assertEquals("450W", computer.getPowerSupply());
    }

    @Test
    void builderChaining() {
        // 链式调用测试
        Computer computer = new Computer.Builder("Intel i5", "16GB")
                .storage("512GB SSD")
                .gpu("集成显卡")
                .motherboard("华硕 B660")
                .powerSupply("500W")
                .hasWifi(true)
                .hasBluetooth(false)
                .build();

        assertNotNull(computer);
        // 验证所有设置的值
        assertEquals("Intel i5", computer.getCpu());
        assertEquals("16GB", computer.getRam());
        assertEquals("512GB SSD", computer.getStorage());
        assertEquals("华硕 B660", computer.getMotherboard());
    }

    @Test
    void builderValidation() {
        // 测试必填参数校验
        assertThrows(IllegalArgumentException.class, () -> {
            new Computer.Builder(null, "16GB");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Computer.Builder("Intel i5", null);
        });
    }
}
