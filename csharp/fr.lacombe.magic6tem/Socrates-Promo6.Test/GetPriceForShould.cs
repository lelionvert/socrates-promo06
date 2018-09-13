using System;
using System.Collections.Generic;
using NFluent;
using Socrates_Promo6.ParticipantManager;
using Xunit;

namespace Socrates_Promo6.Test
{
    public class GetPriceForShould

    {
        private static CheckIn _checkinThursday = new CheckIn(new DateTime(2018, 09, 27));
        private static CheckOut _checkOut = new CheckOut(new DateTime(2018, 09, 30, 15, 0, 0));
        private static readonly DateTime startDateConference = new DateTime(2018, 09, 27);
        private static readonly DateTime endDateConference = new DateTime(2018, 09, 30, 14, 0, 0);
        private readonly Dictionary<String, int> choicePrice = new Dictionary<string, int>
        
        {
            {"Single", 610},
            {"Double", 510},
            {"Triple", 410 },
            {"No Accomodation", 240}

        };

        [Fact]
        public void return_610_when_participant_chooses_SinglePackage()
        {
            ParticipantReservation participantReservation = new ParticipantReservation(_checkinThursday, _checkOut, new SinglePackage());
            PriceManager priceManager = new PriceManager(startDateConference, endDateConference);
            int result = priceManager.GetPriceFor(participantReservation);

            Check.That(result).Equals(610);
        }

        [Fact]
        public void return_510_when_participant_chooses_DoublePackage()
        {
            ParticipantReservation participantReservation = new ParticipantReservation(_checkinThursday, _checkOut, new DoublePackage());

            PriceManager priceManager = new PriceManager(startDateConference, endDateConference);
            int result = priceManager.GetPriceFor(participantReservation);

            Check.That(result).Equals(510);
        }
        [Fact]
        public void return_410_when_participant_chooses_TriplePackage()
        {
            ParticipantReservation participantReservation = new ParticipantReservation(_checkinThursday, _checkOut, new TriplePackage());

            PriceManager priceManager = new PriceManager(startDateConference, endDateConference);
            int result = priceManager.GetPriceFor(participantReservation);

            Check.That(result).Equals(410);
        }

        [Fact]
        public void return_240_when_participant_chooses_no_Accomodation_Package()
        {
            ParticipantReservation participantReservation = new ParticipantReservation(_checkinThursday, _checkOut, new NoAccomodation());

            PriceManager priceManager = new PriceManager(startDateConference, endDateConference);
            int result = priceManager.GetPriceFor(participantReservation);

            Check.That(result).Equals(240);
        }

        [Fact]
        public void return_200_when_participant_chooses_no_Accomodation_Package_and_checkin_friday()
        {
            CheckIn checkinFriday = new CheckIn(new DateTime(2018, 09, 28));
            ParticipantReservation participantReservation = new ParticipantReservation(checkinFriday, _checkOut, new NoAccomodation());

            PriceManager priceManager = new PriceManager(startDateConference, endDateConference);
            int result = priceManager.GetPriceFor(participantReservation);

            Check.That(result).Equals(200);
        }

        [Fact]
        public void return_200_when_participant_chooses_no_Accomodation_Package_and_checkout_saturday()
        {
            CheckIn checkinThursday = _checkinThursday;
            CheckOut checkoutSaturday = new CheckOut(new DateTime(2018, 09, 29, 20, 0, 0));
            ParticipantReservation participantReservation = new ParticipantReservation(checkinThursday, checkoutSaturday, new NoAccomodation());

            PriceManager priceManager = new PriceManager(startDateConference, endDateConference);
            int result = priceManager.GetPriceFor(participantReservation);

            Check.That(result).Equals(200);
        }
        [Fact]
        public void return_160_when_participant_chooses_no_Accomodation_Package_and_checkin_friday_and_checkout_saturday()
        {
            CheckIn checkinFriday = new CheckIn(new DateTime(2018, 09, 28));
            CheckOut checkoutSaturday = new CheckOut(new DateTime(2018, 09, 29, 20, 0, 0));
            ParticipantReservation participantReservation = new ParticipantReservation(checkinFriday, checkoutSaturday, new NoAccomodation());

            PriceManager priceManager = new PriceManager(startDateConference, endDateConference);
            int result = priceManager.GetPriceFor(participantReservation);

            Check.That(result).Equals(160);
        }

    }

}
