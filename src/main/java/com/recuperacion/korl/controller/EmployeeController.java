package com.recuperacion.korl.controller;

import com.recuperacion.korl.dto.EmployeeRequest;
import com.recuperacion.korl.dto.EmployeeResponse;
import com.recuperacion.korl.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing Employee endpoints.
 */
@RestController
@RequestMapping("/api/employees") // Base path API
public class EmployeeController {

    private final EmployeeService service;

    /**
     * Constructor injection for service layer.
     */
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    /**
     * Create new employee.
     * Returns HTTP 201 Created.
     * Automatically validates request body.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse create(
            @Valid @RequestBody EmployeeRequest request) {

        return service.create(request);
    }

    /**
     * List all employees.
     * Returns JSON array.
     */
    @GetMapping
    public List<EmployeeResponse> list() {
        return service.list();
    }
    
}