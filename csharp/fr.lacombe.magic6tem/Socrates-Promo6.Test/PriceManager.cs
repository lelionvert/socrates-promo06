using System;
using System.Collections.Generic;

namespace Socrates_Promo6.Test
{
    public class PriceManager
    {
        private readonly DateTime _limitDateTimeForLastMeal;
        private readonly DateTime _limitDateTimeForFirstMeal;

        private readonly Dictionary<String, int> choicePrice = new Dictionary<string, int>
        {
            {"Single", 610},
            {"Double", 510},
            {"Triple", 410 },
            {"No Accomodation", 240}

        };

        public PriceManager():this(new DateTime(2018, 09, 27), new DateTime(2018, 09, 30, 14, 0, 0))
        {
            
        }

        public PriceManager(DateTime limitDateTimeForFirstMeal, DateTime limitDateTimeForLastMeal)
        {
            _limitDateTimeForLastMeal = limitDateTimeForLastMeal;
            _limitDateTimeForFirstMeal = limitDateTimeForFirstMeal;
        }

        public int GetPriceFor(ParticipantReservation participantReservation)
        {
            return choicePrice[participantReservation.PackageChoice];
        }

        public int GetPriceFor2(ParticipantReservation participantReservation)
        {
            var price = choicePrice[participantReservation.PackageChoice];
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