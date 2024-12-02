package thread.cas.spinlock;

import static util.MyLogger.log;

public class SpinLockMain {
    public static void main(String[] args) {
        final SpinLockBad spinLock = new SpinLockBad();
//        final SpinLock spinLock = new SpinLock();

        final Runnable task = new Runnable() {
            @Override
            public void run() {
                spinLock.lock();
                try {
                    // critical section
                    log("비즈니스 로직 실행");
                    // sleep(1); // 오래 걸리는 로직에서 스핀 락 사용x
                } finally {
                    spinLock.unlock();
                }
            }
        };

        final Thread t1 = new Thread(task, "Thread-1");
        final Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();
    }
}
