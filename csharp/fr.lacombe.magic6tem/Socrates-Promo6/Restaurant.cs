using System;
using System.Collections.Generic;
using System.Text;

namespace Socrates_Promo6
{
    public class Restaurant
    {
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
                return 1;
            return 0;
        }
    }
}
