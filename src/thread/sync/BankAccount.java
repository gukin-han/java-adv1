package thread.sync;

public interface BankAccount {

    /**
     * 계좌의 잔액이 출금할 금액보다 많으면 성공 (true)
     * 적으면 실패 (false)를 반환
     */
    boolean withdraw(int amount);

    int getBalance();
}
