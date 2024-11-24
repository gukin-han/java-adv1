package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV3 implements BankAccount {

    private int balance;

    public BankAccountV3(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int amount) {

        //synchronized 단점: 한 번에 하나의 스레드만 실행 가능 -> 성능 하락
        // 필요한 부분 = 꼭 하나의 스레드만 실행되어야 하는 부분
        // 필요한 부분만 설정해서 성능 하락을 최대한 방지해야함

        log("거래 시작: " + getClass().getSimpleName());

        // === 임계 영역 시작 ===
        synchronized (this) {
            log("[검증 시작] 출금액: " + amount + ", 잔액: " + balance);
            if (balance < amount) {
                log("[검증 실패] 출금액: " + amount + ", 잔액: " + balance);
                return false;
            }
            log("[검증 완료] 출금액: " + amount + ", 잔액: " + balance);
            sleep(1000);
            balance = balance - amount;
            log("[출금 완료] 출금액: " + amount + ", 변경 잔액: " + balance);
        }
        // === 임계 영역 종료 ===

        log("거래 종료");
        return false;
    }

    @Override
    public synchronized int getBalance() {
        return balance;
    }
}
