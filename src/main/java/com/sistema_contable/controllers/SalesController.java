package com.sistema_contable.controllers;

import com.sistema_contable.dto.SalesDTO;
import com.sistema_contable.exceptions.ResourceExceptionsNotFound;
import com.sistema_contable.services.SalesService;
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
@RequestMapping("/api/sales")
public class SalesController {
    
    @Autowired
    private SalesService salesService;
    
    @GetMapping("/find_all")
    public ResponseEntity<?> getAllSales()
    {
        try {
            List<SalesDTO> sales = salesService.findAllSales();
            
            if(sales.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            
            return new ResponseEntity<>(sales, HttpStatus.OK);
        } catch (Exception e) {
            
            return new ResponseEntity<>("Error on procces to request: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }
    
    @GetMapping("/sale_by_id/{id}")
    public  ResponseEntity<?> getSaleById(@PathVariable Long id)
    {
        try {
            SalesDTO sale = salesService.findSaleById(id);
            
            return new ResponseEntity<>(sale, HttpStatus.OK);
        } catch(ResourceExceptionsNotFound e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error to proccess request: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/create_sale")
    public ResponseEntity<?> createSale(@RequestBody SalesDTO salesDto)
    {
        try {
            SalesDTO createdSale = salesService.saveSale(salesDto);
            
            return new ResponseEntity<>(createdSale, HttpStatus.OK);
        } catch(ResourceExceptionsNotFound e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error to proccess the request: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/updated_by_id/{id}")
    public ResponseEntity<?> updateSale(@PathVariable Long id, @RequestBody SalesDTO salesDto)
    {
        try {
            SalesDTO updatedSale = salesService.updateSale(id, salesDto);
            
            return new ResponseEntity<>(updatedSale, HttpStatus.OK);
        } catch(ResourceExceptionsNotFound e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error to proccess the request: "+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/delete_by_id/{id}")
    public ResponseEntity<?> deleteSale(@PathVariable Long id)
    {
        try {
            salesService.deleteById(id);
            
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(ResourceExceptionsNotFound e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error to proccess the request: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }
}


























