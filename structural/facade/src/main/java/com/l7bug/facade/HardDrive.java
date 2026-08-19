package com.l7bug.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 硬盘 — 子系统类（Subsystem）
 *
 * <p>模拟计算机硬盘的读取操作，从指定扇区读取数据。
 * GOF 角色：Subsystem（子系统），被外观类统一调度。</p>
 */
public class HardDrive {

    private static final Logger log = LoggerFactory.getLogger(HardDrive.class);

    /** 启动扇区起始地址 */
    private static final long BOOT_SECTOR = 0L;

    /** 模拟启动数据 */
    private static final byte[] BOOT_DATA = {0x55, (byte) 0xAA, 0x0E, 0x31};

    /**
     * 从指定逻辑块地址读取数据。
     *
     * @param lba  逻辑块地址（Logical Block Address）
     * @param size 要读取的字节数
     * @return 读取到的数据
     */
    public byte[] read(long lba, int size) {
        log.info("硬盘: 从逻辑块地址 0x{} 读取 {} 字节",
                Long.toHexString(lba), size);

        // 模拟读取：返回启动数据（截断或填充到指定大小）
        byte[] result = new byte[size];
        int copyLength = Math.min(BOOT_DATA.length, size);
        System.arraycopy(BOOT_DATA, 0, result, 0, copyLength);
        return result;
    }

    /**
     * 获取启动扇区地址（常量，供外部引用）。
     *
     * @return 启动扇区起始地址
     */
    public long getBootSector() {
        return BOOT_SECTOR;
    }
}
