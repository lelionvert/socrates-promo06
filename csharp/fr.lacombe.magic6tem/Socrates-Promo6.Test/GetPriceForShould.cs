using System;
using NFluent;
using Socrates_Promo6.ParticipantManager;
using Xunit;

namespace Socrates_Promo6.Test
{
    public class GetPriceForShould

    {
        [Fact]
        public void return_610_when_participant_chooses_SinglePackage()
        {
            ParticipantReservation participantReservation = new ParticipantReservation("Single", new CheckIn(new DateTime(2018, 09, 27)), new CheckOut(new DateTime(2018, 09, 30, 15, 0, 0)));
            PriceManager priceManager = new PriceManager();
            int result = priceManager.GetPriceFor(participantReservation);

            Check.That(result).Equals(610);
        }

        [Fact]
        public void return_510_when_participant_chooses_DoublePackage()
        {
            ParticipantReservation participantReservation = new ParticipantReservation("Double", new CheckIn(new DateTime(2018, 09, 27)), new CheckOut(new DateTime(2018, 09, 30, 15, 0, 0)));

            PriceManager priceManager= new PriceManager();
            int result = priceManager.GetPriceFor(participantReservation);

            Check.That(result).Equals(510);
        }
        [Fact]
        public void return_410_when_participant_chooses_TriplePackage()
        {
            ParticipantReservation participantReservation = new ParticipantReservation("Triple", new CheckIn(new DateTime(2018, 09, 27)), new CheckOut(new DateTime(2018, 09, 30, 15, 0, 0)));

            PriceManager priceManager = new PriceManager();
            int result = priceManager.GetPriceFor(participantReservation);

            Check.That(result).Equals(410);
        }

        [Fact]
        public void return_240_when_participant_chooses_no_Accomodation_Package()
        {
            ParticipantReservation participantReservation = new ParticipantReservation("No Accomodation", new CheckIn(new DateTime(2018, 09, 27)), new CheckOut(new DateTime(2018, 09, 30, 15, 0, 0)));

            PriceManager priceManager = new PriceManager();
            int result = priceManager.GetPriceFor(participantReservation);

            Check.That(result).Equals(240);
        }

        [Fact]
        public void return_200_when_participant_chooses_no_Accomodation_Package_and_checkin_friday()
        {
            CheckIn checkinFriday = new CheckIn(new DateTime(2018, 09, 28));
            ParticipantReservation participantReservation = new ParticipantReservation("No Accomodation", checkinFriday, new CheckOut(new DateTime(2018, 09, 30, 15, 0, 0)));

            PriceManager priceManager = new PriceManager();
            int result = priceManager.GetPriceFor2(participantReservation);

            Check.That(result).Equals(200);
        }

        [Fact]
        public void return_200_when_participant_chooses_no_Accomodation_Package_and_checkout_saturday()
        {
            CheckIn checkinThursday = new CheckIn(new DateTime(2018, 09, 27));
            CheckOut checkoutSaturday = new CheckOut(new DateTime(2018, 09, 29, 20, 0, 0));
            ParticipantReservation participantReservation = new ParticipantReservation("No Accomodation", checkinThursday, checkoutSaturday);

            PriceManager priceManager = new PriceManager();
            int result = priceManager.GetPriceFor2(participantReservation);

            Check.That(result).Equals(200);
        }
        [Fact]
        public void return_160_when_participant_chooses_no_Accomodation_Package_and_checkin_friday_and_checkout_saturday()
        {
            CheckIn checkinFriday = new CheckIn(new DateTime(2018, 09, 28));
            CheckOut checkoutSaturday = new CheckOut(new DateTime(2018, 09, 29, 20, 0, 0));
            ParticipantReservation participantReservation = new ParticipantReservation("No Accomodation", checkinFriday, checkoutSaturday);

            PriceManager priceManager = new PriceManager(new DateTime(2018, 09, 27), new DateTime(2018, 09, 30, 19, 0, 0));
            int result = priceManager.GetPriceFor2(participantReservation);

            Check.That(result).Equals(160);
        }

    }

}
