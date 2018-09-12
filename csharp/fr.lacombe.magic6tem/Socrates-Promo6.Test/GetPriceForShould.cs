using System;
using System.Collections.Generic;
using System.Text;
using NFluent;
using Socrates_Promo6.RestaurantManager.MealModels;
using Xunit;

namespace Socrates_Promo6.Test
{
    public class GetPriceForShould

    {
        [Fact]
        public void return_610_when_participant_chooses_SinglePackage()
        {
            ParticipantReservation participantReservation = new ParticipantReservation("Single");
            PriceManager priceManager = new PriceManager();
            int result = priceManager.GetPriceFor(participantReservation);

            Check.That(result).Equals(610);
        }

        [Fact]
        public void return_510_when_participant_chooses_DoublePackage()
        {
            ParticipantReservation participantReservation = new ParticipantReservation("Double");

            PriceManager priceManager= new PriceManager();
            int result = priceManager.GetPriceFor(participantReservation);

            Check.That(result).Equals(510);
        }
        [Fact]
        public void return_410_when_participant_chooses_TriplePackage()
        {
            ParticipantReservation participantReservation = new ParticipantReservation("Triple");

            PriceManager priceManager = new PriceManager();
            int result = priceManager.GetPriceFor(participantReservation);

            Check.That(result).Equals(410);
        }

        [Fact]
        public void return_240_when_participant_chooses_noAccomodationPackage()
        {
            ParticipantReservation participantReservation = new ParticipantReservation("No Accomodation");

            PriceManager priceManager = new PriceManager();
            int result = priceManager.GetPriceFor(participantReservation);

            Check.That(result).Equals(240);
        }
    }

}
