package com.osaid.taqneenhrtask.controllers;

import com.osaid.taqneenhrtask.models.Employee;
import com.osaid.taqneenhrtask.models.Leave;
import com.osaid.taqneenhrtask.repositries.DepartmentRepo;
import com.osaid.taqneenhrtask.repositries.EmployeeRepo;
import com.osaid.taqneenhrtask.repositries.LeavesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {


    private final EmployeeRepo employeeRepo;
    private final LeavesRepo leavesRepo;
    private final DepartmentRepo departmentRepo ;


    @Autowired
    public ManagerController(EmployeeRepo employeeRepo,LeavesRepo leavesRepo,DepartmentRepo departmentRepo){
        this.employeeRepo = employeeRepo;
        this.leavesRepo = leavesRepo;
        this.departmentRepo = departmentRepo;
    }

    @GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployee(){
        return employeeRepo.findAll();
    }

    @GetMapping("/getEmployeesByDate")
    public List<Employee> getAllEmployeesByDate(@RequestParam Date from , @RequestParam Date to){
        List<Employee> list = employeeRepo.findAllByDateBetween(from,to);
        return employeeRepo.findAll();
    }

    @GetMapping("/getEmployeesByDep")
    public List<Employee> getAllEmployeesByDep(@RequestParam String depName){
        return departmentRepo.findDepartmentByDepartmentName(depName).getEmployeeList();
    }

    @GetMapping("/getAllLeaves")
    public List<Leave> getAllEmployeesLeaves(){
        return leavesRepo.findAll();
    }

    @PutMapping("/approveLeave/{leaveId}")
    public ResponseEntity<String> approveOneLeave(@PathVariable Long leaveId){
        Leave leave = leavesRepo.getById(leaveId);
        leave.setApproved(true);
        leavesRepo.save(leave);
        return new ResponseEntity<>("The Leave has been approved", HttpStatus.CREATED);
    }

}
