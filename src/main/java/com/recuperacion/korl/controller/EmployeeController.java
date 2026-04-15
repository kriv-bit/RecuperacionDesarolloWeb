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
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    /**
     * CREATE employee
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse create(
            @Valid @RequestBody EmployeeRequest request) {

        return service.create(request);
    }

    /**
     * GET ALL employees
     */
    @GetMapping
    public List<EmployeeResponse> list() {
        return service.list();
    }

    /**
     * GET employee by ID 
     */
    @GetMapping("/{id}")
    public EmployeeResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }
}