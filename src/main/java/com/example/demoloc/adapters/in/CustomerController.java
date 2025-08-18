package com.example.demoloc.adapters.in;

import com.example.demoloc.domain.model.Customer;
import com.example.demoloc.domain.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@Tag(name = "Customers", description = "API pour gérer les clients")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Rechercher des clients par prénom et nom")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Clients récupérés avec succès"),
        @ApiResponse(responseCode = "400", description = "Paramètres invalides")
    })
    @GetMapping("/findByFirstNameAndLastName")
    public ResponseEntity<List<Customer>> getByFirstNameAndLastName(
        @Parameter(description = "Prénom du client", required = true)
        @RequestParam("firstName") String firstName, 
        @Parameter(description = "Nom du client", required = true)
        @RequestParam("lastName") String lastName) {

        List<Customer> customers = customerService.findByFirstNameAndLastName(firstName, lastName);

        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(customers);
    }

    @Operation(summary = "Rechercher des clients par nom complet")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Clients récupérés avec succès"),
        @ApiResponse(responseCode = "400", description = "Paramètre invalide")
    })

    @GetMapping("/findByFullName")
    public ResponseEntity<List<Customer>> findByFullName(
        @Parameter(description = "Nom complet du client", required = true) 
        @RequestParam("fullName") String fullName) {

        List<Customer> customers = customerService.findByFullName(fullName);

        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(customers);
    }

    @Operation(summary = "Rechercher des clients par email")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Clients récupérés avec succès"),
        @ApiResponse(responseCode = "400", description = "Paramètre invalide")
    })

    @GetMapping("/findByEmail")
    public ResponseEntity<List<Customer>> findByEmail(
        @Parameter(description = "email du client", required = true) 
        @RequestParam("email") String email) {

        List<Customer> customers = customerService.findByEmail(email);

        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(customers);
    }

    @Operation(summary = "Ajouter un nouveau client")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Client ajouté avec succès"),
        @ApiResponse(responseCode = "400", description = "Données client invalides")
    })
    @PostMapping("/add")
    public ResponseEntity<Integer> addCustomer(
        @Parameter(description = "Données du client à ajouter", required = true)
        @RequestBody Customer newCustomer) {

        try {    
            Customer customerSaved = customerService.addCustomer(newCustomer.getLastName(), 
                                                                newCustomer.getFirstName(), 
                                                                newCustomer.getAddress(),
                                                                newCustomer.getEmail(),
                                                                newCustomer.getPhone(),
                                                                newCustomer.getDateBirth(), 
                                                                newCustomer.getLicenseCar());
            return ResponseEntity.status(HttpStatus.CREATED)
                                .body(customerSaved.getId());

        } catch (IllegalArgumentException e) { 
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }                    
    }
}
