package com.sistema_contable.services;

import com.sistema_contable.dto.ClientDto;
import com.sistema_contable.entities.ClientsEntity;
import com.sistema_contable.repositories.ClientsRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//pagination
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class ClientServiceIMPL implements ClientService {
    
    @Autowired
    private ClientsRepository clientRepo;

    @Override
    public ClientDto newClient(ClientDto newClient) {
        
        // Convert DTO to ENTITY
        ClientsEntity client = new ClientsEntity();
        client.setName(newClient.getName());
        client.setCuit(newClient.getCuit());
        client.setAddress(newClient.getAddress());
        client.setEmail(newClient.getEmail());
        client.setPhone(newClient.getPhone());
        client.setImg_url(newClient.getImg_url());
        
        ClientsEntity newClientEntity = clientRepo.save(client);
        
        // Convert ENTITY to DTO
        ClientDto newClientDto = new ClientDto();
        newClientDto.setId(newClientEntity.getId());
        newClientDto.setName(newClientEntity.getName());
        newClientDto.setCuit(newClientEntity.getCuit());
        newClientDto.setAddress(newClientEntity.getAddress());
        newClientDto.setEmail(newClientEntity.getEmail());
        newClientDto.setPhone(newClientEntity.getPhone());
        newClientDto.setImg_url(newClientEntity.getImg_url());
        
        return newClientDto;
    }

    @Override
    public ClientDto getClientById(Long id) {
        // Find client by ID
        ClientsEntity clientFound = clientRepo.findById(id).orElse(null);
        
        if (clientFound == null) {
            return null;
        }
        
        // Save found data into DTO
        ClientDto clientDto = new ClientDto();
        clientDto.setId(clientFound.getId());
        clientDto.setName(clientFound.getName());
        clientDto.setAddress(clientFound.getAddress());
        clientDto.setCuit(clientFound.getCuit());
        clientDto.setEmail(clientFound.getEmail());
        clientDto.setPhone(clientFound.getPhone());
        clientDto.setImg_url(clientFound.getImg_url());
        
        return clientDto;
    }

    @Override
    public List<ClientDto> getAllClients(int numberOfPages, int pageSize) {
        Pageable pageable = (Pageable) PageRequest.of(numberOfPages, pageSize);
        Page<ClientsEntity> clientsPage = clientRepo.findAll(pageable);

        // Map each result to DTO using stream
        return clientsPage.stream().map((ClientsEntity client) -> {
            ClientDto clientDto = new ClientDto();
            clientDto.setId(client.getId());
            clientDto.setName(client.getName());
            clientDto.setCuit(client.getCuit());
            clientDto.setAddress(client.getAddress());
            clientDto.setEmail(client.getEmail());
            clientDto.setPhone(client.getPhone());
            clientDto.setImg_url(client.getImg_url());
            return clientDto;
        }).collect(Collectors.toList());
    }

    @Override
    public ClientDto updateClientById(Long id, ClientDto clientDto) {
        // Get client to be updated
        ClientsEntity client = clientRepo.findById(id).orElse(null);
        
        if (client != null) {
            // Update client with new values from request body
            client.setName(clientDto.getName());
            client.setCuit(clientDto.getCuit());
            client.setAddress(clientDto.getAddress());
            client.setEmail(clientDto.getEmail());
            client.setPhone(clientDto.getPhone());
            client.setImg_url(clientDto.getImg_url());
            
            // Save updated client
            ClientsEntity updatedClientEntity = clientRepo.save(client);
            
            // Save updated data into DTO and return to controller
            ClientDto updatedClientDto = new ClientDto();
            updatedClientDto.setId(updatedClientEntity.getId());
            updatedClientDto.setName(updatedClientEntity.getName());
            updatedClientDto.setCuit(updatedClientEntity.getCuit());
            updatedClientDto.setAddress(updatedClientEntity.getAddress());
            updatedClientDto.setEmail(updatedClientEntity.getEmail());
            updatedClientDto.setPhone(updatedClientEntity.getPhone());
            updatedClientDto.setImg_url(updatedClientEntity.getImg_url());
            
            return updatedClientDto;
        }
        
        return null;
    }

    @Override
    public boolean deleteClientById(Long id) {
        ClientsEntity clientFound = clientRepo.findById(id).orElse(null);
        
        if (clientFound == null) {
            return false;
        }
        
        clientRepo.delete(clientFound);
        return true;
    }
}
