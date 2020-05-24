package multi_threading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MissedSignalDemo {
    public static void main(String args[]) throws InterruptedException {
        new MissedSignalExample().runTest();
    }
}


//TODO fix this using while loop and a boolean condition
class MissedSignalExample {

    final ReentrantLock lock = new ReentrantLock();
    final Condition condition = lock.newCondition();


    Thread signaller = new Thread(() -> {
        lock.lock();
        condition.signal();
        System.out.println("Sent signal");
        lock.unlock();
    });

    Thread waiter = new Thread(() -> {
       lock.lock();

        try {
            condition.await();
            System.out.println("Received signal");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });

    public void runTest() throws InterruptedException {


        signaller.start();
        signaller.join();

        waiter.start();
        waiter.join();

        System.out.println("Program Exiting.");

    }
}
