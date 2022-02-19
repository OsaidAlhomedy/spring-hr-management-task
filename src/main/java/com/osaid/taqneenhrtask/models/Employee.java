package com.osaid.taqneenhrtask.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true)
    private String name;

    private Float salary;

    @ManyToOne
    private Department department;

    @CreatedDate
    private Date date = new Date();

    @OneToMany(mappedBy = "employee")
    @ToString.Exclude
    private List<Leave> leavesList;
}
