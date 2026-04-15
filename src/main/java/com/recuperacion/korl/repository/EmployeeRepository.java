
package com.recuperacion.korl.repository;

import com.recuperacion.korl.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}