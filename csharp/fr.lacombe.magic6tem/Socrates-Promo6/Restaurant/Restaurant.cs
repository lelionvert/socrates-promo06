using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Socrates_Promo6
{
    public class Restaurant
    {
        private const int MEAL_NUMBER = 6;
        private static readonly DinerTime dinerTime = new DinerTime(new DateTime(2018, 10, 31, 21, 0, 0));

        public static int GetColdMeals(List<CheckIn> checkIns)
        {
            return checkIns.FindAll(checkIn => checkIn.IsGoingToBeLateFor(dinerTime)).Count;
        }

        public Covers GetNumberOfCoversDiet(List<Participant> participants)
        {
            if (participants == null)
            {
                return Covers.OfParticipants(new List<Participant>(),MEAL_NUMBER,dinerTime);
            }
            return Covers.OfParticipants(participants, MEAL_NUMBER, dinerTime);
        }

        public List<Covers> GetNumberOfCoversDietPerMeal(List<Participant> participants)
        {
            List<Covers> coversPerMeal = new List<Covers>();
            if (participants == null || participants.Count == 0)
                return coversPerMeal;

            for (int meal = 1; meal <= MEAL_NUMBER; meal++)
            {

                if (meal == 1)
                {
                    coversPerMeal.Add(Covers.OfParticipants2(participants
                        .FindAll(participant => participant.CheckIn.IsGoingToBeOnTimeFor(dinerTime))));
                }
                else
                {
                    coversPerMeal.Add(Covers.OfParticipants2(participants));
                }

            }
            return coversPerMeal;

        }
    }
}

