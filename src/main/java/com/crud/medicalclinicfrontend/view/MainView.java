package com.crud.medicalclinicfrontend.view;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route("medical_clinic")
public class MainView extends HorizontalLayout {
    private Button goToAppointments = new Button("Appointments");
    private Button goToPatients = new Button("Patients");
    private Button goToDoctors = new Button("Doctors");
    private Button goToOffices = new Button("Offices");

    public MainView() {
        goToAppointments.addClickListener(event -> goToAppointments.getUI().ifPresent(ui ->
                ui.navigate("medical_clinic/appointment")));
        add(goToAppointments);
        goToPatients.addClickListener(event -> goToPatients.getUI().ifPresent(ui ->
                ui.navigate("medical_clinic/patient")));
        add(goToPatients);
        goToDoctors.addClickListener(event -> goToDoctors.getUI().ifPresent(ui ->
                ui.navigate("medical_clinic/doctor")));
        add(goToDoctors);
        goToOffices.addClickListener(event -> goToOffices.getUI().ifPresent(ui ->
                ui.navigate("medical_clinic/office")));
        add(goToOffices);
    }
}