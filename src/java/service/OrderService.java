/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.OrderDetailDAO;
import dao.OrderItemDAO;
import dao.ProductDAO;
import entity.OrderDetail;
import entity.OrderItem;
import entity.Product;
import java.util.HashMap;
import entity.User;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author DTS
 */
public class OrderService {

    private final static OrderDetailDAO orderDao = new OrderDetailDAO();
    private final static OrderItemDAO orItemDao = new OrderItemDAO();
    private final static ProductDAO prodDao = new ProductDAO();

    private ProductService pService = new ProductService();

    public void checkout(HashMap<Integer, Integer> cart, User user) {
        if (cart.isEmpty()) { // If cart is empty
            return;
        }
        int lastID = orderDao.getLastOrderID();
        double total = getTotalPrice(cart);
//        DEBUG
        System.out.println("Total: " + total);
//        ------------

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new java.util.Date());
//        System.out.println(timeStamp);
//        System.out.println("User: " + user);
        OrderDetail order = new OrderDetail(lastID + 1, user.getId(), 1, timeStamp, total);
//        System.out.println(order);
        orderDao.addOrder(order);

        // for each item in the cart, we subtract the quantity from DB and add order_items to DB
        for (Map.Entry<Integer, Integer> cartItem : cart.entrySet()) {
            buyItem(order.getOrderID(), cartItem.getKey(), cartItem.getValue());
        }
        // After user checkout, clear the cart
        cart.clear();
    }

    /**
     * Get total price of all items in the cart. total price =
     * final-price-of-item (after discount) * quantity
     *
     * @param cart contains all items user added
     * @return
     */
    private double getTotalPrice(HashMap<Integer, Integer> cart) {
        double total = 0;
        for (Map.Entry<Integer, Integer> cartItem : cart.entrySet()) {
            total += pService.getFinalPrice(cartItem.getKey()) * cartItem.getValue();
        }
        return total;
    }

    /**
     * Buy an item = subtract the amount of item from DB, add record to
     * order_items table
     *
     * @param prodID
     * @param quantity
     */
    private void buyItem(int oID, int prodID, int quantity) {
        // Get product
        Product p = pService.getProductByID(prodID);
        int quant = p.getQuantity();

        //subtract the quantity 
//        -- !!! NEED TO CAREFULLY HANDLED LATER (SINCE THIS SOLUTION IS NOT GOOD)
// I THINK THAT WE SHOULD NOT SET QUAN = 0 WHEN quan < quantity
        quant = (quant - quantity) < 0 ? 0 : quant - quantity;

        OrderItem o = new OrderItem(orItemDao.getLastID() + 1, oID, prodID, quantity); // did NOT check for over quantity
        p.setQuantity(quant);

        // update product quantity and add orderitem
        orItemDao.addOrderItem(o);
        pService.updateProduct(p);
    }

    public Vector<OrderDetail> getAllOrders() {
        return orderDao.getAllOrders();
    }

    public OrderDetail getOrderByID(int oID) {
        return orderDao.getOrderByID(oID);
    }

    /**
     * Delete order_details with orderID, and all order_items corresponding to
     * that order_details
     *
     * @param orderID orderID of order you want to delete
     */
    public void deleteOrder(int orderID) {
        // Delete order_items first
        orItemDao.deleteOrderItems(orderID);
        // Then delete order_details
        orderDao.deleteOrderDetail(orderID);
    }
    
    public void updateOrderDetails(OrderDetail o) {
        orderDao.updateOrderDetail(o);
    }

}
