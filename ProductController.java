package dev.lilli.productservice_sst.controllers;

import dev.lilli.productservice_sst.models.Product;
import dev.lilli.productservice_sst.models.ExceptionDto;
import dev.lilli.productservice_sst.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// This controller is capable to host HTTP APIs
@RestController
// localhost:8080/products -> ProductController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
        try {
            Product product = productService.getProductById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (RuntimeException exception) {
            ExceptionDto dto = new ExceptionDto();
            dto.setMessage("Product not found");
            return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        try {
            Product newProduct = productService.addProduct(product);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (RuntimeException exception) {
            ExceptionDto dto = new ExceptionDto();
            dto.setMessage("Failed to add product");
            return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
        }
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
}
