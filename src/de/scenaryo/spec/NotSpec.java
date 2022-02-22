package de.scenaryo.spec;

import de.scenaryo.model.Product;
import de.scenaryo.repository.SearchCriteria;

public class NotSpec implements SearchCriteria {

    private SearchCriteria criteria;

    public NotSpec(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public boolean matches(Product product) {
        return !this.getCriteria().matches(product);
    }

    public SearchCriteria getCriteria() {
        return criteria;
    }
}
