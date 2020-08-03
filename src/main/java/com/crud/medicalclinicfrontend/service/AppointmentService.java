package com.crud.medicalclinicfrontend.service;

import com.crud.medicalclinicfrontend.domain.Appointment;
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
public class AppointmentService {
    private static AppointmentService appointmentService;

    private RestTemplate restTemplate = new RestTemplate();

    public static AppointmentService getInstance() {
        if (appointmentService == null) {
            appointmentService = new AppointmentService();
        }
        return appointmentService;
    }

    private AppointmentService() {
    }

    public List<Appointment> getAll() {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/medical_clinic/appointment")
                .build().encode().toUri();
        Appointment[] appointments = restTemplate.getForObject(url, Appointment[].class);
        return Arrays.asList(ofNullable(appointments).orElse(new Appointment[0]));
    }

    public Set findByDoctorLastname(String lastname) {
        return getAll().stream()
                .filter(appointment -> appointment.getDoctor().getLastname().contains(lastname))
                .collect(Collectors.toSet());
    }

    public void updateAppointment(Appointment appointment) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/medical_clinic/appointment")
                .build().encode().toUri();
        restTemplate.put(String.valueOf(url), appointment, Appointment.class);
    }

    public void deleteAppointment(Appointment appointment) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/medical_clinic/appointment")
                .build().encode().toUri();
        restTemplate.delete(url + "/" + appointment.getId(), Appointment.class);
    }
}
