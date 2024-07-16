package dev.naga.productservice_sst.models;


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

}
