package com.sistema_contable.entities;

import jakarta.persistence.*;

@Entity
@Table(name="products")
public class ProductEntity {
    
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   @Column(name="name", nullable=false)
   private String name;
   
   @Column(name="description", nullable=false)
   private String description;
   
   @Column(name="price", nullable=false)
   private Double price;
   
   @Column(name="stock_quantity", nullable=true)
   private int stockQuantity;

    public ProductEntity() {
    }

    public ProductEntity(Long id, String name, String description, Double price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

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
    
    
}
