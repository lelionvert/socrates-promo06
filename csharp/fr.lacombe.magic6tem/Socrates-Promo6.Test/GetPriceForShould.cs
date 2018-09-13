using System;
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

        [Fact]
        public void return_610_when_participant_chooses_SinglePackage()
        {
            ParticipantReservation participantReservation = new ParticipantReservation("Single", _checkinThursday, _checkOut);
            PriceManager priceManager = new PriceManager(startDateConference, endDateConference);
            int result = priceManager.GetPriceFor(participantReservation);

            Check.That(result).Equals(610);
        }

        [Fact]
        public void return_510_when_participant_chooses_DoublePackage()
        {
            ParticipantReservation participantReservation = new ParticipantReservation("Double", _checkinThursday, _checkOut);

            PriceManager priceManager = new PriceManager(startDateConference, endDateConference);
            int result = priceManager.GetPriceFor(participantReservation);

            Check.That(result).Equals(510);
        }
        [Fact]
        public void return_410_when_participant_chooses_TriplePackage()
        {
            ParticipantReservation participantReservation = new ParticipantReservation("Triple", _checkinThursday, _checkOut);

            PriceManager priceManager = new PriceManager(startDateConference, endDateConference);
            int result = priceManager.GetPriceFor(participantReservation);

            Check.That(result).Equals(410);
        }

        [Fact]
        public void return_240_when_participant_chooses_no_Accomodation_Package()
        {
            ParticipantReservation participantReservation = new ParticipantReservation("No Accomodation", _checkinThursday, _checkOut);

            PriceManager priceManager = new PriceManager(startDateConference, endDateConference);
            int result = priceManager.GetPriceFor(participantReservation);

            Check.That(result).Equals(240);
        }

        [Fact]
        public void return_200_when_participant_chooses_no_Accomodation_Package_and_checkin_friday()
        {
            CheckIn checkinFriday = new CheckIn(new DateTime(2018, 09, 28));
            ParticipantReservation participantReservation = new ParticipantReservation("No Accomodation", checkinFriday, _checkOut);

            PriceManager priceManager = new PriceManager(startDateConference, endDateConference);
            int result = priceManager.GetPriceFor2(participantReservation);

            Check.That(result).Equals(200);
        }

        [Fact]
        public void return_200_when_participant_chooses_no_Accomodation_Package_and_checkout_saturday()
        {
            CheckIn checkinThursday = _checkinThursday;
            CheckOut checkoutSaturday = new CheckOut(new DateTime(2018, 09, 29, 20, 0, 0));
            ParticipantReservation participantReservation = new ParticipantReservation("No Accomodation", checkinThursday, checkoutSaturday);

            PriceManager priceManager = new PriceManager(startDateConference, endDateConference);
            int result = priceManager.GetPriceFor2(participantReservation);

            Check.That(result).Equals(200);
        }
        [Fact]
        public void return_160_when_participant_chooses_no_Accomodation_Package_and_checkin_friday_and_checkout_saturday()
        {
            CheckIn checkinFriday = new CheckIn(new DateTime(2018, 09, 28));
            CheckOut checkoutSaturday = new CheckOut(new DateTime(2018, 09, 29, 20, 0, 0));
            ParticipantReservation participantReservation = new ParticipantReservation("No Accomodation", checkinFriday, checkoutSaturday);

            PriceManager priceManager = new PriceManager(startDateConference, endDateConference);
            int result = priceManager.GetPriceFor2(participantReservation);

            Check.That(result).Equals(160);
        }

    }

}
