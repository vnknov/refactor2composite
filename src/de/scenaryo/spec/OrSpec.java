package de.scenaryo.spec;

import de.scenaryo.model.Product;
import de.scenaryo.repository.SearchCriteria;

import java.util.ArrayList;
import java.util.List;

public class OrSpec implements SearchCriteria {

    private List<SearchCriteria> criterias;

    public OrSpec(List<SearchCriteria> criterias) {
        if(criterias == null) {
            throw new IllegalArgumentException("Criterias must not be null!");
        }
        this.criterias = criterias;
    }

    public OrSpec() {
        this.criterias = new ArrayList<>();
    }

    public void addCriteria(SearchCriteria criteria) {
        if(criteria == null) {
            return;
        }
        this.criterias.add(criteria);
    }

    @Override
    public boolean matches(Product product) {
        return this.criterias.stream().anyMatch(c -> c.matches(product));
    }
}
