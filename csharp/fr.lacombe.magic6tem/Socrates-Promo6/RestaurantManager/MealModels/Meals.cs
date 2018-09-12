using System;
using System.Collections.Generic;
using System.Text;

namespace Socrates_Promo6.RestaurantManager.MealModels
{
    public class Meals 
    {
        private IEnumerable<IMeal> _meals;
        
        public Meals(IEnumerable<IMeal> meals)
        {
            _meals = meals;
        }


    }
}
