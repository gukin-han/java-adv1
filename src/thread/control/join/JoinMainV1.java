package thread.control.join;

import static util.MyLogger.*;
import static util.ThreadUtils.*;

public class JoinMainV1 {

    public static void main(String[] args) {
        log("Start");
        final SumTask task1 = new SumTask(1, 50);
        final SumTask task2 = new SumTask(51, 100);
        final Thread thread1 = new Thread(task1);
        final Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();

        log("task1.result = " + task1.result);
        log("task2.result = " + task2.result);

        int sumAll = task1.result + task2.result; // main 스레드가 thread1, thread2의 작업을 기다려주지 않고 sum을 함 -> 0
        log("task1 + task1 = " + sumAll);
        log("End");
    }


    static class SumTask implements Runnable {
        int startValue;
        int endValue;
        int result = 0;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public void run() {
            log("작업 시작");
            sleep(2000);
            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                sum += i;
            }
            result = sum;
            log("작업 완료 result=" + result);
        }
    }

}
