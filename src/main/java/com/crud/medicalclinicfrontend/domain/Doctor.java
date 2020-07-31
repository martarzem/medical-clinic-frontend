package com.crud.medicalclinicfrontend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    private long id;
    private String name;
    private String lastname;
    private String specialisation;
    private String review;
}
