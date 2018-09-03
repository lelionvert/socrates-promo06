using System;
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

            int nbColdMeals = GetColdMeal(checkIn, new DinerTime(limitHour));
            Check.That(nbColdMeals).Equals(0);
        }

        [Fact]
        public void Return_one_cold_meal_when_arrival_hour_is_after_limit_hour()
        {
            DateTime limitHour = new DateTime(2018, 10, 4, 21, 0, 0);
            DateTime arrivalHour = new DateTime(2018, 10, 4, 22, 0, 0);

            int nbColdMeals = GetColdMeal(new CheckIn(arrivalHour), new DinerTime(limitHour));
            Check.That(nbColdMeals).Equals(1);
        }

        [Fact]
        public void Return_no_cold_meal_when_arrival_day_is_after_limit_day()
        {
            DateTime limitHour = new DateTime(2018, 10, 4, 21, 0, 0);
            DateTime arrivalHour = new DateTime(2018, 10, 5, 1, 0, 0);

            int nbColdMeals = GetColdMeal(new CheckIn(arrivalHour), new DinerTime(limitHour));
            Check.That(nbColdMeals).Equals(0);
        }

        [Fact]
        public void Return_no_cold_meal_when_limit_day_is_the_last_day_of_the_month_and_arrival_is_first_day_of_the_next_month()
        {
            DateTime limitHour = new DateTime(2018, 10, 31, 21, 0, 0);
            DateTime arrivalHour = new DateTime(2018, 11, 1, 1, 0, 0);

            int nbColdMeals = GetColdMeal(new CheckIn(arrivalHour), new DinerTime(limitHour));
            Check.That(nbColdMeals).Equals(0);
        }


        private int GetColdMeal(CheckIn checkin, DinerTime dinerTime)
        {
            if (checkin.IsSameDay(dinerTime.Start))
            {
                if (checkin.ArrivalTime < dinerTime.Start)
                    return 0;

                return 1;
            }

            return 0;
        }

      
    }
}
