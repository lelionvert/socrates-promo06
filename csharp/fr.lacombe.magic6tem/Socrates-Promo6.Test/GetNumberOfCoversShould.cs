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
    public class GetNumberOfCoversDietPerMealShould
    {
        private static readonly CheckIn ON_TIME_DATE = new CheckIn(new DateTime(2018, 10, 31, 20, 0, 0));
        private static readonly Participant PARTICIPANT = new Participant(Diet.OMNIVOROUS, ON_TIME_DATE);
        private static readonly CheckIn LATE_DATE = new CheckIn(new DateTime(2018, 10, 31, 23, 0, 0));
        private static readonly Participant LATE_PARTICIPANT = new Participant(Diet.OMNIVOROUS, LATE_DATE);
        private static readonly List<IMeal> MEALS = new List<IMeal>
        {
            new FirstMeal(),
            new StandardMeal(),
            new StandardMeal(),
            new StandardMeal(),
            new StandardMeal(),
            new StandardMeal()
        };
        private static readonly Restaurant RESTAURANT = new Restaurant(MEALS);
        [Fact]
        public void Return_one_omnivorous_cover_per_day_when_one_participant_is_not_late()
        {
            var participants = new List<Participant>
                {PARTICIPANT};
            IEnumerable<Covers> covers = RESTAURANT.GetNumberOfCoversDietPerMeal(participants);
            Check.That(covers.Sum(cover => cover.Omnivorous)).Equals(6);
        }

        [Fact]
        public void Return_two_omnivorous_cover_per_day_when_two_participant_is_not_late()
        {
            var participants = new List<Participant>
                {PARTICIPANT, PARTICIPANT};
            IEnumerable<Covers> covers = RESTAURANT.GetNumberOfCoversDietPerMeal(participants);
            Check.That(covers.Sum(cover => cover.Omnivorous)).Equals(12);
        }

        [Fact]
        public void Return_one_omnivorous_cover_the_first_day_when_one_of_the_participant_is_late()
        {
            var participants = new List<Participant>
                {PARTICIPANT, LATE_PARTICIPANT};
            IEnumerable<Covers> covers = RESTAURANT.GetNumberOfCoversDietPerMeal(participants);
            Check.That(covers.Sum(cover => cover.Omnivorous)).Equals(11);
        }

        [Fact]
        public void Return_one_of_each_diet_cover_per_day_when_one_participant_is_not_late()
        {
            var participantVg = new Participant(Diet.VEGETARIAN, ON_TIME_DATE);
            var participantVn = new Participant(Diet.VEGAN, ON_TIME_DATE);
            var participantO = new Participant(Diet.OMNIVOROUS, ON_TIME_DATE);
            var participantP = new Participant(Diet.PESCATARIAN, ON_TIME_DATE);
            var participants = new List<Participant>
                {participantVg, participantVn, participantO, participantP};

            IEnumerable<Covers> covers = RESTAURANT.GetNumberOfCoversDietPerMeal(participants);

            Check.That(covers.Sum(cover => cover.Vegetarian)).Equals(6);
            Check.That(covers.Sum(cover => cover.Vegan)).Equals(6);
            Check.That(covers.Sum(cover => cover.Omnivorous)).Equals(6);
            Check.That(covers.Sum(cover => cover.Pescatarian)).Equals(6);
        }

        [Fact]
        public void Return_one_of_each_diet_cover_for_one_meal_when_participants_are_not_late()
        {
            IMeal meal = new StandardMeal();
            var participantVg = new Participant(Diet.VEGETARIAN, ON_TIME_DATE);
            var participantVn = new Participant(Diet.VEGAN, ON_TIME_DATE);
            var participantO = new Participant(Diet.OMNIVOROUS, ON_TIME_DATE);
            var participantP = new Participant(Diet.PESCATARIAN, ON_TIME_DATE);
            var participants = new List<Participant>
                {participantVg, participantVn, participantO, participantP};

            IEnumerable<Covers> covers =
                new Restaurant(new List<IMeal> {meal}).GetNumberOfCoversDietPerMeal(participants);

            Check.That(covers.Sum(cover => cover.Vegetarian)).Equals(1);
            Check.That(covers.Sum(cover => cover.Vegan)).Equals(1);
            Check.That(covers.Sum(cover => cover.Omnivorous)).Equals(1);
            Check.That(covers.Sum(cover => cover.Pescatarian)).Equals(1);
        }
    }
}