package exampleproject;

public class Time {

    private String startTime;
    private String endTime;

    public Time(String startTime, String endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Time(){
    }

    public String getStartTime(){
        return this.startTime;
    }

    public String getEndTime(){
        return this.endTime;
    }

    public void setStartTime(String time){
        this.startTime = time;
    }

    public void setEndTime(String time){
        this.endTime = time;
    }


    @Override
    public String toString() {
        return startTime + " - " + endTime;
    }

    public static void main(String[] args) {
    }
    
}
