using System;
using System.Collections.Generic;
using System.Linq;
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
            Check.That(new Restaurant().GetNumberOfCoversDiet(null).Vegetarian).Equals(0);
        }
        [Fact]
        public void Return_number_of_meal_for_all_six_meals()
        {

            Check.That(new Restaurant().GetNumberOfCoversDiet(new List<Participant>() { new Participant(Diet.VEGETARIAN, new CheckIn(new DateTime(2018,10,31,20,0,0))) }).Vegetarian)
                .Equals(6);
        }
        [Fact]
        public void Return_number_of_cover_for_two_participants()
        {
            List<Participant> participants = new List<Participant>
            {
                new Participant(Diet.VEGETARIAN, new CheckIn(new DateTime(2018,10,31,20,0,0))),
                new Participant(Diet.VEGETARIAN, new CheckIn(new DateTime(2018,10,31,20,0,0)))
            };
            Check.That(new Restaurant().GetNumberOfCoversDiet(participants).Vegetarian)
                .Equals(12);
        }
        [Fact]
        public void Return_number_of_cover_by_diet()
        {
            List<Participant> participants = new List<Participant>();

            Participant participantVegetarian = new Participant(Diet.VEGETARIAN, new CheckIn(new DateTime(2018,10,31,20,0,0)));    
            Participant participantOmnivorous = new Participant(Diet.OMNIVOROUS, new CheckIn(new DateTime(2018,10,31,20,0,0)));

            participants.Add(participantVegetarian);
            participants.Add(participantOmnivorous);
           
            Covers numberOfCoversDiet = new Restaurant().GetNumberOfCoversDiet(participants);
            Check.That(numberOfCoversDiet.Vegetarian).IsEqualTo(6);
            Check.That(numberOfCoversDiet.Omnivorous).IsEqualTo(6);
        }
        [Fact]
        public void Return_eleven_omnivorous_diets_when_one_participant_is_late_but_other_one_is_not()
        {
            List<Participant> participants = new List<Participant>();
    
            Participant participantOmnivorous = new Participant(Diet.OMNIVOROUS, new CheckIn(new DateTime(2018,10,31,20,0,0)));
            Participant lateParticipantOmnivorous = new Participant(Diet.OMNIVOROUS, new CheckIn(new DateTime(2018,10,31,23,0,0)));

            participants.Add(participantOmnivorous);
            participants.Add(lateParticipantOmnivorous);

            Covers numbersOfOmnivorousCovers = new Restaurant().GetNumberOfCoversDiet(participants);

            Check.That(numbersOfOmnivorousCovers.Omnivorous).Equals(11);

        }
    }

    public class GetNumberOfCoversDietPerMealShould
    {
        [Fact]
        public void Return_one_omnivorous_cover_per_day_when_one_participant_is_not_late()
        {
            
            var arrivalDate = new CheckIn(new DateTime(2018, 10, 31, 20, 0, 0));
            var participant = new Participant(Diet.OMNIVOROUS, arrivalDate);
            var participants = new List<Participant>
                {participant};
            List<Covers> covers = new Restaurant().GetNumberOfCoversDietPerMeal(participants);
            Check.That(covers.Sum(covers1 => covers1.Omnivorous)).Equals(6);
        }

        [Fact]
        public void Return_no_covers_when_participants_is_null_or_empty ()
        {
            List<Covers> covers = new Restaurant().GetNumberOfCoversDietPerMeal(null);
            Check.That(covers).Equals(new List<Covers>());
            List<Covers> covers2 = new Restaurant().GetNumberOfCoversDietPerMeal(new List<Participant>());
            Check.That(covers2).Equals(new List<Covers>());
        }

        [Fact]
        public void Return_two_omnivorous_cover_per_day_when_two_participant_is_not_late()
        {

            var arrivalDate = new CheckIn(new DateTime(2018, 10, 31, 10, 0, 0));
            var participant = new Participant(Diet.OMNIVOROUS, arrivalDate);
            var participant2 = new Participant(Diet.OMNIVOROUS, arrivalDate);
            var participants = new List<Participant>
                {participant,participant2};
            List<Covers> covers = new Restaurant().GetNumberOfCoversDietPerMeal(participants);
            Check.That(covers.Sum(covers1 => covers1.Omnivorous)).Equals(12);
        }

        [Fact]
        public void Return_one_omnivorous_cover_the_first_day_when_one_of_the_participant_is_late()
        {

            var arrivalDate = new CheckIn(new DateTime(2018, 10, 31, 20, 0, 0));
            var lateDate = new CheckIn(new DateTime(2018, 10, 31, 23, 0, 0));
            var participant = new Participant(Diet.OMNIVOROUS, arrivalDate);
            var lateParticipant = new Participant(Diet.OMNIVOROUS, lateDate);
            var participants = new List<Participant>
                {participant, lateParticipant};
            List<Covers> covers = new Restaurant().GetNumberOfCoversDietPerMeal(participants);
            Check.That(covers.Sum(covers1 => covers1.Omnivorous)).Equals(11);
        }
    }

}
