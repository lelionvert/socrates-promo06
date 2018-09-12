using System;

namespace Socrates_Promo6.ParticipantManager
{
    public class CheckOut
    {
        private DateTime _departureTime;

        public CheckOut(DateTime departureTime)
        {
            this._departureTime = departureTime;
        }

        public bool IsAfter(DateTime date)
        {
            return _departureTime > date;
        }
    }
}