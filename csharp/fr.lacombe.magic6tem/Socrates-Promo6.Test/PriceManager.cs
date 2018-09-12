using System;
using System.Collections.Generic;

namespace Socrates_Promo6.Test
{
    public class PriceManager
    {
        private readonly Dictionary<String, int> choicePrice = new Dictionary<string, int>
        {
            {"Single", 610},
            {"Double", 510},
            {"Triple", 410 },
            {"No Accomodation", 240}

        };
        public int GetPriceFor(ParticipantReservation participantReservation)
        {
            return choicePrice[participantReservation.PackageChoice];
        }
        public int GetPriceFor2(ParticipantReservation participantReservation)
        {
            var price = choicePrice[participantReservation.PackageChoice];
            if (participantReservation.HasArrivedAfter(new DateTime(2018, 09, 27)))
            {
                price = price - 40;
            }

            if (participantReservation.HasLeftAfter(new DateTime(2018, 09, 30, 18, 0, 0)))
            {
                price = price - 40;
            }

            return price;
        }
    }

    

}