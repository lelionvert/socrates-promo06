using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Socrates_Promo6
{
    public class Restaurant
    {
        private readonly int _mealNumber;

        public Restaurant(int mealNumber)
        {
            _mealNumber = mealNumber;
        }

        public Restaurant() : this(1)
        {

        }


        public static int GetColdMeals(List<CheckIn> checkIns, DinerTime dinerTime)
        {
            int count = 0;

            foreach (var checkIn in checkIns)
            {
                if (checkIn.IsSameDay(dinerTime.Start))
                {
                    if (checkIn.IsBefore(dinerTime.Start))
                        continue;
                    count++;
                }
            }
            return count;
        }


        public Dictionary<string, int> GetNumberOfCoversDiet(List<Participant> participants)
        {

            Dictionary<string, int> coversNumberWithDiet = new Dictionary<string, int>
            {
                {Diet.VEGETARIAN,0},{Diet.VEGAN,0},{Diet.PESCATARIAN,0},{Diet.OMNIVOROUS,0}
            };
            if (participants == null)
                return coversNumberWithDiet;
            foreach (var participant in participants)
            {
                if (coversNumberWithDiet.ContainsKey(participant.GetDiet()))
                {
                    coversNumberWithDiet[participant.GetDiet()] += _mealNumber;
                }

            }

            return coversNumberWithDiet;
        }
    }
}

