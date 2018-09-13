using System.Collections.Generic;
using System.Linq;
using Socrates_Promo6.ParticipantManager;

namespace Socrates_Promo6
{
    public class Restaurant
    {
        private IEnumerable<IMeal> _meals;

        public Restaurant(IEnumerable<IMeal> meals)
        {
            _meals = meals;
        }

        public static int GetColdMeals(List<CheckIn> checkIns)
        {
            return checkIns.FindAll(checkIn => checkIn.IsGoingToBeLateFor(FirstMeal.DinerTime)).Count;
        }

        public IEnumerable<Covers> GetNumberOfCoversDietPerMeal(IEnumerable<Participant> participants)
        {
            return _meals.Select(meal => meal.From(participants)).ToList();
        }

        public Dictionary<string,int> GetNumberOfCoversDietForAllEvent(List<Participant> participants)
        {
            var covers= GetNumberOfCoversDietPerMeal(participants);
            Dictionary<string,int> coverByDiet = new Dictionary<string, int>
            {
                {Diet.OMNIVOROUS, 0},
                {Diet.PESCATARIAN, 0},
                {Diet.VEGAN, 0},
                {Diet.VEGETARIAN, 0}
            };
            foreach (var cover in covers)
            {
                coverByDiet[Diet.PESCATARIAN] += cover.Pescatarian;
                coverByDiet[Diet.VEGAN] += cover.Vegan;
                coverByDiet[Diet.OMNIVOROUS] += cover.Omnivorous;
                coverByDiet[Diet.VEGETARIAN] += cover.Vegetarian;
            }

            return coverByDiet;

        }
    }
}

