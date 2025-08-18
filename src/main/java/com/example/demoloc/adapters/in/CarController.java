package com.example.demoloc.adapters.in;

import com.example.demoloc.domain.model.Car;
import com.example.demoloc.domain.service.CarService;
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
@RequestMapping("/cars")
@Tag(name = "Cars", description = "API pour gérer les voitures")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @Operation(summary = "Liste des voitures disponibles")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès"),
        @ApiResponse(responseCode = "500", description = "Erreur serveur")
    })
    @GetMapping("/available")
    public ResponseEntity<List<Car>> getAvailableCars() {
        List<Car> cars = carService.listAvailableCars();

        if (cars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cars);
    }

    @Operation(summary = "Louer une voiture par son ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Voiture louée avec succès"),
        @ApiResponse(responseCode = "404", description = "Voiture non trouvée"),
        @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PostMapping("/rent/{id}")
    public ResponseEntity<String> rentCar(
                    @Parameter(description = "ID de la voiture à louer", required = true)
                    @PathVariable String id) {

        try {
            Car rentedCar = carService.rentCar(id);
            return ResponseEntity.ok("Voiture louée avec succès");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (RuntimeException e) {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur serveur inattendue");
        }
    }

    @Operation(summary = "Retourner une voiture par son ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Voiture retournée avec succès"),
        @ApiResponse(responseCode = "404", description = "Voiture non trouvée"),
        @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PostMapping("/return/{id}")
    public ResponseEntity<String> returnCar(
        @Parameter(description = "ID de la voiture à retourner", required = true)
        @PathVariable String id) {

        try {
            Car returnedCar = carService.returnCar(id);
            return ResponseEntity.ok("Voiture retournée avec succès");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur serveur inattendue");
        }
    }
}
