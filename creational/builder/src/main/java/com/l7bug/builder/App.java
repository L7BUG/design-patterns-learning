package com.l7bug.builder;

import lombok.extern.slf4j.Slf4j;

/**
 * 建造者模式演示
 *
 * 场景：电脑配置系统
 * - 使用 Builder 构建复杂的电脑对象
 * - 必填参数：CPU、内存
 * - 可选参数：存储、显卡、主板、电源、WiFi、蓝牙
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        log.info("=== 建造者模式演示 ===\n");

        // 1. 基础配置（只用必填参数）
        log.info("1. 基础配置电脑");
        Computer basicComputer = new Computer.Builder("Intel i5-12400", "16GB")
                .build();
        System.out.println(basicComputer);

        // 2. 办公配置
        log.info("2. 办公配置电脑");
        Computer officeComputer = new Computer.Builder("Intel i7-13700", "32GB")
                .storage("512GB SSD")
                .hasBluetooth(true)
                .build();
        System.out.println(officeComputer);

        // 3. 游戏配置
        log.info("3. 游戏配置电脑");
        Computer gamingComputer = new Computer.Builder("AMD Ryzen 9 7950X", "64GB")
                .storage("2TB NVMe SSD")
                .gpu("NVIDIA RTX 4090")
                .motherboard("华硕 ROG Crosshair X670E")
                .powerSupply("850W 金牌")
                .hasWifi(true)
                .hasBluetooth(true)
                .build();
        System.out.println(gamingComputer);

        // 4. 链式调用演示
        log.info("4. 链式调用演示");
        Computer customComputer = new Computer.Builder("Intel i9-14900K", "128GB")
                .storage("4TB NVMe SSD")
                .gpu("NVIDIA RTX 4090 Ti")
                .motherboard("华硕 ROG Maximus Z790")
                .powerSupply("1000W 铂金")
                .hasWifi(true)
                .hasBluetooth(true)
                .build();
        System.out.println(customComputer);

        log.info("=== 建造者模式演示完成 ===");
    }
}
