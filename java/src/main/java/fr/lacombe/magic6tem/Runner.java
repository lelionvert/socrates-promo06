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
            while (more) {
                System.out.println("Enter hour : ");
                hour = scanner.nextLine();
                System.out.println("Enter minute : ");
                minutes = scanner.nextLine();
                checkIns.add(CheckIn.of(LocalDateTime.of(2018, 10, 25, Integer.valueOf(hour), Integer.valueOf(minutes))));

                System.out.println("Do you want some more ? Y / N");
                more = scanner.nextLine().equals("Y");
            }
        }
        Meal meal = new Meal(checkIns);
        checkIns.forEach(System.out::println);
        System.out.println("Number of Cold Meal : " + meal.getCountOfColdMeal());
    }

}
