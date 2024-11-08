package thread.start;

public class HelloThread extends Thread {

    public HelloThread(String helloThread) {
        super(helloThread);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": run()");
    }
}
