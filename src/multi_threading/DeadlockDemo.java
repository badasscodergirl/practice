package multi_threading;

public class DeadlockDemo {

    public static void main(String[] args) throws InterruptedException {
        DeadlockCounter dc = new DeadlockCounter();
        dc.runTest();;
    }
}

class DeadlockCounter {

    private int counter = 0;

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    void runTest() throws InterruptedException {
        Thread t1 = new Thread(incrementer);
        Thread t2 = new Thread(decremeter);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    Runnable incrementer = new Runnable() {
        @Override
        public void run() {
            try{
                for(int i = 0; i < 100; i++) {
                    incrementCount();
                    System.out.println("Incrementing..."+i);
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
    };

    Runnable decremeter = new Runnable() {
        @Override
        public void run() {
            try{
                for(int i = 0; i < 100; i++) {
                    decremnetCount();
                    System.out.println("Decrementing..."+i);
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
    };

    void incrementCount() throws InterruptedException {
        synchronized (lock2) {
            System.out.println("Lock2 acquired for increment");
            Thread.sleep(1000);

            synchronized (lock1) {
                counter++;
            }
        }
    }

    void decremnetCount() throws InterruptedException {
        synchronized (lock1) {
            System.out.println("Lock1 acquired in decrement");
            Thread.sleep(1000);

            synchronized (lock2) {
                counter--;
            }
        }
    }

}
