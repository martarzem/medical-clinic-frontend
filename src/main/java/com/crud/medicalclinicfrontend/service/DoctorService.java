package com.crud.medicalclinicfrontend.service;

import com.crud.medicalclinicfrontend.domain.Doctor;
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
public class DoctorService {
    private static DoctorService doctorService;

    private RestTemplate restTemplate = new RestTemplate();

    public static DoctorService getInstance() {
        if (doctorService == null) {
            doctorService = new DoctorService();
        }
        return doctorService;
    }

    private DoctorService() {
    }

    public List<Doctor> getAll() {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/medical_clinic/doctor")
                .build().encode().toUri();
        Doctor[] doctors = restTemplate.getForObject(url, Doctor[].class);
        return Arrays.asList(ofNullable(doctors).orElse(new Doctor[0]));
    }

    public Set findByLastname(String lastname) {
        return getAll().stream()
                .filter(doctor -> doctor.getLastname().contains(lastname))
                .collect(Collectors.toSet());
    }

    public void updateDoctor(Doctor doctor) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/medical_clinic/doctor")
                .build().encode().toUri();
        restTemplate.put(String.valueOf(url), doctor, Doctor.class);
    }

    public void deleteDoctor(Doctor doctor) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/medical_clinic/doctor")
                .build().encode().toUri();
        restTemplate.delete(url + "/" + doctor.getId(), Doctor.class);
    }
}
