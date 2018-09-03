using System;

namespace Socrates_Promo6
{
    public class DinerTime
    {
        private readonly DateTime start;
        public DateTime Start => start;


        public DinerTime(DateTime dinnerTime)
        {
            start = dinnerTime;
        }

    }
}