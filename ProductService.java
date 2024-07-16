package dev.naga.productservice_sst.services;

import dev.naga.productservice_sst.models.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Long id);

    List<Product> getAllProducts();

    void addProduct(Product product);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);

    List<Product> getProductsByCategory(Long categoryId);

    List<Product> searchProducts(String keyword);

    List<Product> getProductsOnSale();

    List<Product> getOutOfStockProducts();

    List<Product> getTopBestsellingProducts(int topN);

    List<Product> getProductsByCriteria(double minPrice, double maxPrice, Long categoryId);

    double calculateAverageRating(Long productId);

    void addReview(Long productId, String review);

    List<String> getReviews(Long productId);

    boolean isProductAvailable(Long productId);

    List<Product> getRecommendedProducts(Long userId);

    List<Product> getNewArrivalProducts(int daysAgo);

    int getTotalProductCount();

    List<Product> getProductsByPopularity();

    List<Product> getClearanceSaleProducts();

    List<Product> getExpiringSoonProducts();

    List<Product> getProductsByShippingAvailability(boolean internationalShipping);

    List<Product> getTrendingProducts();

    List<Product> getRelatedProducts(Long productId);

    List<Product> getPersonalizedRecommendations(Long userId);
}
