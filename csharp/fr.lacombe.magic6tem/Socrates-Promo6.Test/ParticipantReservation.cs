namespace Socrates_Promo6.Test
{
    public class ParticipantReservation
    {
        public string PackageChoice { get; }
        public ParticipantReservation(string packageChoice)
        {
            PackageChoice = packageChoice;
        }

        public bool HasChoosen(string choiceKey)
        {
            return choiceKey.Equals(PackageChoice);
        }
    }
}