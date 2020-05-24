package multi_threading;

public class BadSynchronization {
    public static void main( String args[] ) throws InterruptedException {
        IncorrectSynchronization.runExample();
    }
}

class IncorrectSynchronization {

    Boolean flag = Boolean.TRUE;

    public void example() throws InterruptedException {


        Thread t1 = new Thread(() -> {
            synchronized (flag) {
                try {
                    while(flag) {

                        System.out.println("Thread t1 is trying to call wait()");
                        flag.wait(1000);


                        System.out.println("Thread t1 is acquired flag lock and goes to sleep");
                        Thread.sleep(1000);

                        flag.notify();
                        System.out.println("Thread t1 is calling notify()");

                        System.out.println(flag);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            flag = false;
            System.out.println("flag value changed by Thread t2");

        });


        t1.start();
        Thread.sleep(1000);
        t2.start();
        t1.join();
        t2.join();
    }

    public static void runExample() throws InterruptedException {
        IncorrectSynchronization incorrectSynchronization = new IncorrectSynchronization();
        incorrectSynchronization.example();
    }
}
