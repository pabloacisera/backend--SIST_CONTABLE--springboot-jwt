package com.sistema_contable.services;

import com.sistema_contable.dto.SalesDTO;
import com.sistema_contable.entities.SalesEntity;
import com.sistema_contable.exceptions.ResourceExceptionsNotFound;
import com.sistema_contable.repositories.SalesRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesServiceIMPL implements SalesService{
    
    @Autowired
    private SalesRepository salesRepository;
    
    // convertir entity a DTO

    @Override
    public List<SalesDTO> findAllSales() {
        
        List<SalesEntity>salesEntities = salesRepository.findAll();
        
        return salesEntities.stream()
                .map(SalesDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public SalesDTO findSaleById(Long id) throws ResourceExceptionsNotFound {
        SalesEntity salesEntity = salesRepository.findById(id)
                .orElseThrow(() -> new ResourceExceptionsNotFound("Sale not found:  ", "id" ,id));
        return SalesDTO.fromEntity(salesEntity); 
    }


    @Override
    public SalesDTO saveSale(SalesDTO sales) throws ResourceExceptionsNotFound {
        
        SalesEntity salesEntity = converToEntity(sales);
        
        SalesEntity savedSale = salesRepository.save(salesEntity);
                
        return SalesDTO.fromEntity(savedSale);
        
    }

    @Override
    public SalesDTO updateSale(Long id, SalesDTO sales) throws ResourceExceptionsNotFound {
        
        SalesEntity existingSale = salesRepository.findById(id)
                .orElseThrow(()-> new ResourceExceptionsNotFound("Sale not found: ","id", id));
        
        // actualizar la entidad con los valores del dto
        existingSale.setSaleDate(sales.getDateSale());
        existingSale.setTotalPrice(sales.getTotalPrice());
        
        SalesEntity updatedSale = salesRepository.save(existingSale);
        
        return SalesDTO.fromEntity(updatedSale);        
    }

    @Override
    public void deleteById(Long id) {
        salesRepository.deleteById(id);
    }
    
    // metodo para convertir dto a entidad
    private SalesEntity converToEntity(SalesDTO salesDto)
    {
        SalesEntity salesEntity = new SalesEntity();
        
        salesEntity.setId(salesDto.getId());
        salesEntity.setSaleDate(salesDto.getDateSale());
        salesEntity.setTotalPrice(salesDto.getTotalPrice());
        
        return salesEntity;
    }
    
}
