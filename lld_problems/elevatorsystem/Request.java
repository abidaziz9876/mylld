package lld_problems.elevatorsystem;
public class Request {
    int sourceFloor;
    int destFloor;
    Priority priority;
    public int getSourceFloor() {
        return sourceFloor;
    }
    public int getDestFloor() {
        return destFloor;
    }
    public Priority getPriority() {
        return priority;
    }
    public Request(int sourceFloor,int destFloor){
        this.sourceFloor=sourceFloor;
        this.destFloor=destFloor;
    }
    public Request(int sourceFloor,int destFloor,Priority priority){
        this.sourceFloor=sourceFloor;
        this.destFloor=destFloor;
        this.priority=priority;
    }
    
}
