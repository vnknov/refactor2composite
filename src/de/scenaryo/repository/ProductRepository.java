package de.scenaryo.repository;

import de.scenaryo.model.Product;
import de.scenaryo.spec.ColorSpec;
import de.scenaryo.spec.NotSpec;

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

    public Collection<Product> findInDatabase(SearchCriteria spec) {
        String selectStatement = createStatement(spec);
        System.out.println("Search in DB: " + selectStatement);
      return null;
    }

    private String createStatement(SearchCriteria spec) {
        StringBuilder builder = new StringBuilder();
        if(spec instanceof NotSpec) {
            builder.append( "NOT " + createStatement(((NotSpec) spec).getCriteria()));
        }else if(spec instanceof ColorSpec) {
            builder.append( ((ColorSpec)spec).getColor());
        }
        return builder.toString();
    }

}
