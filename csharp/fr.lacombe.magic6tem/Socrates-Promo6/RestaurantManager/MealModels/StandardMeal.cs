using System.Collections.Generic;

namespace Socrates_Promo6
{
    public class StandardMeal : IMeal
    {
        public Covers From(IEnumerable<Participant> participants)
        {
            return Covers.OfParticipants(participants);
        }
    }
}