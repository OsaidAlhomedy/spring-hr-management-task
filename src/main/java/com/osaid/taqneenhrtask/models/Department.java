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

    private String departmentName;


    @OneToMany(mappedBy = "department")
    private List<Employee> employeeList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
