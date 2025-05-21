package org.anurag.myelevatorsystem;

public class ElevatorRequest {
    int id;
    int sourceFloor;
    int destinationFloor;
    Directions requestDirection;
    Boolean isRequestProcessing;

    public ElevatorRequest(int sourceFloor, int destinationFloor, int id) {
        this.sourceFloor = sourceFloor;
        this.destinationFloor = destinationFloor;
        this.id = id;
        if(sourceFloor < destinationFloor)
            requestDirection = Directions.UP;
        else if(sourceFloor > destinationFloor)
            requestDirection = Directions.DOWN;
        else{
            // throw some error
        }
        this.isRequestProcessing = false;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public int getSourceFloor() {
        return sourceFloor;
    }

    public void setSourceFloor(int sourceFloor) {
        this.sourceFloor = sourceFloor;
    }

    public Directions getRequestDirection() {
        return requestDirection;
    }

    public void setRequestDirection(Directions requestDirection) {
        this.requestDirection = requestDirection;
    }

    public Boolean getRequestProcessing() {
        return isRequestProcessing;
    }

    public void setRequestProcessing(Boolean requestProcessing) {
        isRequestProcessing = requestProcessing;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
