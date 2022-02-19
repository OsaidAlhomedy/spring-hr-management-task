package com.osaid.taqneenhrtask.controllers;

import com.osaid.taqneenhrtask.models.Employee;
import com.osaid.taqneenhrtask.repositries.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostLoad;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeController(EmployeeRepo employeeRepo){
        this.employeeRepo = employeeRepo;
    }

    @GetMapping("/getOne")
    public Employee getOneEmployee(@RequestParam(name = "name") String name){
        return employeeRepo.findEmployeeByName(name);
    }

    @PostMapping("/addOne")
    public Employee addOneEmployee(@RequestBody Employee employee){

        Employee employee1 = new Employee();
        employee1.setName(employee.getName());
        employee1.setSalary(employee.getSalary());

        employeeRepo.save(employee1);
        return employeeRepo.findEmployeeByName(employee.getName());
    }

    @PutMapping("/updateOne/{name}")
    public Employee updateOneEmployee(@PathVariable String name, @RequestBody Employee employee){

        Employee employee1 = employeeRepo.findEmployeeByName(name);
        employee1.setName(employee.getName());
        employee1.setSalary(employee.getSalary());
        employee1.setDepartment(employee.getDepartment());

        employeeRepo.save(employee1);
        return employeeRepo.findEmployeeByName(employee.getName());
    }
}
