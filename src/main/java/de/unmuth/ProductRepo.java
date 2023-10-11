package de.unmuth;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepo {
    private List<Product> products;

    public ProductRepo() {
        products = new ArrayList<>();
        products.add(new Product("1", "Apfel"));
    }

    public List<Product> getProducts() {
        return products;
    }


    // Methoden
    // Bearbeitet die Methode 'getProductById' in eurem ProductRepo,
    // sodass sie ein Optional zurückgibt, wenn das Produkt existiert,
    // andernfalls ein leeres Optional.
    public Optional<Product>getProductById(String id) {
        for (Product product : products) {
            if (product.id().equals(id)) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }

    public Optional<Product> addProduct(Product newProduct) {
        products.add(newProduct);
        return Optional.empty();
    }

    public void removeProduct(String id) {
        for (Product product : products) {
           if (product.id().equals(id)) {
               products.remove(product);
               return;
           }
        }
    }
}
