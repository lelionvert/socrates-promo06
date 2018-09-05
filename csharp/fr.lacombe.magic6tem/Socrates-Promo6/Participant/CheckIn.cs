using System;
using System.Collections.Generic;

namespace Socrates_Promo6
{
    public class CheckIn
    {
        private readonly DateTime _arrivalTime;

        public CheckIn(DateTime arrivalTime)
        {
            _arrivalTime = arrivalTime;
        }

        private bool IsSameDay(DateTime dinnerTime)
        {
            return _arrivalTime.Date.Equals(dinnerTime.Date);
        }

        private bool IsAfter(DateTime date)
        {
            return _arrivalTime > date;
        }

        private bool IsBefore(DateTime date)
        {
            return _arrivalTime < date;
        }

        public bool IsGoingToBeLateFor(DinerTime dinerTime)
        {
            return IsSameDay(dinerTime.Start) && IsAfter(dinerTime.Start);
        }

        public bool IsGoingToBeOnTimeFor(DinerTime dinerTime)
        {
            return IsSameDay(dinerTime.Start) && IsBefore(dinerTime.Start);
        }
    }
}
