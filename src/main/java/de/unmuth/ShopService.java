package de.unmuth;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    // Methode, um alle Bestellungen mit einem bestimmten Bestellstatus zurückzugeben
    public List<Order> getOrdersByStatus(OrderStatus orderStatus) {
        List<Order> byStatus = orderRepo.getOrders().stream()
                .filter(order -> order.status().equals(orderStatus))
                .collect(Collectors.toList());

        return byStatus;
    }

    // Bearbeitet die Methode 'addOrder' im ShopService,
    // sodass eine Exception geworfen wird, wenn das Product nicht vorhanden ist.
    public Order addOrder(List<String> productIds) throws RuntimeException {
        List<Product> products = new ArrayList<>();

        for (String productId : productIds) {
            Optional<Product> productToOrder = productRepo.getProductById(productId);

            if (productToOrder.isEmpty()) {
                throw new RuntimeException ("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
            }
            products.add(productToOrder.get());
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products, OrderStatus.PROCESSING);
        return orderRepo.addOrder(newOrder);
    }

    // Methode, um eine Bestellung basierend auf der 'orderId' und dem neuen Status zu aktualisieren
    public Order updateOrder(String orderId, List<String> productIds, OrderStatus newStatus) {
        Order orderToUpdate = Optional.ofNullable(orderRepo.getOrderById(orderId))
                .orElseThrow(() -> new RuntimeException("Bestellung mit der ID: " + orderId + " nicht gefunden!"));

        List<Product> products = productIds.stream()
                .map(productId -> productRepo.getProductById(productId)
                .orElseThrow(() -> new RuntimeException("Bestellung mit der ID: " + orderId + " nicht gefunden!")))
                .toList();

        Order updatedOrder = orderToUpdate.withStatus(newStatus);
        orderRepo.removeOrder(orderId);
        orderRepo.addOrder(updatedOrder);

        return updatedOrder;
    }




    public ProductRepo getProductRepo() {
        return productRepo;
    }

    public void setProductRepo(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public OrderRepo getOrderRepo() {
        return orderRepo;
    }

    public void setOrderRepo(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }
}
