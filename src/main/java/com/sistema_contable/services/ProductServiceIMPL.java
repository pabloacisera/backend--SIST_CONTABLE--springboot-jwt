package com.sistema_contable.services;

import com.sistema_contable.dto.ProductDTO;
import com.sistema_contable.entities.ProductEntity;
import com.sistema_contable.exceptions.ResourceExceptionsNotFound;
import com.sistema_contable.repositories.ProductsRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceIMPL implements ProductService{
    
    @Autowired
    private ProductsRepository productRepository;

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findProductById(Long id) {
        
        return productRepository.findById(id)
                .map(ProductDTO::fromEntity)
                .orElseThrow(()-> new ResourceExceptionsNotFound("product", "id", id));
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDto) {
        
        ProductEntity product = new ProductEntity();
        
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setStockQuantity(productDto.getStockQuantity());
        
        ProductEntity savedProduct = productRepository.save(product);

        return ProductDTO.fromEntity(savedProduct);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO product) throws ResourceExceptionsNotFound {
        
        // buscar el producto
        
        ProductEntity existingProduct = productRepository.findById(id)
                .orElseThrow(()-> new ResourceExceptionsNotFound("product", "id", id));
        
        // actualizamos los campos que no son nulos
        
        if(product.getName() != null)
        {
            existingProduct.setName(product.getName());
        }
        
        if(product.getDescription() !=null)
        {
            existingProduct.setDescription(product.getDescription());
        }
        
        if(product.getPrice() != null)
        {
            existingProduct.setPrice(product.getPrice());
        }
        
        if(product.getStockQuantity() >= 1)
        {
            existingProduct.setStockQuantity(product.getStockQuantity());
        }
        
        // guardar el producto actualizado
        
        ProductEntity updatedProduct = productRepository.save(existingProduct);
        
        return ProductDTO.fromEntity(updatedProduct);
        
    }
    
}
