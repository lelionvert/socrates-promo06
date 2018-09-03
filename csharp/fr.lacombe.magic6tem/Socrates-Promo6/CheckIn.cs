using System;

namespace Socrates_Promo6
{
    public class CheckIn
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

        public bool IsBefore(DateTime date)
        {
            return _arrivalTime < date;
        }
    }
}
