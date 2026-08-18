package com.l7bug.builder;

/**
 * 产品 - 电脑
 *
 * 使用 Builder 模式构建复杂的电脑对象
 * 构造函数是私有的，只能通过 Builder 创建
 */
public class Computer {

    // 必填参数
    private final String cpu;
    private final String ram;

    // 可选参数
    private final String storage;
    private final String gpu;
    private final String motherboard;
    private final String powerSupply;
    private final boolean hasWifi;
    private final boolean hasBluetooth;

    // 私有构造函数，只能通过 Builder 创建
    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.gpu = builder.gpu;
        this.motherboard = builder.motherboard;
        this.powerSupply = builder.powerSupply;
        this.hasWifi = builder.hasWifi;
        this.hasBluetooth = builder.hasBluetooth;
    }

    // Getters
    public String getCpu() { return cpu; }
    public String getRam() { return ram; }
    public String getStorage() { return storage; }
    public String getGpu() { return gpu; }
    public String getMotherboard() { return motherboard; }
    public String getPowerSupply() { return powerSupply; }
    public boolean isHasWifi() { return hasWifi; }
    public boolean isHasBluetooth() { return hasBluetooth; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== 电脑配置 ===\n");
        sb.append("CPU: ").append(cpu).append("\n");
        sb.append("内存: ").append(ram).append("\n");
        if (storage != null) sb.append("存储: ").append(storage).append("\n");
        if (gpu != null) sb.append("显卡: ").append(gpu).append("\n");
        if (motherboard != null) sb.append("主板: ").append(motherboard).append("\n");
        if (powerSupply != null) sb.append("电源: ").append(powerSupply).append("\n");
        sb.append("WiFi: ").append(hasWifi ? "有" : "无").append("\n");
        sb.append("蓝牙: ").append(hasBluetooth ? "有" : "无").append("\n");
        return sb.toString();
    }

    /**
     * 静态内部类 - 建造者
     *
     * 必填参数通过构造函数传入
     * 可选参数通过链式方法设置
     */
    public static class Builder {
        // 必填参数
        private final String cpu;
        private final String ram;

        // 可选参数（有默认值）
        private String storage = "256GB SSD";
        private String gpu = "集成显卡";
        private String motherboard = "标准主板";
        private String powerSupply = "450W";
        private boolean hasWifi = true;
        private boolean hasBluetooth = false;

        /**
         * 构造函数：必须传入必填参数
         * @param cpu CPU 型号
         * @param ram 内存大小
         */
        public Builder(String cpu, String ram) {
            if (cpu == null || ram == null) {
                throw new IllegalArgumentException("CPU 和内存不能为空");
            }
            this.cpu = cpu;
            this.ram = ram;
        }

        // 链式设置可选参数，每个方法返回 this
        public Builder storage(String storage) {
            this.storage = storage;
            return this;
        }

        public Builder gpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public Builder motherboard(String motherboard) {
            this.motherboard = motherboard;
            return this;
        }

        public Builder powerSupply(String powerSupply) {
            this.powerSupply = powerSupply;
            return this;
        }

        public Builder hasWifi(boolean hasWifi) {
            this.hasWifi = hasWifi;
            return this;
        }

        public Builder hasBluetooth(boolean hasBluetooth) {
            this.hasBluetooth = hasBluetooth;
            return this;
        }

        /**
         * 构建最终的 Computer 对象
         * @return Computer 实例
         */
        public Computer build() {
            return new Computer(this);
        }
    }
}
