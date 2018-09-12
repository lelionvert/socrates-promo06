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
    }

    

}