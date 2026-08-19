package com.l7bug.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * 内存 — 子系统类（Subsystem）
 *
 * <p>模拟计算机内存的加载操作，将数据写入指定位置。
 * GOF 角色：Subsystem（子系统），被外观类统一调度。</p>
 */
public class Memory {

    private static final Logger log = LoggerFactory.getLogger(Memory.class);

    /** 内部数据缓冲区，保存最近一次 load 的数据 */
    private byte[] data = new byte[0];

    /**
     * 将数据加载到内存的指定位置。
     *
     * @param position 内存地址
     * @param data     要加载的数据
     */
    public void load(long position, byte[] data) {
        this.data = Arrays.copyOf(data, data.length);
        log.info("内存: 在地址 0x{} 加载了 {} 字节数据",
                Long.toHexString(position), data.length);
    }

    /**
     * 获取内存中当前存储的数据（防御性拷贝）。
     *
     * @return 内存中的数据副本
     */
    public byte[] getData() {
        return Arrays.copyOf(data, data.length);
    }
}
