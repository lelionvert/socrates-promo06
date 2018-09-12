using Socrates_Promo6.ParticipantManager;
using System;

namespace Socrates_Promo6_Runner
{
    class Program
    {
        static void Main(string[] args)
        {
            System.Collections.Generic.List<CheckIn> checkIns = new System.Collections.Generic.List<CheckIn>();
            bool more = true;
            String hour;
            String minutes;
            while (more)
            {
                Console.WriteLine("Enter hour : ");
                hour = Console.ReadLine();
                Console.WriteLine("Enter minutes :");
                minutes = Console.ReadLine();
                checkIns.Add(new CheckIn(new DateTime(2018, 10, 25, Convert.ToInt32(hour), Convert.ToInt32(minutes), 0)));

                Console.WriteLine("Do you cant some more ? Y / N ");
                more = Console.ReadLine() == "Y";
            }
        /*   var coldMeals = Restaurant.GetColdMeals(checkIns, new DinerTime(new DateTime(2018, 10, 25, 21, 0, 0)));
            Console.WriteLine("Number of cold meals : " + coldMeals);
            Console.ReadLine(); */
        }
    }
}
