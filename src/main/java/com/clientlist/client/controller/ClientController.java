package com.clientlist.client.controller;

import com.clientlist.client.model.Client;
import com.clientlist.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(
            @PathVariable("id") Long id
    ) {
        try {
            Client client = new Client();

            if (clientService.getClientById(id).isPresent()) {
                client = clientService.getClientById(id).get();
            }

            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Client>> getAllClientInPage(
            @RequestParam(value = "page", defaultValue = "0") int pageNumber
    ) {
        try {
            return new ResponseEntity<>(clientService.getAllClientInPage(pageNumber), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Client> createClient(
            @RequestBody Client client
    ) {
        try {
            Client newClient = clientService.createClient(client);
            return new ResponseEntity<>(newClient, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(
            @PathVariable("id") Long id,
            @RequestBody Client client
    ) {
        try {
            Client updatedClient = clientService.updateClient(id, client);
            return new ResponseEntity<>(updatedClient, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/test-third-party")
    public ResponseEntity<String> testThirdPartyApi() {
        try {
            String post = clientService.testThirdPartyApi();
            return new ResponseEntity<>(post, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
