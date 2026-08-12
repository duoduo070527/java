
//方式1：继承Thread类
class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "正在运行");
        }
    }
}

//方式2：实现Runnable接口（推荐，避免单继承局限，资源共享）
class TicketTask implements Runnable {
    private int ticket = 10; //总票数
    private final Object lock = new Object(); //锁对象

    @Override
    public void run() {
        while (true) {
            //同步代码块：锁住共享资源，解决线程安全
            synchronized (lock) {
                if (ticket <= 0) {
                    System.out.println(Thread.currentThread().getName() + "：票已售罄");
                    break;
                }
                try {
                    Thread.sleep(200); //模拟售票耗时
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "售出第" + ticket-- + "张票");
            }
        }
    }
}

public class ThreadTicketDemo {
    public static void main(String[] args) {
        System.out.println("=====继承Thread方式启动线程=====");
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        t1.setName("线程A");
        t2.setName("线程B");
        t1.start();
        t2.start();

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n=====Runnable售票案例（3窗口卖10张票，加同步锁）=====");
        TicketTask task = new TicketTask();
        //三个窗口共用同一个任务对象，共享票数资源
        Thread win1 = new Thread(task, "窗口1");
        Thread win2 = new Thread(task, "窗口2");
        Thread win3 = new Thread(task, "窗口3");
        win1.start();
        win2.start();
        win3.start();
    }
}
