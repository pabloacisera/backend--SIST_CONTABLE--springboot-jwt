package com.sistema_contable.controllers;

import com.sistema_contable.dto.ProductDTO;
import com.sistema_contable.exceptions.ResourceExceptionsNotFound;
import com.sistema_contable.services.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping("/find_all_products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        try {

            List<ProductDTO> products = productService.findAll();

            return new ResponseEntity<>(products, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get_product_by_id/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {

        try {

            ProductDTO product = productService.findProductById(id);

            return new ResponseEntity<>(product, HttpStatus.OK); // Devuelve 200 OK si el producto existe

        } catch (ResourceExceptionsNotFound e) {

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Devuelve 404 Not Found si el producto no existe
        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // Devuelve 500 Internal Server Error para otros errores
        }
    }

    @PostMapping("/create_product")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDto) {

        try {

            ProductDTO createdProduct = productService.saveProduct(productDto);

            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (ResourceExceptionsNotFound e) {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update_product_by_id/{id}")
    public ResponseEntity<ProductDTO> updatedProduct(@PathVariable Long id,@RequestBody ProductDTO product) {
        try {
            ProductDTO updatedProduct = productService.updateProduct(id, product);

            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);

        } catch (ResourceExceptionsNotFound e) {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete_product_by_id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            productService.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
