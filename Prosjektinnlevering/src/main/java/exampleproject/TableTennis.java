package exampleproject;

import java.util.ArrayList;
import java.util.List;

public class TableTennis {
    
    private Person person = new Person();
    private String time = new Time().toString();
    private int fee = 20;
    private List<String> listOfTimes = new ArrayList<String>();

    public TableTennis(){}

    public TableTennis(Person person){
        this.person = person;
    }

    public TableTennis(Person person, String time){
        this.person = person;
        this.time = time;
    }

    public List<String> getListOfTimes(){
        listOfTimes.addAll(List.of(
                    ("08:00 - 08:30"),
                    ("08:30 - 09:00"),
                    ("09:00 - 09:30"),
                    ("09:30 - 10:00"),
                    ("10:00 - 10:30"),
                    ("10:30 - 11:00"),
                    ("11:00 - 11:30"),
                    ("11:30 - 12:00"),
                    ("12:00 - 12:30"),
                    ("12:30 - 13:00"),
                    ("13:00 - 13:30"),
                    ("13:30 - 14:00"),
                    ("14:00 - 14:30"),
                    ("14:30 - 15:00"),
                    ("15:00 - 15:30"),
                    ("15:30 - 16:00"),
                    ("16:00 - 16:30"),
                    ("16:30 - 17:00")
                )); 
        
                return new ArrayList<>(listOfTimes);
            }


    public Person getPerson(){
        return person;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return person.getName();
    }

    public int getFee() {
        return fee;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCost(int antallTimer){
        return antallTimer* this.getFee();
    }

    public void changeFee(int fee){
        if(fee < 0 || fee > 10){
            throw new IllegalArgumentException("ny avgift må være mellom 0 og 10 kr/min");
        }
        this.fee = fee;
    }

    public List<String> getBookedTimeList(){
        return person.getTimeList();
    }


    public void addBooking(String timeToBook){
        if (this.getBookedTimeList().size() >= 3){
            throw new IllegalArgumentException("Kan ikke booke flere enn tre tider");
        }
        if (this.getBookedTimeList().contains(timeToBook)){
            throw new IllegalArgumentException("Velg en tid du ikke har booket enda");
        }
        else {
            person.addTime(timeToBook);
            listOfTimes.remove(timeToBook.toString());
            
    }}


    public void removeBooking(String timeToDelete) {
        if(listOfTimes.contains(timeToDelete)) {
            throw new IllegalArgumentException("Kan ikke fjerne en tid fra du ikke har booket");
        }
        if (!this.getBookedTimeList().contains(timeToDelete)) {
            throw new IllegalArgumentException("Trykk en tid i lista til venstre");
        }
        else {
            person.removeTime(timeToDelete);
            listOfTimes.add(timeToDelete.toString());
        }
    }

    
    @Override
    public String toString() {
        if (this.getBookedTimeList() == null || this.getBookedTimeList().size() == 0) {
            return this.getName() + " har ikke booket noen tider";
        }
        if (this.getBookedTimeList().size() == 1) {
            return this.getName() + " har booket tid " + getBookedTimeList().get(getBookedTimeList().size()-1);
        }
        else {
        return this.getName() + " har booket tid " + getBookedTimeList().get(getBookedTimeList().size()-1) + "\n\n" + this.getName() + " har nå booket: " + this.getBookedTimeList();
    }
    }

    public String toStringDelete() {
        if (this.getBookedTimeList().size() == 0) {
            return this.getName() + " har ikke booket noen tider";
        }
        if (this.getBookedTimeList().size() == 1) {
            return this.getName() + " har booket tid " + getBookedTimeList().get(getBookedTimeList().size()-1);
        }
        else {
        return this.getName() + " har nå booket: " + this.getBookedTimeList();
    }
    }

    

    public static void main(String[] args) {
       
    }

    

    


}
