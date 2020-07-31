package com.crud.medicalclinicfrontend.domain;

import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
public class Appointment {
    private long id;
    private Office office;
    private Patient patient;
    private Doctor doctor;
    private LocalDate date;
    private String status;
    private List<String> purposes;

    public static class AppointmentBuilder {
        private long id;
        private Office office;
        private Patient patient;
        private Doctor doctor;
        private LocalDate date;
        private String status;
        private List<String> purposes = new ArrayList<>();

        public AppointmentBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public AppointmentBuilder setOffice(Office office) {
            this.office = office;
            return this;

        }

        public AppointmentBuilder setPatient(Patient patient) {
            this.patient = patient;
            return this;
        }

        public AppointmentBuilder setDoctor(Doctor doctor) {
            this.doctor = doctor;
            return this;
        }

        public AppointmentBuilder setDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public AppointmentBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public AppointmentBuilder setPurposes(String purpose) {
            purposes.add(purpose);
            return this;
        }

        public Appointment build() {
            return new Appointment(id, office, patient, doctor, date, status, purposes);
        }
    }

    public Appointment(long id, Office office, Patient patient, Doctor doctor,
                       LocalDate date, String status, List<String> purposes) {
        this.id = id;
        this.office = office;
        this.patient = patient;
        this.doctor = doctor;
        this.status = status;
        this.purposes = new ArrayList<>(purposes);
    }
}
