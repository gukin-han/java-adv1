package thread.bounded;

import util.ThreadUtils;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.MyLogger.log;
import static util.ThreadUtils.*;

public class BoundedQueueV2 implements BoundedQueue {
    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV2(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        while (queue.size() == max) {
            log("[put] 큐가 가득참, 생산자 대기");
            sleep(1000); // 다른 방식은 로그가 너무 많이 발생하기 때문에 sleep 사용
        }
        queue.offer(data);
    }

    @Override
    public synchronized String take() {
        while (queue.isEmpty()) {
            log("[take] 큐에 대이터가 없음, 소비자 대기");
            sleep(1000); // 다른 방식은 로그가 너무 많이 발생하기 때문에 sleep 사용
        }
        return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
