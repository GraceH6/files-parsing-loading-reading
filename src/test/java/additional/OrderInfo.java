package additional;

public class OrderInfo {
    private Integer orderID;
    private String customerName;
    private String customerEmail;
    private OrderContent orderContent;
    private Boolean orderCompleted;

    public Integer getOrderID() {
        return orderID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public OrderContent getOrderContent() {
        return orderContent;
    }

    public static class OrderContent {
        Integer productID, quantity;
        String productName;

        public Integer getProductID() {
            return productID;
        }

        public String getProductName() {
            return productName;
        }

        public Integer getQuantity() {

            return quantity;
        }
    }

    public Boolean isOrderCompleted() {
        return orderCompleted;
    }
}
