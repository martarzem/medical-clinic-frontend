package com.crud.medicalclinicfrontend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Office {
    private long id;
    private int number;
    private String description;
}
