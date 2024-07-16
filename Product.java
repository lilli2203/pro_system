package dev.lilli.productservice_sst.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private Long id;
    private String title;
    private String description;
    private double price;
    private Category category;
    private String image;
    private double rating; 
    private int stock; 
    private String brand; 
    private String dimensions; 
    private String weight; 
    private String supplier; 

    public Product() {
    }

    public Product(Long id, String title, String description, double price, Category category, String image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.image = image;
    }

    public Product(Long id, String title, String description, double price, Category category, String image, double rating, int stock, String brand, String dimensions, String weight, String supplier) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.image = image;
        this.rating = rating;
        this.stock = stock;
        this.brand = brand;
        this.dimensions = dimensions;
        this.weight = weight;
        this.supplier = supplier;
    }

    public String displayProductInfo() {
        return String.format("Product ID: %d%nTitle: %s%nDescription: %s%nPrice: $%.2f%nCategory: %s%nBrand: %s%nRating: %.1f%nStock: %d%nDimensions: %s%nWeight: %s%nSupplier: %s",
                id, title, description, price, category.getName(), brand, rating, stock, dimensions, weight, supplier);
    }

    public boolean isInStock() {
        return stock > 0;
    }

    public void applyDiscount(double discountPercentage) {
        if (discountPercentage > 0 && discountPercentage <= 100) {
            this.price = this.price - (this.price * discountPercentage / 100);
        }
    }

    public void updateRating(double newRating) {
        if (newRating >= 0 && newRating <= 5) {
            this.rating = newRating;
        }
    }

    public void updateStock(int newStock) {
        if (newStock >= 0) {
            this.stock = newStock;
        }
    }

    public String getSummary() {
        return String.format("Product: %s%nPrice: $%.2f%nRating: %.1f", title, price, rating);
    }

    public static Product createSimpleProduct(Long id, String title, double price, Category category) {
        return new Product(id, title, null, price, category, null);
    }

    public static Product createDetailedProduct(Long id, String title, String description, double price, Category category, String image, double rating, int stock, String brand, String dimensions, String weight, String supplier) {
        return new Product(id, title, description, price, category, image, rating, stock, brand, dimensions, weight, supplier);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", image='" + image + '\'' +
                ", rating=" + rating +
                ", stock=" + stock +
                ", brand='" + brand + '\'' +
                ", dimensions='" + dimensions + '\'' +
                ", weight='" + weight + '\'' +
                ", supplier='" + supplier + '\'' +
                '}';
    }
}
