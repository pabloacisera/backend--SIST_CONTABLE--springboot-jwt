package com.sistema_contable.services;

import com.sistema_contable.dto.SalesDTO;
import com.sistema_contable.exceptions.ResourceExceptionsNotFound;
import java.util.List;


public interface SalesService {
    
    
    List<SalesDTO> findAllSales();
    
    SalesDTO findSaleById(Long id) throws ResourceExceptionsNotFound;
    
    SalesDTO saveSale(SalesDTO sales) throws ResourceExceptionsNotFound;
    
    SalesDTO updateSale(Long id, SalesDTO sales) throws ResourceExceptionsNotFound;
    
    void deleteById(Long id);
}