package de.scenaryo.spec;

import de.scenaryo.model.Product;
import de.scenaryo.repository.SearchCriteria;

public class PriceSpec implements SearchCriteria {

    private float price;

    public PriceSpec (float price) {
        this.price = price;
    }

    @Override
    public boolean matches(Product product) {
        return this.price - product.getPrice() == 0f;
    }

}
