package com.sistema_contable.dto;

import com.sistema_contable.entities.ProductEntity;

public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private int stockQuantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    
    // clase que permite utilizar el dto en otros codigos
    public static ProductDTO fromEntity(ProductEntity product)
    {
        ProductDTO dto = new ProductDTO();
        
        dto.setId(product.getId());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        
        return dto;
    }
}
