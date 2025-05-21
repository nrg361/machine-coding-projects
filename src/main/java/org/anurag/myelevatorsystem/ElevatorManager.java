package org.anurag.myelevatorsystem;

import java.util.ArrayList;
import java.util.List;

public class ElevatorManager {
    List<Elevator> elevatorsList;
    public ElevatorManager(int elevatorCount){
        elevatorsList = new ArrayList<>();
        for(int i=0;i<elevatorCount;i++){
            Elevator elevator = new Elevator(i+1);
            Thread t = new Thread(() -> elevator.start());
            elevatorsList.add(elevator);
            t.start();
        }
    }

    public void addRequest(ElevatorRequest request){
        int duration = Integer.MAX_VALUE;
        Elevator bestElevator = this.elevatorsList.getFirst();
        for(Elevator elevator : elevatorsList){
            int elevatorDuration = ElevatorUtil.getDuration(elevator, request);
            if(duration > elevatorDuration){
                duration = elevatorDuration;
                bestElevator = elevator;
            }
        }

        bestElevator.addRequest(request);
    }
}
