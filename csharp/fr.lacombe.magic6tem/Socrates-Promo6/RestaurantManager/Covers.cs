using System.Collections.Generic;
using System.Linq;

namespace Socrates_Promo6.ParticipantManager
{
    public class Covers
    {
        private readonly Dictionary<string, int> _coversByDiet;

        private Covers(Dictionary<string, int> coversByDiet)
        {
            _coversByDiet = coversByDiet;
        }

        private int GetValueOrDefault(string diet)
        {
            if (_coversByDiet.ContainsKey(diet))
                return _coversByDiet[diet];
            return 0;
        }

        public int Vegetarian => GetValueOrDefault(Diet.VEGETARIAN);
        public int Vegan => GetValueOrDefault(Diet.VEGAN);
        public int Omnivorous => GetValueOrDefault(Diet.OMNIVOROUS);
        public int Pescatarian => GetValueOrDefault(Diet.PESCATARIAN);

        public static Covers OfParticipants(IEnumerable<Participant> participants)
        {
            var countEachDiet = participants
                .GroupBy(participant => participant.Diet)
                .ToDictionary(x=>x.Key, x=> x.Count());

            return new Covers(countEachDiet);

        }
    }
}