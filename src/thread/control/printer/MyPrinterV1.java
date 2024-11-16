package thread.control.printer;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static util.MyLogger.*;
import static util.ThreadUtils.*;

public class MyPrinterV1 {

    public static void main(String[] args) {
        final Printer printer = new Printer();
        final Thread printerThread = new Thread(printer, "printer");
        printerThread.start();

        final Scanner userInput = new Scanner(System.in);
        while (true) {
            log("프린터할 문서를 입력하세요. 종료(q): ");
            final String input = userInput.nextLine();
            if (input.equals("q")) {
                printer.isWorking = false;
                break;
            }

            printer.addJob(input);
        }
    }

    static class Printer implements Runnable {
        volatile boolean isWorking = true;
        Queue<String> jobQueue = new ConcurrentLinkedQueue<>();
        @Override
        public void run() {
            while (isWorking) {
                if (jobQueue.isEmpty()) {
                    continue;
                }

                final String job = jobQueue.poll();
                log("출력 시작: " + job + ", 대기 문서: " + jobQueue);
                sleep(3000); // 출력에 걸리는 시간
                log("출력 완료: " + job);
            }
            log("프린터 종료");
        }

        public void addJob(String input) {
            jobQueue.offer(input);
        }
    }

}
