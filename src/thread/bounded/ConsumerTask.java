package thread.bounded;

import static util.MyLogger.log;

public class ConsumerTask implements Runnable {
    private BoundedQueue queue;

    public ConsumerTask(BoundedQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        log("[생산 시도]     ? <- " + queue);
        final String data = queue.take();
        log("[생산 완료] " + data + " <- " + queue);
    }
}
