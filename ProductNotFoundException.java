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

    // Additional methods for error handling and customization
    public void handleIssue() {
        System.out.println("Handling issue for product ID: " + productId);
    }

    public void logToDatabase() {
        System.out.println("Logging exception to database for product ID: " + productId);
    }

    public void performAudit() {
        System.out.println("Performing audit for product ID: " + productId);
    }

    public void sendEmailNotification() {
        System.out.println("Sending email notification for product ID: " + productId);
    }

    public void escalateToSupport() {
        System.out.println("Escalating issue to support team for product ID: " + productId);
    }

    public void handleRetryLogic() {
        System.out.println("Implementing retry logic for product ID: " + productId);
    }

    public void handleEscalation() {
        System.out.println("Handling escalation process for product ID: " + productId);
    }

    public void logToAuditTrail() {
        System.out.println("Logging exception to audit trail for product ID: " + productId);
    }

    public void performAnalysis() {
        System.out.println("Performing analysis for product ID: " + productId);
    }

    public void handleResolution() {
        System.out.println("Handling resolution for product ID: " + productId);
    }
}
