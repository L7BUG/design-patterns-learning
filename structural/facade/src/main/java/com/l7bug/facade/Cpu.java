package com.l7bug.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CPU — 子系统类（Subsystem）
 *
 * <p>模拟计算机 CPU 的基本操作：冻结、跳转、执行。
 * GOF 角色：Subsystem（子系统），被外观类统一调度。</p>
 */
public class Cpu {

    private static final Logger log = LoggerFactory.getLogger(Cpu.class);

    /** 是否已执行（供外部验证启动流程是否完整） */
    private boolean executed;

    /**
     * 冻结 CPU，准备加载启动数据。
     * 在执行 jump 和 execute 之前必须先调用此方法。
     */
    public void freeze() {
        log.info("CPU: 冻结处理器，准备加载数据");
    }

    /**
     * 跳转到指定内存地址。
     *
     * @param position 目标内存地址
     */
    public void jump(long position) {
        log.info("CPU: 跳转到内存地址 0x{}", Long.toHexString(position));
    }

    /**
     * 执行已加载的指令。
     * 调用后 {@link #isExecuted()} 返回 true。
     */
    public void execute() {
        executed = true;
        log.info("CPU: 执行指令完成");
    }

    /**
     * 查询 CPU 是否已执行。
     *
     * @return true 表示已执行
     */
    public boolean isExecuted() {
        return executed;
    }
}
