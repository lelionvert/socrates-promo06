using System.Collections.Generic;

namespace Socrates_Promo6.ParticipantManager
{
    public class StandardMeal : IMeal
    {
        public Covers From(IEnumerable<Participant> participants)
        {
            return Covers.OfParticipants(participants);
        }
    }
}