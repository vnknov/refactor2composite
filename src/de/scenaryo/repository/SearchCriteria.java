package de.scenaryo.repository;

import de.scenaryo.model.Product;

public interface SearchCriteria {

    boolean matches(Product product);
}
