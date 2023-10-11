package de.unmuth;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ShopService service = new ShopService();
        ProductRepo productRepoService = service.getProductRepo();

        service.getProductRepo().addProduct(new Product("p1","iPhone"));
        service.getProductRepo().addProduct(new Product("p2","Galaxy Phone"));
        service.getProductRepo().addProduct(new Product("p3","iPad"));
        service.getProductRepo().addProduct(new Product("p4","Mac Book"));
        service.getProductRepo().addProduct(new Product("p5","Xiaomi Phone"));

        service.addOrder(List.of("100"));
    }
}


