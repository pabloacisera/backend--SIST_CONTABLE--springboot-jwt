
package com.sistema_contable.dto;

import com.sistema_contable.entities.ProductEntity;
import com.sistema_contable.entities.SalesEntity;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

public class SalesDTO {
    private Long id;
    private Date dateSale;
    private Long clientId;
    private Double totalPrice;
    private Set<Long> productsIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateSale() {
        return dateSale;
    }

    public void setDateSale(Date dateSale) {
        this.dateSale = dateSale;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<Long> getProductsIds() {
        return productsIds;
    }

    public void setProductsIds(Set<Long> productsIds) {
        this.productsIds = productsIds;
    }
    
    public static SalesDTO fromEntity(SalesEntity sales)
    {
        SalesDTO dto = new SalesDTO();
        
        dto.setId(sales.getId());
        dto.setDateSale(sales.getSaleDate());
        dto.setClientId(sales.getClient().getId());
        dto.setTotalPrice(sales.getTotalPrice());
        
        Set<Long> productsId = sales.getProducts().stream()
                .map(ProductEntity::getId)
                .collect(Collectors.toSet());
        dto.setProductsIds(productsId);
        
        return dto;
    }
}
