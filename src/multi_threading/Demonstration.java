package multi_threading;

class Demonstration {
    public static void main( String args[] ) throws InterruptedException {
        SumUpExample.runTest();
    }
}

class SumUpExample {

    long startRange;
    long endRange;
    long counter = 0;
    static long MAX_NUM = Integer.MAX_VALUE;

    public SumUpExample(long startRange, long endRange) {
        this.startRange = startRange;
        this.endRange = endRange;
    }

    public void add() {

        for (long i = startRange; i <= endRange; i++) {
            counter += i;
        }
    }

    static public void twoThreads() throws InterruptedException {

        long start = System.currentTimeMillis();
        SumUpExample s1 = new SumUpExample(1, MAX_NUM / 2);
        SumUpExample s2 = new SumUpExample(1 + (MAX_NUM / 2), MAX_NUM);

        Thread t1 = new Thread(() -> {
            s1.add();
        });

        Thread t2 = new Thread(() -> {
            s2.add();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        long finalCount = s1.counter + s2.counter;
        long end = System.currentTimeMillis();
        System.out.println("Two threads final count = " + finalCount + " took " + (end - start));
    }

    static public void fourThread() throws InterruptedException {
        long start = System.currentTimeMillis();

        SumUpExample s1 = new SumUpExample(1, MAX_NUM/4);
        SumUpExample s2 = new SumUpExample(MAX_NUM/4 + 1, MAX_NUM/2);
        SumUpExample s3 = new SumUpExample(MAX_NUM/2 + 1, 3*MAX_NUM/4);
        SumUpExample s4 = new SumUpExample(3*MAX_NUM/4 + 1, MAX_NUM);

        Thread t1 = new Thread(() -> {
           s1.add();
        });
        Thread t2 = new Thread(() -> {
            s2.add();
        });
        Thread t3 = new Thread(() -> {
            s3.add();
        });
        Thread t4 = new Thread(() -> {
            s4.add();
        });
        t1.start();
        t2.start();;
        t3.start();
        t4.start();

        t1.join();;
        t2.join();
        t3.join();;
        t4.join();

        long finalCount = s1.counter + s2.counter + s3.counter + s4.counter;
        long end = System.currentTimeMillis();

        System.out.println("Four thread final count = " + finalCount + " took " + (end - start));
    }



    static public void oneThread() {

        long start = System.currentTimeMillis();
        SumUpExample s = new SumUpExample(1, MAX_NUM );
        s.add();
        long end = System.currentTimeMillis();
        System.out.println("Single thread final count = " + s.counter + " took " + (end - start));
    }


    public static void runTest() throws InterruptedException {

        oneThread();
        twoThreads();
        fourThread();

    }
}

