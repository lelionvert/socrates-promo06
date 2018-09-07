package fr.lacombe.magic6tem.conference;

import java.util.ArrayList;
import java.util.List;

public class OrganisationMeal {

    private List<String> mealsOfTheConference;

    private OrganisationMeal() {
        initialiseMealsOfTheConference();
    }
    public static OrganisationMeal getOrganisationMeal(){
        return new OrganisationMeal();
    }
    private void initialiseMealsOfTheConference(){
        mealsOfTheConference = new
            ArrayList<>();
        mealsOfTheConference.add("Thursday Night");
        mealsOfTheConference.add("Friday Noon");
        mealsOfTheConference.add("Friday Night");
        mealsOfTheConference.add("Saturday Noon");
        mealsOfTheConference.add("Saturday Night");
        mealsOfTheConference.add("Sunday Noon");

    }

    public List<String> getMealsOfTheConference() {
        return mealsOfTheConference;
    }
}
