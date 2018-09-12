using System;


namespace Socrates_Promo6.ParticipantManager
{
    public class CheckIn: ICheckIn
    {
        private readonly DateTime _arrivalTime;

        public CheckIn(DateTime arrivalTime)
        {
            _arrivalTime = arrivalTime;
        }

        public bool IsSameDay(DateTime dinnerTime)
        {
            return _arrivalTime.Date.Equals(dinnerTime.Date);
        }

        public bool IsAfter(DateTime date)
        {
            return _arrivalTime > date;
        }

        public bool IsBefore(DateTime date)
        {
            return _arrivalTime < date;
        }

         public bool IsGoingToBeLateFor(MealTime mealTime)
        {
            return IsSameDay(mealTime.Start) && IsAfter(mealTime.Start);
        }

        public bool IsGoingToBeOnTimeFor(MealTime mealTime)
        {
            return IsSameDay(mealTime.Start) && IsBefore(mealTime.Start);
        }
    }

}
