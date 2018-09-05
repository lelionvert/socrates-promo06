using System;

namespace Socrates_Promo6
{
    public class Participant
    {
        public string Diet { get; }
        public CheckIn CheckIn { get; }

        public Participant(string diet, CheckIn checkIn)
        {
            Diet = diet;
            CheckIn = checkIn;
        }
    }
}