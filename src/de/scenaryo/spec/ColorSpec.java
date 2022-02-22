package de.scenaryo.spec;

import de.scenaryo.model.Product;
import de.scenaryo.repository.SearchCriteria;

import java.awt.*;

public class ColorSpec implements SearchCriteria {

    public Color color;

    public ColorSpec (Color color) {
        this.color = color;
    }

    @Override
    public boolean matches(Product product) {
        return this.color.equals(product.getColor());
    }
}
