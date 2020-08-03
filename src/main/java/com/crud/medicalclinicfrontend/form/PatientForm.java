package com.crud.medicalclinicfrontend.form;

import com.crud.medicalclinicfrontend.domain.Patient;
import com.crud.medicalclinicfrontend.service.PatientService;
import com.crud.medicalclinicfrontend.view.PatientView;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToLongConverter;
import lombok.Data;


@Data
public class PatientForm extends FormLayout {
    private PatientView patientView;
    private PatientService patientService = PatientService.getInstance();

    private TextField name = new TextField("Name");
    private TextField lastname = new TextField("Lastname");
    private TextField pesel = new TextField("Pesel");

    private Button update = new Button("Update");
    private Button delete = new Button("Delete");

    private Binder<Patient> binder = new Binder<>(Patient.class);

    private Patient patient = new Patient();

    public PatientForm(PatientView patientView) {
        this.patientView = patientView;

        HorizontalLayout buttons = new HorizontalLayout(update, delete);
        update.addClickListener(event -> updatePatient());
        delete.addClickListener(event -> deletePatient());

        add(name, lastname, pesel, buttons);

        binder.forField(pesel).withConverter(new StringToLongConverter(""))
                .bind(Patient::getPesel, Patient::setPesel);
        binder.bindInstanceFields(this);
    }

    private void updatePatient() {
        Patient patient = binder.getBean();
        patientService.updatePatient(patient);
        patientView.refresh();
    }

    private void deletePatient() {
        Patient patient = binder.getBean();
        patientService.deletePatient(patient);
        patientView.refresh();
    }

    public void setPatient(Patient patient) {
        binder.setBean(patient);
    }
}
