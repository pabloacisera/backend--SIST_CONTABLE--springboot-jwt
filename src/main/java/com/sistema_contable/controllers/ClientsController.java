package com.sistema_contable.controllers;

import com.sistema_contable.dto.ClientDto;
import com.sistema_contable.services.ClientService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clients")
public class ClientsController {
    
    @Autowired
    private ClientService clientService;
    
    @PostMapping("/new_client")
    public ResponseEntity<?> newClient(@RequestBody ClientDto newClient) {
        try {
            ClientDto savedClient = clientService.newClient(newClient);
            return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error saving the new client: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/get_by_id/{id}")
    public ResponseEntity<?> getClientById(@PathVariable String id) {
        try {
            ClientDto client = clientService.getClientById(Long.valueOf(id));
            if (client == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("Id not valid", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/all_clients")
    public ResponseEntity<?> getAllClients(
            @RequestParam(value="numberOfPages", defaultValue = "0", required = false) int numberOfPages,
            @RequestParam(value="pageSize", defaultValue = "10", required = false) int pageSize            
    ) {
        try {
            List<ClientDto> clients = clientService.getAllClients(numberOfPages,pageSize);
            if (clients.isEmpty()) {
                return new ResponseEntity<>("Clients not found", HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(clients, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/delete_by_id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        try {
            boolean deleted = clientService.deleteClientById(Long.valueOf(id));
            if (deleted) {
                return new ResponseEntity<>("Client deleted successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Client not found.", HttpStatus.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("Id not valid", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/update_by_id/{id}")
    public ResponseEntity<?> updateById(@PathVariable String id, @RequestBody ClientDto clientDetails) {
        try {
            ClientDto updatedClient = clientService.updateClientById(Long.valueOf(id), clientDetails);
            if (updatedClient == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(updatedClient, HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("Id not valid", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
