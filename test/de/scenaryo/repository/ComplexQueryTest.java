package de.scenaryo.repository;

import de.scenaryo.model.Product;
import de.scenaryo.model.ProductSize;
import de.scenaryo.spec.*;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class ComplexQueryTest {

    private ProductRepository repository;

    @Before
    public void setUp() {

        Product p1 = new Product("Laptop", Color.BLACK, ProductSize.M, 1999.99f);
        Product p2 = new Product("Laptop", Color.GRAY, ProductSize.M, 1999.99f);
        Product p3 = new Product("Pencil", Color.RED, ProductSize.S, 0.99f);
        Product p4 = new Product("Post Its", Color.RED, ProductSize.S, 2.99f);
        Product p5 = new Product("Post Its", Color.GREEN, ProductSize.S, 2.99f);
        Product p6 = new Product("Post Its", Color.BLUE, ProductSize.S, 2.99f);
        Product p7 = new Product("Table", Color.WHITE, ProductSize.S, 2.99f);

        repository = new ProductRepository();
        repository.addAll(p1, p2, p3, p4, p5, p6, p7);
    }


    @Test
    public void testColorAndPriceSpec() {
        SearchCriteria priceSpec1 = new PriceSpec(2.99f);
        ColorSpec colorSpec1 = new ColorSpec(Color.RED);
        AndSpec andSpec1= new AndSpec();
        andSpec1.addCriteria(priceSpec1);
        andSpec1.addCriteria(colorSpec1);

        ColorSpec colorSpec2 = new ColorSpec(Color.GRAY);
        NotSpec notGray = new NotSpec(colorSpec2);
        SizeSpec sizeM = new SizeSpec(ProductSize.M);
        AndSpec andSpec2= new AndSpec();
        andSpec2.addCriteria(notGray);
        andSpec2.addCriteria(sizeM);

        OrSpec orSpec = new OrSpec();
        orSpec.addCriteria(andSpec1);
        orSpec.addCriteria(andSpec2);


        Collection<Product> products = repository.findBy(orSpec);
        products.forEach(System.out::println);
        assertEquals(2, products.size());


        repository.findInDatabase(orSpec);
    }

}