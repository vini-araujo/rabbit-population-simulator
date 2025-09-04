//Vinicius Araujo.
//This file has the purpose to simulate the reproduction of the rabbits over a period of a year.
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;

public class RabbitSimulation {
    public static ArrayList <Rabbit> rabbits = new ArrayList<Rabbit>(); //ArrayList to store the initial rabbits and add the new ones after they are born.

    //public static ArrayList<Boolean> inputFile = new ArrayList<Boolean>();

    public static void main(String[] args) {
        //Reading input file to run the code.
            try {
                File input = new File("src/running.txt");
                //File input = new File("src/Assignment_1/running.txt");
                Scanner scan = new Scanner(input);
                int[][] inputsArray = new int[3][2];
                int index = 0;
                while(scan.hasNextLine() && index < 3) {
                    String[] pair = scan.nextLine().split("\t");
                    inputsArray[index][0] = Integer.parseInt(pair[0]);
                    inputsArray[index][1] = Integer.parseInt(pair[1]);
                    index++;


                }
                scan.close();
                for(int[] pair : inputsArray) {
                    // System.out.println(pair[0] + " " + pair[1]);    //testing to see if the input is being read correctly.

                }
                finalRun(inputsArray[0][0], inputsArray[0][1]);
                System.out.println();
                finalRun(inputsArray[1][0], inputsArray[1][1]);
                System.out.println();
                finalRun(inputsArray[2][0], inputsArray[2][1]);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            System.getProperty("user.dir");
        }





    public static void litter() { //method to let them have a liter from 3-8 rabbits
    Random rand = new Random();
    int litterSize = rand.nextInt(6) + 3;   //gets a random number between 3 and 8
        for (int i = 0; i <= litterSize; i++) {
            rabbits.add(new Rabbit(rand.nextBoolean())); // creates the random amount of new rabbits with 50% chance male 50 % female.
        }
    }

    public static void year(ArrayList<Rabbit> rabbits) {
        Random rand = new Random();
        for (int i = 0; i <= 365; i++) {    // for loop for 365 days. It
            //System.out.println("day: " + i);
            int totalRabbits = 0;
            for (int j = 0; j <= rabbits.size() - 1; j++ ) {    // for loop to go through each rabbit in the ArrayList after each day.
                if (rabbits.get(j).getSex()) {  //checking if rabbit is female
                    //System.out.println(rabbits.get(j).getDaysOfGestation());
                        if (rabbits.get(j).getAge() >= 100) {       //check if rabbit is at least 100 days old
                            if (rabbits.get(j).getIsPregnant()) {       //checking if rabbit is pregnant
                                if (rabbits.get(j).getDaysOfGestation() >= rabbits.get(j).getGestationPeriod()) {   //gets how long it's been pregnant
                                        totalRabbits = totalRabbits + 1;        //adds 1 to total
                                        rabbits.get(j).setDaysOfGestation(0);   //sets days of gestation to 0
                                        rabbits.get(j).setPregnant(false);      //sets it to not pregnant anymore
                                        rabbits.get(j).setDaysFromDelivery(-7);     //sets it to -7 days from delivery so that it has to wait 7 days to be able to get pregnant again.
                                    }
                                }
                    }
                }
                if (rabbits.get(j).getIsPregnant()) {
                    rabbits.get(j).increaseDaysOfGestation();   //If rabbit is pregnant, increase days of gestation by one.
                } else {
                    rabbits.get(j).setDaysOfGestation(0);       //If not pregnant, days of gestation is always 0.
                }
                //Method to check if rabbit is at least 100 years old, female, and days from last delivery is greater than 0, and it is not pregnant.
                //If it is all that, it sets is to pregnant on a chance of 1 in 15 each day.
                if (rabbits.get(j).getAge() >= 100 && rabbits.get(j).getSex() && rabbits.get(j).getDaysFromDelivery() >= 0 && rabbits.get(j).getIsPregnant() == false) {
                    int random = rand.nextInt(15);
                    if (random == 2) {  //random chance of 1 in 15, if the random number is = 2, it sets the rabbit to pregnant.
                        rabbits.get(j).setPregnant(true);
                        rabbits.get(j).setGestationPeriod(rand.nextInt(5) + 28);
                        rabbits.get(j).setDaysOfGestation(0);
                    }
                }
                rabbits.get(j).addAge();    //Adds age to each rabbit after each day.
            }

                for(int h = 0; h < totalRabbits; h++) {
                            litter();

                }

        }

    }

    public static int getMaleCount () {        //Method to go through every rabbit on the ArrayList and count the amount of males
        int maleCount = 0;
        for (int i = 0; i <= rabbits.size() - 1; i++ ) {
            if (!rabbits.get(i).getSex()) {
                maleCount ++;
            }
        }
        return maleCount;
    }

    public static int getFemaleCount() {       //Method to go through every rabbit and count the amount of females.
        int femaleCount = 0;
        for (int i = 0; i <= rabbits.size() - 1; i++ ) {
            if (rabbits.get(i).getSex()) {
                femaleCount ++;
            }
        }
        return femaleCount;
    }

    public static void runSimulation(int AmountOfFemales, int AmountOfMales) {
        for (int i = 0; i <= AmountOfFemales; i++) {    //adds the number of females from parameter to the ArrayList
            rabbits.add(new Rabbit(true));
        }
        for (int i = 0; i <= AmountOfMales; i++) {      //adds the number of males from parameter to the ArrayList
            rabbits.add(new Rabbit(false));
        }
        year(rabbits);  //runs the method to go trhough the period of a year on the ArrayList of rabbits inputted.
    }

    public static void finalRun(int does, int bucks) {  //Prints the results and calculates standard deviation and averages.
        int maleTotal = 0;
        int femaleTotal = 0;
        int total = 0;
        //int[] trialsFemales = new int[11];  //stores the value for the total of each trial for males
        //int[] trialsMales = new int[11];    //stores the value for each trial for females
        int deviationFemales = 0;
        int deviationMales = 0;
        double totalDeviationFemaleAdded = 0;
        double totalDeviationMaleAdded = 0;
        System.out.println("Starting with " + does +  " doe(s) and " + bucks + " buck(s)");
        for (int i = 0; i <= 10; i++) {     //runs the trial 10 times with the same input
            runSimulation(does,bucks);
            getFemaleCount();
            getMaleCount();
            System.out.println("Trial " + i + ": " + (getFemaleCount() + getMaleCount()) + " was the final population of rabbits; " + getFemaleCount() + " does, " + getMaleCount()  + " bucks.");
            maleTotal = maleTotal + getMaleCount();
            femaleTotal = femaleTotal + getFemaleCount();
            total = total + getFemaleCount() + getMaleCount();
            //trialsFemales[i] = getFemaleCount();
            //trialsMales[i] = getMaleCount();
            deviationFemales = (femaleTotal / 10) - getFemaleCount();
            deviationMales = (maleTotal / 10) - getMaleCount();
            totalDeviationFemaleAdded = Math.sqrt(Math.abs(deviationMales) + Math.abs(totalDeviationFemaleAdded) * Math.abs(deviationMales) + Math.abs(totalDeviationFemaleAdded) * 2);
            totalDeviationMaleAdded = Math.sqrt(Math.abs(deviationMales) + Math.abs(totalDeviationMaleAdded) * Math.sqrt(Math.abs(deviationMales) + Math.abs(totalDeviationMaleAdded)) * 2);
            rabbits.clear();

        }
        System.out.printf("Average number of rabbits: " + total / 10  + " with standard deviation of %.3f\n", totalDeviationMaleAdded + totalDeviationFemaleAdded);
        System.out.printf("Average number of female rabbits: " + femaleTotal / 10  + " with standard deviation of %.3f\n", totalDeviationFemaleAdded);
        System.out.printf("Average number of male rabbits: " + maleTotal / 10  + " with standard deviation of %.3f\n", totalDeviationMaleAdded);
    }

}


