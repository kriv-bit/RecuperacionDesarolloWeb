package com.recuperacion.korl.service.impl;

import com.recuperacion.korl.dto.EmployeeRequest;
import com.recuperacion.korl.dto.EmployeeResponse;
import com.recuperacion.korl.entity.Department;
import com.recuperacion.korl.entity.Employee;
import com.recuperacion.korl.exception.ResourceNotFoundException;
import com.recuperacion.korl.repository.DepartmentRepository;
import com.recuperacion.korl.repository.EmployeeRepository;
import com.recuperacion.korl.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Complete implementation of EmployeeService.
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(
            EmployeeRepository repository,
            DepartmentRepository departmentRepository) {

        this.repository = repository;
        this.departmentRepository = departmentRepository;
    }

    /**
     * CREATE employee with Department relation
     */
    @Override
    public EmployeeResponse create(EmployeeRequest request) {

        Department department = departmentRepository
                .findById(request.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found"));

        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setSalary(request.getSalary());
        employee.setDepartment(department);

        Employee saved = repository.save(employee);

        return toResponse(saved);
    }

    /**
     * GET ALL employees
     */
    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponse> list() {

        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    /**
     * GET employee by ID 
     */
    @Override
    @Transactional(readOnly = true)
    public EmployeeResponse getById(Long id) {

        Employee employee = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        return toResponse(employee);
    }

    /**
     * Entity → DTO mapper
     */
    private EmployeeResponse toResponse(Employee employee) {

        EmployeeResponse response = new EmployeeResponse();

        response.setId(employee.getId());
        response.setName(employee.getName());
        response.setEmail(employee.getEmail());
        response.setSalary(employee.getSalary());

        if (employee.getDepartment() != null) {
            response.setDepartmentName(
                    employee.getDepartment().getName()
            );
        }

        return response;
    }
}