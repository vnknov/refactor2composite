package de.scenaryo.repository;

import de.scenaryo.model.Product;

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

    public Collection<Product> findBy(SearchCriteria spec) {

            return this.products.stream()
                    .filter(asdf -> spec.matches(asdf))
                    .collect(Collectors.toList());

     }

}
