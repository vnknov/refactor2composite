package de.scenaryo.repository;

import de.scenaryo.model.Product;
import de.scenaryo.spec.ColorSpec;
import de.scenaryo.spec.SizeSpec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRepository {

    private List<Product> products = new ArrayList<>();

    public void addAll(Product... products) {
            this.products.addAll(Arrays.asList(products));
    }

    public Collection<Product> findBy(ColorSpec colorSpec) {
       return this.products.stream()
               .filter(p -> colorSpec.getColor().equals(p.getColor()))
               .collect(Collectors.toList());
    }

    public Collection<Product> findBy(SizeSpec sizeSpec) {
        return this.products.stream()
                .filter(p -> sizeSpec.getSize().equals(p.getSize()))
                .collect(Collectors.toList());
    }
}
