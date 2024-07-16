package dev.nlilli.productservice_sst.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Category {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public Category() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    public Category(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title;
        this.updatedAt = LocalDateTime.now();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void updateCategory(String title, String description) {
        setTitle(title);
        setDescription(description);
    }


    public boolean isNewCategory() {
        return id == null;
    }

    public boolean isSameCategory(Category other) {
        return other != null && Objects.equals(id, other.id);
    }

    public boolean hasSameDetails(Category other) {
        return other != null && Objects.equals(title, other.title) && Objects.equals(description, other.description);
    }

    public void resetUpdatedAt() {
        this.updatedAt = this.createdAt;
    }

    public String getCategoryInfo() {
        return String.format("Category ID: %d%nTitle: %s%nDescription: %s%nCreated At: %s%nUpdated At: %s",
                id, title, description, createdAt, updatedAt);
    }


    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }


    public static void main(String[] args) {
        Category category = new Category(1L, "Electronics", "Category for electronic products");
        System.out.println("Created category: " + category);

        try {
            category.setTitle(null); g
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }

        category.updateCategory("Mobile Phones", "Category for mobile phones");
        System.out.println("Updated category: " + category);

        Category category2 = new Category(2L, "Clothing", "Category for clothing products");
        System.out.println("\nCreated category 2: " + category2);
        System.out.println("Is category 2 new? " + category2.isNewCategory());
        System.out.println("Is category 1 the same as category 2? " + category.isSameCategory(category2));
        System.out.println("Does category 1 have the same details as category 2? " + category.hasSameDetails(category2));
    }
}
