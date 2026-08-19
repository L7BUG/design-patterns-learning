package com.l7bug.facade;

/**
 * 计算机外观 — 外观类（Facade）
 *
 * <p>为 CPU、内存、硬盘三个子系统提供统一的启动接口。
 * 客户端只需调用 {@link #start()}，无需了解各子系统的交互顺序。
 * GOF 角色：Facade（外观），封装子系统的复杂调用流程。</p>
 */
public class ComputerFacade {

    private static final long BOOT_ADDRESS = 0x0001L;

    private final Cpu cpu;
    private final Memory memory;
    private final HardDrive hardDrive;

    /**
     * 构造计算机外观，注入三个子系统实例。
     *
     * @param cpu       CPU 子系统
     * @param memory    内存子系统
     * @param hardDrive 硬盘子系统
     */
    public ComputerFacade(Cpu cpu, Memory memory, HardDrive hardDrive) {
        this.cpu = cpu;
        this.memory = memory;
        this.hardDrive = hardDrive;
    }

    /**
     * 启动计算机：冻结 CPU → 从硬盘读取启动数据 → 加载到内存 → 跳转 → 执行。
     *
     * <p>这是一个简化的启动流程，对外隐藏了子系统之间的协调细节。</p>
     */
    public void start() {
        // 1. 冻结 CPU，防止在加载数据时执行旧指令
        cpu.freeze();

        // 2. 从硬盘读取启动数据并加载到内存
        byte[] bootData = hardDrive.read(hardDrive.getBootSector(), 4);
        memory.load(BOOT_ADDRESS, bootData);

        // 3. CPU 跳转到启动地址并执行
        cpu.jump(BOOT_ADDRESS);
        cpu.execute();
    }

    /**
     * 获取 CPU 子系统引用（供测试验证）。
     *
     * @return CPU 实例
     */
    public Cpu getCpu() {
        return cpu;
    }

    /**
     * 获取内存子系统引用（供测试验证）。
     *
     * @return 内存实例
     */
    public Memory getMemory() {
        return memory;
    }
}
