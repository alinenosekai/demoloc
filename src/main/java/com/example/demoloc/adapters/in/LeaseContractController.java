package com.example.demoloc.adapters.in;

import com.example.demoloc.adapters.in.exception.ResourceNotFoundException;
import com.example.demoloc.domain.model.LeaseContract;
import com.example.demoloc.domain.model.LeaseContractFull;
import com.example.demoloc.domain.service.LeaseContractService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;

@RestController
@RequestMapping("/leasecontracts")
@Tag(name = "Lease Contracts", description = "API pour gérer les contrats de location")
public class LeaseContractController {

    private final LeaseContractService leaseContractService;

    public LeaseContractController(LeaseContractService leaseContractService) {
        this.leaseContractService = leaseContractService;
    }

    @Operation(summary = "Rechercher les contrats de location par ID client")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contrats récupérés avec succès"),
        @ApiResponse(responseCode = "400", description = "Paramètre invalide")
    })
    @GetMapping("/findByIdCustomer")
    public ResponseEntity<List<LeaseContract>> getByIdCustomer(
        @Parameter(description = "ID du client", required = true)
        @RequestParam("idCustomer") Integer id) {
  
        List<LeaseContract> contracts = leaseContractService.findByIdCustomer(id);

        if (contracts.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }

        return ResponseEntity.ok(contracts); 
    }

    @Operation(summary = "Rechercher les contrats de location par ID voiture")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contrats récupérés avec succès"),
        @ApiResponse(responseCode = "400", description = "Paramètre invalide")
    })
    @GetMapping("/findByIdCar")
    public ResponseEntity<List<LeaseContract>> getByIdCar(
        @Parameter(description = "ID de la voiture", required = true)
        @RequestParam("idCar") String id) {
        
        List<LeaseContract> contracts = leaseContractService.findByIdCar(id);
    
        if (contracts.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }

        return ResponseEntity.ok(contracts); 
    }

    @Operation(summary = "Ajouter un nouveau contrat de location")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contrat ajouté avec succès"),
        @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping("/add")
    public ResponseEntity<Integer> addLeaseContract(
        @Parameter(description = "Données du contrat de location complet", required = true)
        @RequestBody LeaseContractFull newLeaseContractFull) {

        try {
            LeaseContract leaseContractSaved = leaseContractService.addLeaseContract(newLeaseContractFull);
            return ResponseEntity.status(HttpStatus.CREATED).body(leaseContractSaved.getId());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(404);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(500);
        }
    }

    @Operation(summary = "Modifier le statut d'un contrat de location")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Statut modifié avec succès"),
        @ApiResponse(responseCode = "400", description = "Paramètres invalides"),
        @ApiResponse(responseCode = "404", description = "Contrat non trouvé")
    })
    @PostMapping("/changestatus/{id}")
    public ResponseEntity<Void> changeStatus(
        @Parameter(description = "ID du contrat de location", required = true)
        @PathVariable Integer id, 
        @Parameter(description = "Nouveau statut", required = true)
        @RequestParam("status") String newStatus) {
        
        try {
            leaseContractService.changeStatus(id, newStatus);
             return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}





