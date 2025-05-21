package org.anurag.myelevatorsystem;

public class ElevatorUtil {
    public static int getDuration(Elevator elevator, ElevatorRequest request){
        int weight = 0;
//        find weight to pick the request
        if(Directions.UP.equals(request.getRequestDirection()) && Directions.UP.equals(elevator.getCurrentDirection()) && elevator.getCurrentFloor() <= request.getSourceFloor()
                || Directions.DOWN.equals(request.getRequestDirection()) && Directions.DOWN.equals(elevator.getCurrentDirection()) &&elevator.getCurrentFloor() >= request.getSourceFloor()
                || Directions.NEUTRAL.equals(elevator.getCurrentDirection())) {
            weight += Math.abs(request.getSourceFloor() - elevator.getCurrentFloor());
        }
        else{
            weight += 1000000 + Math.abs(elevator.getDestinationFloor() - elevator.getCurrentFloor()) + Math.abs(request.getSourceFloor() - elevator.getDestinationFloor());
        }
        return weight;
    }

    public static void addDelay(int delay) {
        try{
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            System.out.println("some error occured");
        }

    }
}
