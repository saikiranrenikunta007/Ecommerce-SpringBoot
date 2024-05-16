package com.ecommercewebsite.ecommercewebsite.apiintegration;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@RequiredArgsConstructor
@RestController
public class ApiIntegrationController {

    private final ApiIntegrationService integrationService;

    @GetMapping("/api-integration/users")
    public String getAllUsers() throws IOException {
        return integrationService.getUsers();
    }

    @PostMapping("/api-integration/users")
    public ResponseEntity<String> addUser(@Valid @RequestBody ApiIntegrationRequest apiIntegrationRequest) throws IOException{
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri();
        String response = integrationService.addUser(apiIntegrationRequest);
        return ResponseEntity.created(location).body(response);
    }

    @DeleteMapping("/api-integration/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) throws IOException {
        integrationService.deleteUser(userId);
        return ResponseEntity.status(204).body("Deleted Succesfully");
    }

    @PutMapping("/api-integration/users/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable String userId) throws IOException {
        return ResponseEntity.status(202).body(integrationService.updateUser(userId));
    }

}
