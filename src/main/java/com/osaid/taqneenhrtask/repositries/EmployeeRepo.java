package com.osaid.taqneenhrtask.repositries;

import com.osaid.taqneenhrtask.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository @Transactional
public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    Employee findEmployeeByName(String name);
}
