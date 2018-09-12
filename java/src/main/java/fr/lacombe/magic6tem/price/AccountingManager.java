package fr.lacombe.magic6tem.price;

import java.time.LocalDateTime;
import java.util.Map;

class AccountingManager {

    private Map<PackageChoice, Price> packageChoicePriceMap;
    private LocalDateTime limitDateTimeOfFirstMeal;
    private LocalDateTime limitDateTimeOfLastMeal;
    private int priceValueOfOneMeal;

    public AccountingManager(Map<PackageChoice, Price> packageChoicePriceMap, LocalDateTime limitDateTimeOfFirstMeal, LocalDateTime limitDateTimeOfLastMeal, int priceValueOfOneMeal) {
        this.packageChoicePriceMap = packageChoicePriceMap;
        this.limitDateTimeOfFirstMeal = limitDateTimeOfFirstMeal;
        this.limitDateTimeOfLastMeal = limitDateTimeOfLastMeal;
        this.priceValueOfOneMeal = priceValueOfOneMeal;
    }

    public Price calculatePrice(Attendee attendee) {
        Price price;

        price = this.packageChoicePriceMap.entrySet().stream()
                .filter(entry -> attendee.choose(entry.getKey()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseGet(() -> new Price(0));

        if (attendee.checkinAfter(limitDateTimeOfFirstMeal)){
            price =  price.removePriceOfOnMeal(priceValueOfOneMeal);
        }

        if (attendee.checkoutBefore(limitDateTimeOfLastMeal)){
            price = price.removePriceOfOnMeal(priceValueOfOneMeal);
        }
        return price;


    }
}
