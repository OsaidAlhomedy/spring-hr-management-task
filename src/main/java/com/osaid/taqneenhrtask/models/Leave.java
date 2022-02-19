package com.osaid.taqneenhrtask.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String reason;
    private Date date;
    private Boolean approved = false;

    @ManyToOne
    private Employee employee;

    public Leave(String reason,Date date){
        this.reason = reason;
        this.date = date;
    }

}
