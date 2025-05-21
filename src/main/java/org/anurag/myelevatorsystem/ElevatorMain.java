package org.anurag.myelevatorsystem;

import java.util.Random;

public class ElevatorMain {
    public static void main(String[] args) {
        ElevatorManager elevatorManager = new ElevatorManager(3);

        ElevatorRequest r1 = new ElevatorRequest(5,10,1);
        ElevatorRequest r2 = new ElevatorRequest(3, 7, 2);
        ElevatorRequest r3 = new ElevatorRequest(8, 2, 3);
        ElevatorRequest r4 = new ElevatorRequest(1, 9, 4);

        elevatorManager.addRequest(r1);
        ElevatorUtil.addDelay(1000);
        elevatorManager.addRequest(r2);
        elevatorManager.addRequest(r3);
        ElevatorUtil.addDelay(2000);
        elevatorManager.addRequest(r4);
    }
}
