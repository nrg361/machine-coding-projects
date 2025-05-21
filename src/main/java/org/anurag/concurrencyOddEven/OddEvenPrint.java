package org.anurag.concurrencyOddEven;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OddEvenPrint {

    int val;

    public OddEvenPrint(){
        val = 0;
    }

    public synchronized void print() throws InterruptedException {
        while(val < 20){
            int remainder = val%2;
            if(Thread.currentThread().getName().equals("Thread-"+remainder)){
                log.info("this is printed by {} and value = {}", Thread.currentThread().getName(), val);
                val++;
                notifyAll();
            }else{
                wait();
            }
        }
    }
    public void start(){
        Thread t1 = new Thread(() -> {
            try {
                print();
            } catch (InterruptedException e) {

            }
        }, "Thread-0"); // even
        Thread t2 = new Thread(() -> {
            try {
                print();
            } catch (InterruptedException e) {

            }
        }, "Thread-1");

        t1.start();
        t2.start();
    }
}