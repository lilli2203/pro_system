package dev.lilli.productservice_sst.exceptionhandlers;

import java.time.LocalDateTime;

public class ProductNotFoundException extends RuntimeException {
    private String productId;
    private LocalDateTime timestamp;
    private String message;

    public ProductNotFoundException(String productId) {
        this.productId = productId;
        this.timestamp = LocalDateTime.now();
        this.message = "Product not found";
    }

    public ProductNotFoundException(String productId, String message) {
        this.productId = productId;
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }

    public ProductNotFoundException(String productId, String message, Throwable cause) {
        super(cause);
        this.productId = productId;
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }

    public String getProductId() {
        return productId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ProductNotFoundException{" +
                "productId='" + productId + '\'' +
                ", timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", cause=" + getCause() +
                '}';
    }

    public void logException() {
        System.err.println(toString());
    }

    public void notifyUser() {
        System.out.println("Notification: Product with ID " + productId + " was not found at " + timestamp);
    }

    public void retryOperation() {
        System.out.println("Retrying operation for product ID: " + productId);
    }

    public void escalateIssue() {
        System.out.println("Escalating issue for product ID: " + productId);
    }

    public static void main(String[] args) {
        try {
            throw new ProductNotFoundException("12345", "Custom error message");
        } catch (ProductNotFoundException e) {
            e.logException();
            e.notifyUser();
            e.retryOperation();
            e.escalateIssue();
        }

        try {
            throw new ProductNotFoundException("67890");
        } catch (ProductNotFoundException e) {
            e.logException();
            e.notifyUser();
        }
    }
}
