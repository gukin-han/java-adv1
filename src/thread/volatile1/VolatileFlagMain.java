package thread.volatile1;

import util.ThreadUtils;

import static util.MyLogger.*;
import static util.ThreadUtils.*;

public class VolatileFlagMain {

    public static void main(String[] args) {
        final MyTask task = new MyTask();
        final Thread t = new Thread(task, "work");
        log("runFlag = " + task.runFlag);
        t.start();

        sleep(1000);
        log("runFlag를 false로 변경 시도");
        task.runFlag = false;
        log("runFlag = " + task.runFlag);
        log("main 종료");
    }

    static class MyTask implements Runnable {
//        boolean runFlag = true;
        volatile boolean runFlag = true;

        @Override
        public void run() {
            log("task 시작");
            while (runFlag) {
                // runFlag가 false로 변하면 탈출
            }
            log("task 종료");
        }
    }

}
