using System;
using Socrates_Promo6.ParticipantManager;

namespace Socrates_Promo6.Test
{
    public class ParticipantReservation
    {
        private readonly CheckOut _checkOut;
        private readonly ICheckIn _checkin;
        public PackageChoice PackageChoice2 { get; }

        public ParticipantReservation(ICheckIn checkIn, CheckOut checkOut, PackageChoice packageChoice2)
        {
            _checkOut = checkOut;
            PackageChoice2 = packageChoice2;
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