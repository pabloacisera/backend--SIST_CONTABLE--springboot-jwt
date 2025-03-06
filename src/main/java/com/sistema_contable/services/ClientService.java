package com.sistema_contable.services;

import com.sistema_contable.dto.ClientDto;
import java.util.List;

public interface ClientService {
    
    public ClientDto newClient(ClientDto newClient);
    
    public ClientDto getClientById(Long id);
    
    public List<ClientDto> getAllClients(int numberOfPages, int pageSize);
    
    public ClientDto updateClientById(Long id, ClientDto client);
    
    public boolean deleteClientById(Long id);
    
}
