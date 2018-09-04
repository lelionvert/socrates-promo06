using System;
using System.Collections.Generic;
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
            int numberOfCovers = new Restaurant().GetNumberOfCovers(participant);
            Check.That(numberOfCovers).Equals(0);
        }

        [Fact]
        public void Return_one_cover_for_one_participant()
        {
            int numberOfCovers = new Restaurant().GetNumberOfCovers(new List<Participant>(){new Participant()});
            Check.That(numberOfCovers).Equals(1);
        }
        [Fact]
        public void Return_zero_covers_when_zero_meal()
        {
            int numberOfCovers = new Restaurant(0).GetNumberOfCovers(new List<Participant>() { new Participant() });
            Check.That(numberOfCovers).Equals(0);
        }

        [Fact]
        public void Return_number_of_meal_for_one_participant()
        {

            Check.That(new Restaurant(1).GetNumberOfCovers(new List<Participant>() { new Participant() }))
                .Equals(1);
        }
        [Fact]
        public void Return_number_of_meal_for_all_six_meals()
        {

            Check.That(new Restaurant(6).GetNumberOfCovers(new List<Participant>() { new Participant() }))
                .Equals(6);
        }
        [Fact]
        public void Return_number_of_meal_for_all_six_meals_without_participant()
        {
            List<Participant> participant = null;
            Check.That(new Restaurant(6).GetNumberOfCovers(participant))
                .Equals(0);
        }
        [Fact]
        public void bar()
        {
            List<Participant> participants = new List<Participant>
            {
                new Participant(),
                new Participant()
            };
            Check.That(new Restaurant(6).GetNumberOfCovers(participants))
                .Equals(12);
        }

        
    }

}
