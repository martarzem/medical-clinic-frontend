package com.crud.medicalclinicfrontend.service;

import com.crud.medicalclinicfrontend.domain.Patient;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Data
@Service
public class PatientService {
    private static PatientService patientService;

    private RestTemplate restTemplate = new RestTemplate();

    public static PatientService getInstance() {
        if (patientService == null) {
            patientService = new PatientService();
        }
        return patientService;
    }

    public List<Patient> getAll() {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/medical_clinic/patient")
                .build().encode().toUri();
        Patient[] patients = restTemplate.getForObject(url, Patient[].class);
        return Arrays.asList(ofNullable(patients).orElse(new Patient[0]));
    }

    public Set findByLastname(String lastname) {
        return getAll().stream()
                .filter(patient -> patient.getLastname().contains(lastname))
                .collect(Collectors.toSet());
    }

    public void updatePatient(Patient patient) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/medical_clinic/patient")
                .build().encode().toUri();
        restTemplate.put(String.valueOf(url), patient, Patient.class);
    }

    public void deletePatient(Patient patient) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/medical_clinic/patient")
                .build().encode().toUri();
        restTemplate.delete(url + "/" + patient.getId(), Patient.class);
    }
}
