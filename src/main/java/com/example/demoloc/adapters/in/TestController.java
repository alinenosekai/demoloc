package com.example.demoloc.adapters.in;

import com.example.demoloc.domain.model.Car;
import com.example.demoloc.domain.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
@Tag(name = "Tests", description = "API pour tests basiques")
public class TestController {

    @GetMapping(path="/coffee")
	@Operation(summary = "Répond à la demande de café")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "418", description = "I'm a teapot")
    })
	public ResponseEntity<String> coffee() {
		return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT.getReasonPhrase(), HttpStatus.I_AM_A_TEAPOT);
	}

}
