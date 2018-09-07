using System.Collections.Generic;
using System.Linq;

namespace Socrates_Promo6
{
    public class Restaurant
    {
        private IEnumerable<IMeal> _meals;

        public Restaurant(IEnumerable<IMeal> meals)
        {
            this._meals = meals;
        }

        public static int GetColdMeals(List<CheckIn> checkIns)
        {
            return checkIns.FindAll(checkIn => checkIn.IsGoingToBeLateFor(FirstMeal.DinerTime)).Count;

        }

        public IEnumerable<Covers> GetNumberOfCoversDietPerMeal(IEnumerable<Participant> participants)
        {
            return _meals.Select(meal => meal.From(participants)).ToList();
        }
    }
}

