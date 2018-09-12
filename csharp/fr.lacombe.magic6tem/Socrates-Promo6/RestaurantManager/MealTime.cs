using System;

namespace Socrates_Promo6.ParticipantManager
{
    public class MealTime
    {
        public DateTime Start { get; }

        public MealTime(DateTime dinnerTime)
        {
            Start = dinnerTime;
        }

    }
}