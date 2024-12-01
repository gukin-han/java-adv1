package thread.increment;

import util.ThreadUtils;

import java.util.ArrayList;
import java.util.List;

import static util.ThreadUtils.*;

public class IncrementThreadMain {

    public static final int THREAD_COUNT = 1000;

    public static void main(String[] args) throws InterruptedException {
        test(new BasicInteger());
        test(new VolatileInteger());
        test(new SyncInteger());
        test(new MyAtomicInteger());
    }

    private static void test(IncrementInteger incrementInteger) throws InterruptedException {

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                sleep(10); // 너무 빨리 실행되기 때문에, 다른 스레드와 동시 실행을 위해 잠깐 쉬었다가 실행
                incrementInteger.increment();
            }
        };


        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            final Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        final int result = incrementInteger.get();
        System.out.println(incrementInteger.getClass().getSimpleName() + " result: " + result);
    }
}