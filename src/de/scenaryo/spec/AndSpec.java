package de.scenaryo.spec;

import de.scenaryo.model.Product;
import de.scenaryo.repository.SearchCriteria;

import java.util.ArrayList;
import java.util.List;

public class AndSpec implements SearchCriteria {

    private List<SearchCriteria> criterias;

    public AndSpec(List<SearchCriteria> criterias) {
        if(criterias == null) {
            throw new IllegalArgumentException("Criterias must not be null!");
        }
        this.criterias = criterias;
    }

    public AndSpec() {
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
        return this.criterias.stream().allMatch(c -> c.matches(product));
    }

    public List<SearchCriteria> getCriterias() {
        return this.criterias;
    }
}
