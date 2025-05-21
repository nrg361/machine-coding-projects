package org.anurag.myelevatorsystem;

import java.util.ArrayList;
import java.util.List;

public class Elevator {
    Directions currentDirection;
    int currentFloor;
    int destinationFloor;
    List<ElevatorRequest> requests;
    int id;

    public Elevator(int id) {
        currentFloor = 1;
        destinationFloor = 1;
        currentDirection = Directions.NEUTRAL;
        this.id = id;
        requests = new ArrayList<>();
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Directions getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Directions currentDirection) {
        this.currentDirection = currentDirection;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public List<ElevatorRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<ElevatorRequest> requests) {
        this.requests = requests;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public synchronized void addRequest(ElevatorRequest request){
        System.out.println("adding request with id: " + request.getId() + " in elevator id " + this.id);
        requests.add(request);
        notifyAll();
    }

    public void start() {
        while (true) {
            if(currentDirection.equals(Directions.NEUTRAL)){
                processRequest();
            }else{
                moveInDirection();
            }
            pickAndRemoveRequests();
        }
    }
    private synchronized void processRequest(){
        while(true) {
            if (requests.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("error occured" + e.getMessage());
                }
            } else break;
        }

        ElevatorRequest request = requests.getFirst();
        destinationFloor = request.getSourceFloor();
        if(destinationFloor < currentFloor)
            currentDirection = Directions.DOWN;
        else if(destinationFloor > currentFloor)
            currentDirection = Directions.UP;
        else
            currentDirection = request.getRequestDirection();
    }

    private void moveInDirection(){
        if(Directions.UP.equals(currentDirection)){
            currentFloor++;
        }
        else if(Directions.DOWN.equals(currentDirection)){
            currentFloor--;
        }
        ElevatorUtil.addDelay(1000);

    }
    private void pickAndRemoveRequests(){
        System.out.println("Elevator " + this.id + " reached on floor " + this.currentFloor);
        requests.forEach(request -> {
            if(!request.getRequestProcessing() && currentFloor == request.getSourceFloor() && currentDirection.equals(request.getRequestDirection())){
                System.out.println("picking request with id: " + request.getId() + " by elevator id: " + this.id);
                request.setRequestProcessing(true);
                if(Directions.UP.equals(currentDirection))
                    destinationFloor = Math.max(destinationFloor, request.getDestinationFloor());
                else
                    destinationFloor = Math.min(destinationFloor, request.getDestinationFloor());
            }
        });

        requests.removeIf(request -> {
            if(request.getRequestProcessing() && request.getDestinationFloor() == currentFloor){
                // drop this request
                System.out.println("removing request with id: " + request.getId() + " by elevator id: " + this.id);
                return true;
            }
            return false;
        });

        if(currentFloor == destinationFloor)
            currentDirection = Directions.NEUTRAL;
    }
}
