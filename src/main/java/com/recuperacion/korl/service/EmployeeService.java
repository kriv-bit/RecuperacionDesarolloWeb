package com.recuperacion.korl.service;

import com.recuperacion.korl.dto.EmployeeRequest;
import com.recuperacion.korl.dto.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse create(EmployeeRequest request);

    List<EmployeeResponse> list();

    EmployeeResponse getById(Long id);
}