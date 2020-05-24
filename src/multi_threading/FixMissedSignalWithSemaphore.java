package multi_threading;

import java.util.concurrent.Semaphore;

public class FixMissedSignalWithSemaphore {

    public static void main(String args[]) throws InterruptedException {
        FixedMissedSignalExample.example();
    }
}

class FixedMissedSignalExample {
    static void example() throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);

        Thread signaller = new Thread(() -> {
            semaphore.release();
            System.out.println("Signal sent");
        });

        Thread waiter = new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("Received signal");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        signaller.start();
        signaller.join();

        Thread.sleep(10000);


        waiter.start();
        waiter.join();

    }
}
