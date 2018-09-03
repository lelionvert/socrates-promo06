using System;

namespace Socrates_Promo6
{
    public class CheckIn
    {
        private readonly DateTime _arrivalTime;
        public DateTime ArrivalTime => _arrivalTime;


        public CheckIn(DateTime arrivalTime)
        {
            _arrivalTime = arrivalTime;
        }

        public bool IsSameDay(DateTime dinnerTime)
        {
            return ArrivalTime.Date.Equals(dinnerTime.Date);
        }
    }
}
