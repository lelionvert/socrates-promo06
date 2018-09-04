package fr.lacombe.magic6tem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Runner {

    public static void main(String[] args) {
        List<CheckIn> checkIns = new ArrayList<>();
        boolean more = true;
        String hour;
        String minutes;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("What do you want ? ");
            System.out.println("1 --> Get Cold Meal Given multiple Checkin Hour. ");
            System.out.println("2 --> Get Covers By Diet Given Participant. ");
            System.out.println("");

            switch (scanner.nextLine()) {
                case "1":
                    while (more) {
                        System.out.println("Enter hour : ");
                        hour = scanner.nextLine();
                        System.out.println("Enter minute : ");
                        minutes = scanner.nextLine();
                        checkIns.add(CheckIn.of(LocalDateTime.of(2018, 10, 25, Integer.valueOf(hour), Integer.valueOf(minutes))));

                        System.out.println("Do you want some more ? Y / N");
                        more = scanner.nextLine().equals("Y");

                    }
                    Restaurant restaurant = new Restaurant(checkIns);
                    checkIns.forEach(System.out::println);
                    System.out.println("Number of Cold Restaurant : " + restaurant.getCountOfColdMeal());
                    break;
                case "2":
                    List<Participant> participants = new ArrayList<>();
                    while (more) {
                        System.out.println("Choose Diet  : ");
                        System.out.println("\t 1 --> VEGETARIAN ");
                        System.out.println("\t 2 --> VEGAN ");
                        System.out.println("\t 3 --> PESCARIAN ");
                        System.out.println("\t 4 --> OMNIVORE ");
                        System.out.println("");
                        String dietString = scanner.nextLine();
                        Diet diet = Diet.OMNIVORE;
                        Participant participant;
                        if (dietString.equals("1")) {
                            diet = Diet.VEGETARIAN;
                        }
                        if (dietString.equals("2")) {
                            diet = Diet.VEGAN;
                        }
                        if (dietString.equals("3")) {
                            diet = Diet.PESCARIAN;
                        }
                        if (dietString.equals("4")) {
                            diet = Diet.OMNIVORE;
                        }
                        System.out.println("Participant is Late for Thursday ? Y / N");
                        if(scanner.nextLine().equals("Y")){
                            CheckIn checkIn = CheckIn.of(LocalDateTime.of(2018,10,25,22,0));
                            participant = new Participant(diet,checkIn);
                            participants.add(participant);

                        } else {
                            participant = new Participant(diet);
                            participants.add(participant);
                        }
                        System.out.println("Do you want some more ? Y / N");
                        more = scanner.nextLine().equals("Y");
                    }
                    restaurant = new Restaurant();
                    System.out.println(restaurant.coversFor(participants,6));
                    break;
            }
        }

    }

}
