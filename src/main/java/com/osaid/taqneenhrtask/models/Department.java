package com.osaid.taqneenhrtask.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    private Manager manager;

    @Column(unique = true,nullable = false)
    private String departmentName;

    @OneToMany(mappedBy = "department")
    private List<Employee> employeeList;

    @ManyToOne
    private Directorate directorate;

}
