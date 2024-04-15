package exampleproject;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private String name;
    private Time time;
    private List<String> timeList = new ArrayList<String>();

    public Person(String name){
        validateName(name);
        this.name = name;
    }

    public Person(){}

    public Person(String name, Time time){
        validateName(name);
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public Time getTime() {
        return time; 
    }

    public List<String> getTimeList(){
        return timeList;
    }

    public String getTimeAtIndex (int indeks) {
        return timeList.get(indeks);
    }


    public void setName(String name){
        validateName(name);
        this.name = name;
    }

    public void setTime(Time time){
        this.time = time;
    }

    public void addTime(String time){
        if (time == null) {
            throw new IllegalArgumentException("Velg en tid først");
        }
        if (timeList.contains(time)){ 
            throw new IllegalArgumentException("Kan ikke legge til duplikat av tid");
        }
        if (timeList.size() >= 3){
            throw new IllegalArgumentException("Kan ikke booke flere enn  3 tider");
        }
        else {
            timeList.add(time);
        }
    }

    public void removeTime(String time){
        if (timeList.contains(time) ){
            timeList.remove(time);
        }
        else {
            throw new IllegalArgumentException("Kan ikke fjerne booking du ikke har gjort");
        }
    }

    public void validateName(String name){
        if(name.length() <= 1 || name.length() >= 20){
            throw new IllegalArgumentException("Må være lengre enn ett tegn og mindre enn 20 tegn!");
        }
        if(!name.toLowerCase().matches("[a-æøå]*")){
            throw new IllegalArgumentException("Kan ikke inneholde tall eller spesielle karakterer!");
        }
    }

    @Override
    public String toString() {
        return getName() + " har booket tider " + getTimeList();
    }

    public static void main(String[] args) {
        Person mac = new Person("Macmiller");
        mac.addTime(new Time("08:00", "08:30"));
        mac.addTime(new Time("09:00", "09:30"));
        System.out.println( mac.getTime());


    }

    


    
}
