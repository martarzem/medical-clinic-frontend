package com.crud.medicalclinicfrontend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    private long id;
    private String name;
    private String lastname;
    private long pesel;
}
