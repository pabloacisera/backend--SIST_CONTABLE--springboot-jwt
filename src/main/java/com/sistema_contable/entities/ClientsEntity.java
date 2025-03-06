
package com.sistema_contable.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="clientes", uniqueConstraints = {@UniqueConstraint(columnNames = {"CUIT"})})
public class ClientsEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name="nombre", nullable=false)
    private String name;
    @Column(name="CUIT", nullable=false)
    private String cuit;
    @Column(name="direccion", nullable=false)
    private String address;
    @Column(name="email", nullable=false)
    private String email;
    @Column(name="tel", nullable=false)
    private String phone;
    @Column(name="img", nullable=true)
    private String img_url;

    public ClientsEntity() {
    }

    public ClientsEntity(long id, String name, String cuit, String address, String email, String phone, String img_url) {
        this.id = id;
        this.name = name;
        this.cuit = cuit;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.img_url = img_url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }   
}
