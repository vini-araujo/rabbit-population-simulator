//Vinicius Araujo.
//This file has the purpose to create a Rabbit object and have getter and setter methods to allow the rabbits to age, get pregnant, get assigned a sex, and more.
import java.util.Random;

public class Rabbit {
    Random rand = new Random();
    private boolean isFemale;
    private int age = 0;
    private boolean isPregnant;
    private int daysOfGestation = 0;  // (different every time 28-32)
    private int daysFromDelivery;
    private int gestationPeriod;


    public Rabbit(boolean isFemale) {   //Constructor for new Rabbit
        Random rand = new Random();
        this.isFemale = isFemale;
        if (isFemale == true) {
            isPregnant = false;
            gestationPeriod = rand.nextInt(5) + 28;
        }
    }

    public void addAge() {       //adds a day to every rabbits age by 1 day after each day
        age = age + 1;
        daysFromDelivery++;
    }

    public int getAge() {       //gets the age of the rabbit
        return age;
    }

    public void setDaysFromDelivery(int days) {     //method to set the days from delivery to -7 for rabbits who give birth, so they have to wait 7 days to get pregnant again.
        daysFromDelivery = days;
    }
    public int getDaysFromDelivery() {      //gets the value of days from delivery
        return daysFromDelivery;
    }
    public boolean getSex() {       //gets the sex of the rabbit
        return isFemale;
    }

    public boolean getIsPregnant() {        //gets if the rabbit is pregnant or not
        return isPregnant;
    }

    public int getDaysOfGestation() {       //gets how many days the rabbit is on her gestation
        return daysOfGestation;
    }

    public void setDaysOfGestation(int daysOfGestation) {       // method to set days of gestation to zero after it gives birth.
        this.daysOfGestation = 0;
    }

    public int getGestationPeriod() {       //gets days of gestation of the rabbit.
        return gestationPeriod;
    }

    public void setGestationPeriod(int gestationPeriod) {       //sets the gestation period of the rabbit to a value.
        this.gestationPeriod = gestationPeriod;
    }

    public void increaseDaysOfGestation() {         //increases the days of gestation by 1 at the end of each day if rabbit is pregnant.
        ++daysOfGestation;
        //System.out.println(daysOfGestation);
    }

    public void setPregnant(boolean pregnant) {     //sets the rabbit to pregnant or not pregnant.
        this.isPregnant = pregnant;
    }

}






