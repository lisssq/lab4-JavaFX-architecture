package models.CpuModel;

public class BCpuModel {
    static ICpuModel cpu = new CpuModel();

    public static ICpuModel build() {
        return cpu;
    }
}
