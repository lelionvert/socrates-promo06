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


        public int GetNumberOfCovers(List<Participant> participants)
        {
            if (participants == null)
            {
                return 0;
            }
            int numberOfCovers = 0;
            foreach (var participant in participants)
            {
                numberOfCovers += _mealNumber;
            }

           return participants.Count * _mealNumber;
            return numberOfCovers;
           
        }
    }
}
