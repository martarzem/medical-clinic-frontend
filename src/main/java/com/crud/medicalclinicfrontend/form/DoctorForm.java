package com.crud.medicalclinicfrontend.form;

import com.crud.medicalclinicfrontend.domain.Doctor;
import com.crud.medicalclinicfrontend.service.DoctorService;
import com.crud.medicalclinicfrontend.view.DoctorView;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.data.binder.Binder;
import lombok.Data;


@Data
public class DoctorForm extends FormLayout {
    private DoctorView doctorView;
    private DoctorService doctorService = DoctorService.getInstance();

    private TextField name = new TextField("Name");
    private TextField lastname = new TextField("Lastname");
    private TextField specialisation = new TextField("Specialisation");

    private Button update = new Button("Update");
    private Button delete = new Button("Delete");

    private Binder<Doctor> binder = new Binder<>(Doctor.class);

    private Doctor doctor = new Doctor();

    public DoctorForm(DoctorView doctorView) {
        this.doctorView = doctorView;

        HorizontalLayout buttons = new HorizontalLayout(update, delete);
        update.addClickListener(event -> updateDoctor());
        delete.addClickListener(event -> deleteDoctor());

        add(name, lastname, specialisation, buttons);

        binder.bindInstanceFields(this);
    }

    private void updateDoctor() {
        Doctor doctor = binder.getBean();
        doctorService.updateDoctor(doctor);
        doctorView.refresh();
    }

    private void deleteDoctor() {
        Doctor doctor = binder.getBean();
        doctorService.deleteDoctor(doctor);
        doctorView.refresh();
    }

    public void setDoctor(Doctor doctor) {
        binder.setBean(doctor);
    }
}
