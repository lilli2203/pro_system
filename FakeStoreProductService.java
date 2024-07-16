package dev.naga.productservice_sst.services;

import dev.naga.productservice_sst.dtos.FakeStoreProductDto;
import dev.naga.productservice_sst.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class FakeStoreProductService implements ProductService {

    private final ExecutorService executorService = Executors.newFixedThreadPool(10); // New executor service

    @Override
    public Product getProductById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("http://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    public List<Product> getAllProducts() {
        RestTemplate restTemplate = new RestTemplate();
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("http://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return products;
    }

    public Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImage(fakeStoreProductDto.getImage());
        product.setRating(fakeStoreProductDto.getRating());
        product.setCategory(fakeStoreProductDto.getCategory());
        product.setStock(fakeStoreProductDto.getStock());
        product.setBrand(fakeStoreProductDto.getBrand());
        product.setDimensions(fakeStoreProductDto.getDimensions());
        product.setWeight(fakeStoreProductDto.getWeight());
        product.setSupplier(fakeStoreProductDto.getSupplier());
        return product;
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

    public CompletableFuture<Product> getProductByIdAsync(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            RestTemplate restTemplate = new RestTemplate();
            FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("http://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
            return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
        }, executorService);
    }

    public void shutdownExecutorService() {
        executorService.shutdown();
    }


}
