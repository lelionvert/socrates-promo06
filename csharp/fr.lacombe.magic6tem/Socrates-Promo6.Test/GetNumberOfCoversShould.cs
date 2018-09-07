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
        [Fact]
        public void Return_one_omnivorous_cover_per_day_when_one_participant_is_not_late()
        {
            
            var arrivalDate = new CheckIn(new DateTime(2018, 10, 31, 20, 0, 0));
            var participant = new Participant(Diet.OMNIVOROUS, arrivalDate);
            var participants = new List<Participant>
                {participant};
            IEnumerable<Covers> covers = new Restaurant().GetNumberOfCoversDietPerMeal(participants);
            Check.That(covers.Sum(covers1 => covers1.Omnivorous)).Equals(6);
        }

        [Fact]
        public void Return_no_covers_when_participants_is_null_or_empty ()
        {
            IEnumerable<Covers> covers = new Restaurant().GetNumberOfCoversDietPerMeal(null);
            Check.That(covers).Equals(new List<Covers>());
            IEnumerable<Covers> covers2 = new Restaurant().GetNumberOfCoversDietPerMeal(new List<Participant>());
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
            IEnumerable<Covers> covers = new Restaurant().GetNumberOfCoversDietPerMeal(participants);
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
            IEnumerable<Covers> covers = new Restaurant().GetNumberOfCoversDietPerMeal(participants);
            Check.That(covers.Sum(covers1 => covers1.Omnivorous)).Equals(11);
        }
    }

}
