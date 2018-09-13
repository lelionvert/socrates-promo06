using System;

namespace Socrates_Promo6.Test
{

    public abstract class PackageChoice
    {
        public  int Price { get; }

        public PackageChoice(int price)
        {
            Price = price;
        }
        
        
    }

}