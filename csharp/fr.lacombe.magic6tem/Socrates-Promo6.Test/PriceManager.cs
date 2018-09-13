using System;
using System.Linq;

namespace Socrates_Promo6.Test
{
    public class PriceManager
    {
        private readonly DateTime _limitDateTimeForLastMeal;
        private readonly DateTime _limitDateTimeForFirstMeal;

        public PriceManager(DateTime limitDateTimeForFirstMeal, DateTime limitDateTimeForLastMeal)
        {
            _limitDateTimeForLastMeal = limitDateTimeForLastMeal;
            _limitDateTimeForFirstMeal = limitDateTimeForFirstMeal;
        }

        public int GetPriceFor(ParticipantReservation participantReservation)
        {
            var price = participantReservation.PackageChoice2.Price;
            if (participantReservation.HasArrivedAfter(this._limitDateTimeForFirstMeal))
            {
                price = price - 40;
            }

            if (participantReservation.HasLeftBefore(this._limitDateTimeForLastMeal))
            {
                price = price - 40;
            }

            return price;
        }
    }

    

}