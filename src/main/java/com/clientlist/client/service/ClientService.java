package com.clientlist.client.service;

import com.clientlist.client.client.PostClient;
import com.clientlist.client.model.Client;
import com.clientlist.client.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PostClient postClient;

    @Transactional
    public Optional<Client> getClientById(Long id) {
        try {
            return clientRepository.findById(id);
        } catch (Exception e){
            throw new RuntimeException("Error retrieving client", e);
        }
    }

    @Transactional
    public Page<Client> getAllClientInPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 10, Sort.by(Sort.Direction.ASC, "id"));
        return clientRepository.findAll(pageable);
    }

    @Transactional
    public Client createClient(Client client) {
        try {
            return clientRepository.save(client);
        } catch (Exception e) {
            throw new RuntimeException("Error creating client", e);
        }
    }

    @Transactional
    public Client updateClient(Long id, Client client) {
        try {
            Client existClient = clientRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("Client not found")
            );
            existClient.setName(client.getName());
            existClient.setEmail(client.getEmail());
            existClient.setGender(client.getGender());

            return clientRepository.save(existClient);
        } catch (Exception e) {
            throw new RuntimeException("Error updating client", e);
        }
    }

    public String testThirdPartyApi() {
        return postClient.getPosts();
    }
}
