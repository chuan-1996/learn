package pattern.structure.facade;

public class Camera {
    // 打开录像机
    public void turnOn() {
        System.out.println("Turning on the camera.");
    }
    // 关闭录像机
    public void turnOff() {
        System.out.println("Turning off the camera.");
    }
    // 转动录像机
    public void rotate(int degrees) {
        System.out.println("Rotating the camera by "+degrees+" degrees.");
    }
}
