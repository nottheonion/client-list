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
        Client client = new Client();

        if (clientService.getClientById(id).isPresent()) {
            client = clientService.getClientById(id).get();
        }

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Client>> getAllClientInPage(
            @RequestParam(value = "page", defaultValue = "0") int pageNumber
    ) {
        return new ResponseEntity<>(clientService.getAllClientInPage(pageNumber), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(
            @RequestBody Client client
    ) {
        Client newClient = clientService.createClient(client);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(
            @PathVariable("id") Long id,
            @RequestBody Client client
    ) {
        Client updatedClient = clientService.updateClient(id, client);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    @GetMapping("/test-third-party")
    public ResponseEntity<String> testThirdPartyApi() {
        String post = clientService.testThirdPartyApi();
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
}
