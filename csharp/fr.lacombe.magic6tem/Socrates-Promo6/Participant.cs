namespace Socrates_Promo6
{
    public class Participant
    {
        private readonly string _diet;

        public Participant(string diet)
        {
            _diet = diet;
        }

        public string GetDiet()
        {
            return this._diet;
        }
    }
}