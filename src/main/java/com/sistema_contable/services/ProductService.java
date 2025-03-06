package com.sistema_contable.services;

import com.sistema_contable.dto.ProductDTO;
import com.sistema_contable.exceptions.ResourceExceptionsNotFound;
import java.util.List;

public interface ProductService {
    
    
    List<ProductDTO> findAll();
    
    ProductDTO findProductById(Long id) throws ResourceExceptionsNotFound;
    
    ProductDTO saveProduct(ProductDTO productDto) throws ResourceExceptionsNotFound;
    
    ProductDTO updateProduct(Long id, ProductDTO product) throws ResourceExceptionsNotFound;
    
    void deleteById(Long id);
}
