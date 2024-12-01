package thread.increment;

import java.util.ArrayList;
import java.util.List;

import static util.ThreadUtils.sleep;

public class IncrementPerformanceMain {

    public static final int COUNT = 100_000_000;

    public static void main(String[] args) throws InterruptedException {
        test(new BasicInteger());
        test(new VolatileInteger());
        test(new SyncInteger());
        test(new MyAtomicInteger());
    }

    private static void test(IncrementInteger incrementInteger) throws InterruptedException {
        final long startMs = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++) {
            incrementInteger.increment();
        }
        final long endMs = System.currentTimeMillis();
        System.out.println(incrementInteger.getClass().getSimpleName() + " : ms=" + (endMs - startMs));
    }
}
