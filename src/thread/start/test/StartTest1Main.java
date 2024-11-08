package thread.start.test;


import static util.MyLogger.*;

public class StartTest1Main {

    public static void main(String[] args) {
        final CounterThread counterThread = new CounterThread();
        counterThread.start();
    }


    static class CounterThread extends Thread {
        @Override
        public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        log("value: " + i);

                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
            }
        }
    }
}

