package de.scenaryo.spec;

import de.scenaryo.model.Product;
import de.scenaryo.model.ProductSize;
import de.scenaryo.repository.SearchCriteria;

public class SizeSpec implements SearchCriteria {

    private ProductSize size;

    public SizeSpec(ProductSize size) {
        this.size = size;
    }

    @Override
    public boolean matches(Product product) {
        return this.size.equals(product.getSize());
    }
}
