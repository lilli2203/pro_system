package dev.naga.productservice_sst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceSstApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceSstApplication.class, args);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        try {
            Product updatedProduct = productService.updateProduct(id, product);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (RuntimeException exception) {
            ExceptionDto dto = new ExceptionDto();
            dto.setMessage("Failed to update product");
            return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException exception) {
            ExceptionDto dto = new ExceptionDto();
            dto.setMessage("Failed to delete product");
            return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable("categoryId") Long categoryId) {
        List<Product> products = productService.getProductsByCategory(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam("keyword") String keyword) {
        List<Product> products = productService.searchProducts(keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/sale")
    public ResponseEntity<List<Product>> getProductsOnSale() {
        List<Product> products = productService.getProductsOnSale();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

 @GetMapping("/featured")
    public ResponseEntity<List<Product>> getFeaturedProducts() {
        List<Product> products = productService.getFeaturedProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/new-arrivals")
    public ResponseEntity<List<Product>> getNewArrivalProducts() {
        List<Product> products = productService.getNewArrivalProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/recommended")
    public ResponseEntity<List<Product>> getRecommendedProducts() {
        List<Product> products = productService.getRecommendedProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

public List<Product> getAllProducts(int page, int size) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("http://fakestoreapi.com/products?_page=%d&_limit=%d", page, size);
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(url, FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return products;
    }

    public Product getProductByIdWithErrorHandling(Long id) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("http://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
            return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve product with id: " + id, e);
        }
    }
}
