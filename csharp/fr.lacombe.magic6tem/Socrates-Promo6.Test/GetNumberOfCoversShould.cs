using System;
using System.Collections.Generic;
using System.Text;
using NFluent;
using Xunit;

namespace Socrates_Promo6.Test
{
    public class RestaurantTest
    {
        [Fact]
      public void toto()
        {
            int numberOfCovers = new Restaurant().GetNumberOfCovers();
            Check.That(numberOfCovers).Equals(0);
        }
    }
}
