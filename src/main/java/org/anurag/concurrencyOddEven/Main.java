package org.anurag.concurrencyOddEven;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {

        OddEvenPrint oddEvenPrint = new OddEvenPrint();
        oddEvenPrint.start();
    }
}