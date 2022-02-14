package de.scenaryo.spec;

import de.scenaryo.model.ProductSize;

public class SizeSpec {

    private ProductSize size;

    public SizeSpec(ProductSize size) {
        this.size = size;
    }

    public ProductSize getSize() {
        return this.size;
    }
}
