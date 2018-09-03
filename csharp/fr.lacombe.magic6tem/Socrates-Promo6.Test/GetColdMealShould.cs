using System;
using System.Collections.Generic;
using System.ComponentModel.Design;
using NFluent;
using Xunit;


namespace Socrates_Promo6.Test
{
    public class GetColdMealShould
    {
        [Fact]
        public void Return_zero_cold_meal_when_arrival_hour_is_before_limit_hour()
        {
            DateTime limitHour = new DateTime(2018, 10, 4, 21, 0, 0);
            DateTime arrivalHour = new DateTime(2018, 10, 4, 20, 0, 0);

            var checkIn = new CheckIn(arrivalHour);
            var limitTime = new DinerTime(limitHour);

            List<CheckIn> checkIns = new List<CheckIn>()
            {
                checkIn  
            };
            int nbColdMeals = Restaurant.GetColdMeals(checkIns, new DinerTime(limitHour));
            Check.That(nbColdMeals).Equals(0);
        }

        [Fact]
        public void Return_one_cold_meal_when_arrival_hour_is_after_limit_hour()
        {
            DateTime limitHour = new DateTime(2018, 10, 4, 21, 0, 0);
            DateTime arrivalHour = new DateTime(2018, 10, 4, 22, 0, 0);
            

            List<CheckIn> checkIns = new List<CheckIn>()
            {
                new CheckIn(arrivalHour)  
            };
            int nbColdMeals = Restaurant.GetColdMeals(checkIns, new DinerTime(limitHour));
            Check.That(nbColdMeals).Equals(1);
        }

        [Fact]
        public void Return_no_cold_meal_when_arrival_day_is_after_limit_day()
        {
            DateTime limitHour = new DateTime(2018, 10, 4, 21, 0, 0);
            DateTime arrivalHour = new DateTime(2018, 10, 5, 1, 0, 0);

            List<CheckIn> checkIns = new List<CheckIn>()
            {
                new CheckIn(arrivalHour)  
            };
            int nbColdMeals = Restaurant.GetColdMeals(checkIns, new DinerTime(limitHour));
            Check.That(nbColdMeals).Equals(0);
        }

        [Fact]
        public void Return_no_cold_meal_when_limit_day_is_the_last_day_of_the_month_and_arrival_is_first_day_of_the_next_month()
        {
            DateTime limitHour = new DateTime(2018, 10, 31, 21, 0, 0);
            DateTime arrivalHour = new DateTime(2018, 11, 1, 1, 0, 0);

            List<CheckIn> checkIns = new List<CheckIn>()
            {
                new CheckIn(arrivalHour)  
            };
            int nbColdMeals = Restaurant.GetColdMeals(checkIns, new DinerTime(limitHour));
            Check.That(nbColdMeals).Equals(0);
        }

        [Fact]
        public void Return_no_cold_meal_when_all_checkIns_arrive_before_limit_hour()
        {
            DateTime limitHour = new DateTime(2018, 10, 31, 21, 0, 0);

            DateTime firstArrivalHour = new DateTime(2018, 10, 31, 20, 0, 0);
            DateTime secondArrivalHour = new DateTime(2018, 10, 31, 20, 30, 0);
            List<CheckIn> checkIns = new List<CheckIn>
            {
                new CheckIn(firstArrivalHour),
                new CheckIn(secondArrivalHour)

            };

            int nbColdMeals = Restaurant.GetColdMeals(checkIns, new DinerTime(limitHour));
            Check.That(nbColdMeals).Equals(0);
        }

        [Fact]
        public void Return_two_cold_meals_when_two_checkIns_arrive_after_limit_hour()
        {
            DateTime limitHour = new DateTime(2018, 10, 31, 21, 0, 0);

            DateTime firstArrivalHour = new DateTime(2018, 10, 31, 20, 0, 0);
            DateTime secondArrivalHour = new DateTime(2018, 10, 31, 22, 30, 0);
            DateTime thirdArrivalHour = new DateTime(2018, 10, 31, 23, 00, 0);
            List<CheckIn> checkIns = new List<CheckIn>
            {
                new CheckIn(firstArrivalHour),
                new CheckIn(secondArrivalHour),
                new CheckIn(thirdArrivalHour)

            };

            int nbColdMeals = Restaurant.GetColdMeals(checkIns, new DinerTime(limitHour));
            Check.That(nbColdMeals).Equals(2);
        }


    }
}
