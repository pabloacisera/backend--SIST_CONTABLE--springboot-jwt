package com.sistema_contable.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="sales")
public class SalesEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private ClientsEntity client;
    
    @Column(name="sale_date", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date saleDate;
    
    @Column(name="total_price", nullable=false)
    private Double totalPrice;
    
    @ManyToMany
    @JoinTable(
            name="sale_products",
            joinColumns=@JoinColumn(name="sale_id"),
            inverseJoinColumns = @JoinColumn(name="product_id")
    )
    private Set<ProductEntity> products;

    public SalesEntity() {
    }

    public SalesEntity(Long id, ClientsEntity client, Date saleDate, Double totalPrice, Set<ProductEntity> products) {
        this.id = id;
        this.client = client;
        this.saleDate = saleDate;
        this.totalPrice = totalPrice;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientsEntity getClient() {
        return client;
    }

    public void setClient(ClientsEntity client) {
        this.client = client;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductEntity> products) {
        this.products = products;
    }
}