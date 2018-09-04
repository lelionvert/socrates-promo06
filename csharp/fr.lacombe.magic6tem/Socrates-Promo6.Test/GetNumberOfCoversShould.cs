using System;
using System.Collections.Generic;
using System.Reflection.PortableExecutable;
using System.Text;
using NFluent;
using Xunit;
using Xunit.Sdk;

namespace Socrates_Promo6.Test
{
    public class GetNumberOfCoversShould
    {
        [Fact]
      public void Return_zero_cover_for_zero_participant()
        {
            List<Participant> participant = null;
            int numberOfCovers = new Restaurant().GetNumberOfCoversDiet(participant)[Diet.VEGETARIAN];
            Check.That(numberOfCovers).Equals(0);
        }

        [Fact]
        public void Return_one_cover_for_one_participant()
        {
            int numberOfCovers = new Restaurant().GetNumberOfCoversDiet(new List<Participant>(){new Participant(Diet.VEGETARIAN)})[Diet.VEGETARIAN];
            Check.That(numberOfCovers).Equals(1);
        }
        [Fact]
        public void Return_zero_covers_when_zero_meal()
        {
            int numberOfCovers = new Restaurant(0).GetNumberOfCoversDiet(new List<Participant>() { new Participant(Diet.VEGETARIAN) })[Diet.VEGETARIAN];
            Check.That(numberOfCovers).Equals(0);
        }

        [Fact]
        public void Return_number_of_meal_for_one_participant()
        {

            Check.That(new Restaurant(1).GetNumberOfCoversDiet(new List<Participant>() { new Participant(Diet.VEGETARIAN) })[Diet.VEGETARIAN])
                .Equals(1);
        }
        [Fact]
        public void Return_number_of_meal_for_all_six_meals()
        {

            Check.That(new Restaurant(6).GetNumberOfCoversDiet(new List<Participant>() { new Participant(Diet.VEGETARIAN) })[Diet.VEGETARIAN])
                .Equals(6);
        }
        [Fact]
        public void Return_number_of_meal_for_all_six_meals_without_participant()
        {
            List<Participant> participant = null;
            Check.That(new Restaurant(6).GetNumberOfCoversDiet(participant)[Diet.VEGETARIAN])
                .Equals(0);
        }
        [Fact]
        public void Return_number_of_cover_for_two_participants()
        {
            List<Participant> participants = new List<Participant>
            {
                new Participant(Diet.VEGETARIAN),
                new Participant(Diet.VEGETARIAN)
            };
            Check.That(new Restaurant(6).GetNumberOfCoversDiet(participants)[Diet.VEGETARIAN])
                .Equals(12);
        }
        [Fact]
        public void Return_number_of_cover_by_diet()
        {
            List<Participant> participants = new List<Participant>();

            Participant participantVegetarian = new Participant(Diet.VEGETARIAN);    
            Participant participantOmnivorous = new Participant(Diet.OMNIVOROUS);

            participants.Add(participantVegetarian);
            participants.Add(participantOmnivorous);

           
            var numberOfCoversDiet = new Restaurant(6).GetNumberOfCoversDiet(participants);
            Check.That(numberOfCoversDiet[Diet.VEGETARIAN]).IsEqualTo(6);
            Check.That(numberOfCoversDiet[Diet.OMNIVOROUS]).IsEqualTo(6);
        }
        //[Fact]
        public void toto()
        {
            List<Participant> participants = new List<Participant>();

            Participant participantVegetarian = new Participant(Diet.VEGETARIAN);    
            Participant participantOmnivorous = new Participant(Diet.OMNIVOROUS);

            participants.Add(participantVegetarian);
            participants.Add(participantOmnivorous);

            Dictionary<string, int> coversNumberWithDiet = new Dictionary<string, int>();

            coversNumberWithDiet.Add(Diet.VEGETARIAN,6);
            coversNumberWithDiet.Add(Diet.OMNIVOROUS,6);

            Check.That(new Restaurant(6).GetNumberOfCoversDiet(participants)).Equals(coversNumberWithDiet);

        }

        
    }

}
