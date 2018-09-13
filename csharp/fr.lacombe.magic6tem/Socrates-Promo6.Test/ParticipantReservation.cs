using System;
using Socrates_Promo6.ParticipantManager;

namespace Socrates_Promo6.Test
{
    public class ParticipantReservation
    {
        private readonly CheckOut _checkOut;
        private readonly ICheckIn _checkin;
        public string PackageChoice { get; }

        public ParticipantReservation(string packageChoice)
        {
            PackageChoice = packageChoice;

        }

        public ParticipantReservation(string choice, ICheckIn checkin) :this(choice,checkin,new CheckOut(new DateTime(2018,09,30,15,0,0)))
        {

        }

        public ParticipantReservation(string packageChoice, ICheckIn checkIn, CheckOut checkOut)
        {
            _checkOut = checkOut;
            PackageChoice = packageChoice;
            _checkin = checkIn;
        }


        public bool HasArrivedAfter(DateTime dateTime)
        {
            return _checkin.IsAfter(dateTime);
        }

        public bool HasLeftBefore(DateTime dateTime)
        {
            return _checkOut.IsBefore(dateTime);
        }
    }
}