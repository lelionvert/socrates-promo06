using System;

namespace Socrates_Promo6.ParticipantManager
{
    public interface ICheckIn
    {
        bool IsSameDay(DateTime date);
        bool IsAfter(DateTime date);
        bool IsBefore(DateTime date);


    }
}