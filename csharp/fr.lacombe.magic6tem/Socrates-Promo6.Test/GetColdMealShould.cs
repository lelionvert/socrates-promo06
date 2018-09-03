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

            int nbColdMeals = GetColdMeal(limitHour, arrivalHour);
            Check.That(nbColdMeals).Equals(0);
        }
        [Fact]
        public void Return_one_cold_meal_when_arrival_hour_is_after_limit_hour()
        {
            DateTime limitHour = new DateTime(2018, 10, 4, 21, 0, 0);
            DateTime arrivalHour = new DateTime(2018, 10, 4, 22, 0, 0);

            int nbColdMeals = GetColdMeal(limitHour, arrivalHour);
            Check.That(nbColdMeals).Equals(1);
        }


        private int GetColdMeal(DateTime limitHour, DateTime arrivalHour)
        {
            if (arrivalHour < limitHour)
                return 0;
            return 1;
        }
    }
}
