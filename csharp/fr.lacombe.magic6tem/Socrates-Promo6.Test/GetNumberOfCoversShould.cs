using System;
using System.Collections.Generic;
using System.Text;
using NFluent;
using Xunit;

namespace Socrates_Promo6.Test
{
    public class GetNumberOfCoversShould
    {
        [Fact]
      public void Return_zero_cover_for_zero_participant()
        {
            int numberOfCovers = new Restaurant().GetNumberOfCovers(null);
            Check.That(numberOfCovers).Equals(0);
        }

        [Fact]
        public void Return_one_cover_for_one_participant()
        {
            int numberOfCovers = new Restaurant().GetNumberOfCovers(new Participant());
            Check.That(numberOfCovers).Equals(1);
        }
        [Fact]
        public void Return_zero_covers_when_zero_meal()
        {
            int numberOfCovers = new Restaurant(0).GetNumberOfCovers(new Participant());
            Check.That(numberOfCovers).Equals(0);
        }
    }

}
