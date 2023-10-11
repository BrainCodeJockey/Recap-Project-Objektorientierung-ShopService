package de.unmuth;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ShopService service = new ShopService();
        ProductRepo productRepoService = service.getProductRepo();

        service.getProductRepo().addProduct(new Product("2","iPhone"));
        service.getProductRepo().addProduct(new Product("3","Galaxy"));
        service.getProductRepo().addProduct(new Product("4","iPad"));
        service.getProductRepo().addProduct(new Product("5","MacBook"));
        service.getProductRepo().addProduct(new Product("6","Xiaomi"));

        //service.addOrder(List.of("100"));

        Order initialOrder = service.addOrder(List.of("1"));
        System.out.println(initialOrder);

        Order updatedOrder = service.updateOrder(initialOrder.id(), OrderStatus.COMPLETED);
        System.out.println(updatedOrder);
    }
}
