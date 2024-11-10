package thread.control;

public class CheckedExceptionMain {

    public static void main(String[] args) throws Exception {
        throw new Exception(); // main() 은 체크 예외를 밖으로 던질 수 있다.
    }

    static class CheckedRunnable implements Runnable {
        @Override
        public void run() {
//            throw new Exception(); // 주석 풀면 예외 발생
        }
    }

}
