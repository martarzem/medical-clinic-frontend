package com.crud.medicalclinicfrontend.view;

import com.crud.medicalclinicfrontend.domain.Appointment;
import com.crud.medicalclinicfrontend.form.AppointmentForm;
import com.crud.medicalclinicfrontend.service.AppointmentService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("medical_clinic/appointment")
public class AppointmentView extends VerticalLayout {
    private AppointmentService appointmentService = AppointmentService.getInstance();
    private Grid appointmentGrid = new Grid<>(Appointment.class);
    private TextField filter = new TextField();

    private AppointmentForm appointmentForm = new AppointmentForm(this);

    public AppointmentView() {
        filter.setPlaceholder("Filter by doctor's lastname...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(event -> findByDoctorLastname());
        appointmentGrid.setColumns("id", "office", "patient", "doctor", "date", "status", "purposes");

        HorizontalLayout content = new HorizontalLayout(appointmentGrid, appointmentForm);
        content.setSizeFull();
        appointmentGrid.setSizeFull();
        add(filter, content);

        setSizeFull();
        refresh();

        appointmentGrid.asSingleSelect().addValueChangeListener(event ->
                appointmentForm.setAppointment((Appointment) appointmentGrid.asSingleSelect().getValue()));
    }

    public void refresh() {
        appointmentGrid.setItems(appointmentService.getAll());
    }

    public void findByDoctorLastname() {
        appointmentGrid.setItems(appointmentService.findByDoctorLastname(filter.getValue()));
    }
}
