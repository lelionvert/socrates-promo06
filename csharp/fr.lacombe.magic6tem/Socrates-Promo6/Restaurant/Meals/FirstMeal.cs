using System;
using System.Collections.Generic;
using System.Linq;

namespace Socrates_Promo6
{
    public class FirstMeal : IMeal
    {
        public static readonly DinerTime DinerTime = new DinerTime(new DateTime(2018, 10, 31, 21, 0, 0));

        public Covers From(IEnumerable<Participant> participants)
        {
            return Covers.OfParticipants(participants.ToList()
                .FindAll(participant => participant.CheckIn.IsGoingToBeOnTimeFor(DinerTime)));
        }
    }
}