using System;
using System.Collections.Generic;
using System.Text;

namespace Socrates_Promo6
{
    public class Restaurant
    {
        private int _mealNumber;

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

        public int GetNumberOfCovers(Participant participant)
        {
            if (participant != null)
            {
                return _mealNumber;
            }
                
            return 0;
        }
    }
}
