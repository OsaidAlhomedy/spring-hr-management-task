package com.osaid.taqneenhrtask.controllers;

import com.osaid.taqneenhrtask.models.Employee;
import com.osaid.taqneenhrtask.models.Leave;
import com.osaid.taqneenhrtask.repositries.EmployeeRepo;
import com.osaid.taqneenhrtask.repositries.LeavesRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import javax.persistence.PostLoad;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeRepo employeeRepo;
    private final LeavesRepo leavesRepo;

    @Autowired
    public EmployeeController(EmployeeRepo employeeRepo,LeavesRepo leavesRepo) {
        this.employeeRepo = employeeRepo;
        this.leavesRepo = leavesRepo;
    }

    @GetMapping("/getOne")
    public Employee getOneEmployee(@RequestParam(name = "name") String name, @RequestBody @Nullable String body) { //added the get body to show you that it works :0
        System.out.println(body); // <<<==== Add a body to your GET request then checkout the terminal
        return employeeRepo.findEmployeeByName(name);
    }

    @PostMapping("/addOne")
    public Employee addOneEmployee(@RequestBody Employee employee) {

        Employee employee1 = new Employee();
        employee1.setName(employee.getName());
        employee1.setSalary(employee.getSalary());

        employeeRepo.save(employee1);
        return employeeRepo.findEmployeeByName(employee.getName());
    }

    @PutMapping("/updateOne/{name}")
    public Employee updateOneEmployee(@PathVariable String name, @RequestBody Employee employee) {

        Employee employee1 = employeeRepo.findEmployeeByName(name);
        employee1.setName(employee.getName());
        employee1.setSalary(employee.getSalary());
        employee1.setDepartment(employee.getDepartment());

        employeeRepo.save(employee1);
        return employeeRepo.findEmployeeByName(employee.getName());
    }

    @DeleteMapping("/deleteOne/{name}")
    public ResponseEntity<String> deleteOneEmployee(@PathVariable String name) {
        Employee employee1 = employeeRepo.findEmployeeByName(name);
        employeeRepo.delete(employee1);
        return new ResponseEntity<>("Employee has been deleted", HttpStatus.valueOf(204));
    }

    @PostMapping("/requestLeave/{employeeName}")
    public ResponseEntity<String> requestLeave(@PathVariable String employeeName, @RequestBody Leave leave) {
        Employee employee1 = employeeRepo.findEmployeeByName(employeeName);
        leave.setEmployee(employee1);
        var savedLeave = leavesRepo.save(leave);
        System.out.println(savedLeave);
        employeeRepo.save(employee1);
        return new ResponseEntity<>("{\"nice\":\"Wait for manager \"}", HttpStatus.valueOf(204));
    }

    @GetMapping("/getLeavesByDate/{employeeName}")
    public List<Leave> getAllLeavesBetweenDate(@PathVariable String employeeName, @RequestParam Date from, @RequestParam Date to){
        Employee employee = employeeRepo.findEmployeeByName(employeeName);
        var leaveList = employee.getLeavesList();
        var list = leaveList.stream()
                .filter(leave -> from.compareTo(leave.getDate()) < 0 && to.compareTo(leave.getDate()) > 0)
                .collect(Collectors.toList());
        return list;
    }

}
