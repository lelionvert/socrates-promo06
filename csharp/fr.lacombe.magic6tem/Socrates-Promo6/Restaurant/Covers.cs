using System.Collections.Generic;

namespace Socrates_Promo6
{
    public class Covers
    {
        private Dictionary<string, int> coversByDiet;

        private Covers()
        {
            coversByDiet = new Dictionary<string, int>
            {
                {"VEGETARIAN",0},
                {"VEGAN",0},
                {"OMNIVOROUS",0},
                {"PESCATARIAN",0}
            };
        }

        public int Vegetarian => coversByDiet["VEGETARIAN"];
        public int Vegan => coversByDiet["VEGAN"];
        public int Omnivorous => coversByDiet["OMNIVOROUS"];
        public int Pescatarian => coversByDiet["PESCATARIAN"];

        public static Covers OfParticipants(IEnumerable<Participant> participants, int meals, DinerTime dinerTime)
        {
            Covers covers = new Covers();
            foreach (Participant participant in participants)
            {
                var mealPerParticipant = meals;
                if (participant.CheckIn.IsGoingToBeLateFor(dinerTime))
                        mealPerParticipant -= 1;
                covers.coversByDiet[participant.Diet] += mealPerParticipant;
            }
            return covers;
        }

        public static Covers OfParticipants2(List<Participant> participants)
        {
            Covers covers = new Covers();
            foreach (Participant participant in participants)
            {
                covers.coversByDiet[participant.Diet] +=1;
            }
            return covers;

        }
    }
}