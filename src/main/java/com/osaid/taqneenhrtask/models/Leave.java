package com.osaid.taqneenhrtask.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String reason;
    private Date date;
    private Boolean approved = false;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Leave(String reason,Date date){
        this.reason = reason;
        this.date = date;
    }

}
