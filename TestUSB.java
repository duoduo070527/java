
// USB接口：定义插拔规范（契约）
interface USB {
    // 接入电脑
    void connect();
    // 断开电脑
    void disconnect();
}

// 鼠标实现USB规范
class Mouse implements USB {
    @Override
    public void connect() {
        System.out.println("鼠标插入USB接口，光标就绪");
    }

    @Override
    public void disconnect() {
        System.out.println("鼠标拔出USB接口");
    }
}

// 键盘实现USB规范
class Keyboard implements USB {
    @Override
    public void connect() {
        System.out.println("键盘插入USB接口，按键可用");
    }

    @Override
    public void disconnect() {
        System.out.println("键盘拔出USB接口");
    }
}

// 电脑类：可以接入任意USB设备
class Computer {
    public void plugUSB(USB usbDevice) {
        usbDevice.connect();
    }
    public void pullUSB(USB usbDevice) {
        usbDevice.disconnect();
    }
}

// 测试
public class TestUSB {
    public static void main(String[] args) {
        Computer pc = new Computer();
        USB mouse = new Mouse();
        USB keyboard = new Keyboard();

        pc.plugUSB(mouse);
        pc.plugUSB(keyboard);
        pc.pullUSB(mouse);
        pc.pullUSB(keyboard);
    }
}
