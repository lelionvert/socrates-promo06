using System;
using System.Collections.Generic;
using System.Linq;

namespace Socrates_Promo6.Test
{
    public class PriceManager
    {
        private readonly DateTime _limitDateTimeForLastMeal;
        private readonly DateTime _limitDateTimeForFirstMeal;

        private readonly Dictionary<String, int> _choicePrice;
        private readonly IList<PackageChoice> _packageChoices;

        public PriceManager(DateTime limitDateTimeForFirstMeal, DateTime limitDateTimeForLastMeal, Dictionary<string, int> choicePrice, IList<PackageChoice> packageChoices = null)
        {
            _limitDateTimeForLastMeal = limitDateTimeForLastMeal;
            _limitDateTimeForFirstMeal = limitDateTimeForFirstMeal;
            _choicePrice = choicePrice;
            _packageChoices = packageChoices;
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