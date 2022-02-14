package de.scenaryo.repository;

import de.scenaryo.model.Product;
import de.scenaryo.model.ProductSize;
import de.scenaryo.spec.ColorSpec;
import de.scenaryo.spec.SizeSpec;
import org.junit.Before;
import org.junit.Test;


import java.awt.*;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class ProductRepositoryTest {

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
    public void testColorSpec() {
        ColorSpec colorSpec = new ColorSpec(Color.RED);
        Collection<Product> products = repository.findBy(colorSpec);
        products.forEach(System.out::println);
        assertEquals(2, products.size());
    }

    @Test
    public void testSizeSpec() {
        SizeSpec sizeSpec = new SizeSpec(ProductSize.S);
        Collection<Product> products = repository.findBy(sizeSpec);
        products.forEach(System.out::println);
        assertEquals(5, products.size());
    }



}