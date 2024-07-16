package dev.naga.productservice_sst.controllers;

import dev.naga.productservice_sst.models.Product;
import dev.naga.productservice_sst.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// this controller is capable to host HTTP API'S
@RestController
// localhost:8080/products->ProductController
@RequestMapping("/products")
public class ProductController { // waiter
    private ProductService productService;

    ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping ("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
//        ResponseEntity<Product> responseEntity = null;
//        Product product = null;
//        try {
//            product = productService.getProductById(id);
//            responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
//            return responseEntity;
//        } catch (RuntimeException exception) {
//            ExceptionDto dto = new ExceptionDto();
//            dto.setMessage("Something went wrong");
//            ResponseEntity<ExceptionDto> response = new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
//
//
        return productService.getProductById(id);
//
    }
        @GetMapping
        public List<Product> getAllProducts () {
            return productService.getAllProducts();
        }
    }
