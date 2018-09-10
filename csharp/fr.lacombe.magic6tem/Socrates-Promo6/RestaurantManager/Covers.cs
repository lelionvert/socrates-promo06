using System.Collections.Generic;
using System.Linq;

namespace Socrates_Promo6
{
    public class Covers
    {
        private readonly Dictionary<string, int> _coversByDiet;

        private Covers(Dictionary<string, int> coversByDiet)
        {
            _coversByDiet = coversByDiet;
        }

        public int Vegetarian => _coversByDiet[Diet.VEGETARIAN];
        public int Vegan => _coversByDiet[Diet.VEGAN];
        public int Omnivorous => _coversByDiet[Diet.OMNIVOROUS];
        public int Pescatarian => _coversByDiet[Diet.PESCATARIAN];

        public static Covers OfParticipants(IEnumerable<Participant> participants)
        {
            var countEachDiet = participants
                .GroupBy(participant => participant.Diet)
                .ToDictionary(x=>x.Key, x=> x.Count());

            return new Covers(countEachDiet);

        }
    }
}