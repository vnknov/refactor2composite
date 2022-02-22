package de.scenaryo.repository;

import de.scenaryo.model.Product;
import de.scenaryo.spec.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRepository {

    private final List<Product> products = new ArrayList<>();

    public void addAll(Product... products) {
        this.products.addAll(Arrays.asList(products));
    }

    public Collection<Product> findBy(SearchCriteria spec) {

        return this.products.stream()
                .filter(asdf -> spec.matches(asdf))
                .collect(Collectors.toList());

    }

    public Collection<Product> findInDatabase(SearchCriteria spec) {
        String selectStatement = createStatement(spec);
        System.out.println("Search in DB: " + selectStatement);
        return null;
    }

    private String createStatement(SearchCriteria spec) {
        StringBuilder builder = new StringBuilder();
        if (spec instanceof NotSpec) {
            builder.append("NOT " + createStatement(((NotSpec) spec).getCriteria()));
        } else if (spec instanceof ColorSpec) {
            builder.append(((ColorSpec) spec).getColor());
        } else if (spec instanceof SizeSpec) {
            builder.append(((SizeSpec) spec).getSize());
        } else if (spec instanceof PriceSpec) {
            builder.append(((PriceSpec) spec).getPrice());
        } else if (spec instanceof AndSpec) {
            StringBuilder andBuilder = new StringBuilder();
            for (SearchCriteria currentCriteria : ((AndSpec) spec).getCriterias()) {
                if (andBuilder.length() > 0) {
                    andBuilder.append(" AND ");
                }
                if(currentCriteria instanceof OrSpec || currentCriteria instanceof AndSpec) {
                    andBuilder.append("(");
                }
                andBuilder.append(createStatement(currentCriteria));
                if(currentCriteria instanceof OrSpec || currentCriteria instanceof AndSpec) {
                    andBuilder.append(")");
                }
            }
            builder.append(" " + andBuilder + " ");
        } else if (spec instanceof OrSpec) {
            StringBuilder orBuilder = new StringBuilder();
            for (SearchCriteria currentCriteria : ((OrSpec) spec).getCriterias()) {
                if (orBuilder.length() > 0) {
                    orBuilder.append(" OR ");
                }
                if(currentCriteria instanceof OrSpec || currentCriteria instanceof AndSpec) {
                    orBuilder.append("(");
                }
                orBuilder.append(createStatement(currentCriteria));
                if(currentCriteria instanceof OrSpec || currentCriteria instanceof AndSpec) {
                    orBuilder.append(")");
                }

            }
            builder.append(" " + orBuilder + " ");

        }
        return builder.toString();
    }

}
