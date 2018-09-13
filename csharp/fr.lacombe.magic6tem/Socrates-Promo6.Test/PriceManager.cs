using System;
using System.Collections.Generic;

namespace Socrates_Promo6.Test
{
    public class PriceManager
    {
        private readonly DateTime _limitDateTimeForLastMeal;
        private readonly DateTime _limitDateTimeForFirstMeal;

        private readonly Dictionary<String, int> _choicePrice;

        public PriceManager(DateTime limitDateTimeForFirstMeal, DateTime limitDateTimeForLastMeal, Dictionary<string, int> choicePrice)
        {
            _limitDateTimeForLastMeal = limitDateTimeForLastMeal;
            _limitDateTimeForFirstMeal = limitDateTimeForFirstMeal;
            _choicePrice = choicePrice;
            
        }

        public int GetPriceFor(ParticipantReservation participantReservation)
        {
            var price = _choicePrice[participantReservation.PackageChoice];
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