package fr.lacombe.magic6tem.run;

import fr.lacombe.magic6tem.conference.OrganisationMeal;
import fr.lacombe.magic6tem.conference.Participant;
import fr.lacombe.magic6tem.restaurant.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to the SoCraTes software");
        System.out.println("Please enter the information for the participants.");
        List<Participant> participantList = new ArrayList<>();
        Scanner consoleInput = new Scanner(System.in);
        for (int i = 0; i < 99; i++) {
            System.out.println("Please enter information for Participant 1 :");
            int hours = -1;

            while (hours == -1) {
                System.out.println("Hour (23 to 0) = ");
                hours = consoleInput.nextInt();
                if (hours > 23 || hours < 0) {
                    hours = -1;
                    System.out.println("Please enter a valid hour !");
                }
            }
            int minutes = -1;
            while (minutes == -1) {
                System.out.println("Minutes (59 to 0) = ");
                minutes = consoleInput.nextInt();
                if (minutes > 59 || minutes < 0) {
                    minutes = -1;
                    System.out.println("Please enter a valid minute !");
                }
            }
            System.out.println("New participant added to the restaurant coming at : " + hours + "h" + minutes);
            participantList.add(Participant.ofArrivalTime(hours, minutes));
            System.out.println("Do you want to stop the input of the participants ? (true or false)");
            boolean breakBoucle = consoleInput.nextBoolean();
            if(breakBoucle) {
                break;
            }

        }
        Restaurant restaurant = new Restaurant(participantList, OrganisationMeal.getOrganisationMeal());
        System.out.println("You will need " + restaurant.getColdMeals() + " cold meals to be prepared.");
        System.out.println("Go restaurant yourself");
    }
}
