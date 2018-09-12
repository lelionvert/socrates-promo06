package fr.lacombe.magic6tem.price;

import java.util.Objects;

class Price {

    private int value;

    public Price(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return value == price.value;
    }

    @Override
    public int hashCode() {

        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Price{" +
                "\nvalue=" + value +
                '}';
    }

    public Price removePriceOfOnMeal(int priceOfOneMeal) {

        return new Price(this.value - priceOfOneMeal);
    }
}
