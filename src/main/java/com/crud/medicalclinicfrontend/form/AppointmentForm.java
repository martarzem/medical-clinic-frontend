package com.crud.medicalclinicfrontend.form;

import com.crud.medicalclinicfrontend.domain.Appointment;
import com.crud.medicalclinicfrontend.domain.Office;
import com.crud.medicalclinicfrontend.service.AppointmentService;
import com.crud.medicalclinicfrontend.view.AppointmentView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import lombok.Data;


@Data
public class AppointmentForm extends FormLayout {
    private AppointmentView appointmentView;
    private AppointmentService appointmentService = AppointmentService.getInstance();

    private TextField office = new TextField("Office");
    private TextField patient = new TextField("Patient");
    private TextField doctor = new TextField("Doctor");
    private TextField date = new TextField("Date");
    private TextField status = new TextField("Status");
    private TextField purposes = new TextField("Purposes");


    private Button update = new Button("Update");
    private Button delete = new Button("Delete");

    private Binder<Appointment> binder = new Binder<>(Appointment.class);

    private Appointment.AppointmentBuilder appointment = new Appointment.AppointmentBuilder();

    public AppointmentForm(AppointmentView appointmentView) {
        this.appointmentView = appointmentView;

        HorizontalLayout buttons = new HorizontalLayout(update, delete);
        update.addClickListener(event -> updateAppointment());
        delete.addClickListener(event -> deleteAppointment());

        add(office, patient, doctor, date, status, purposes, buttons);

        binder.bindInstanceFields(this);
    }

    private void updateAppointment() {
        Appointment appointment = binder.getBean();
        appointmentService.updateAppointment(appointment);
        appointmentView.refresh();
    }

    private void deleteAppointment() {
        Appointment appointment = binder.getBean();
        appointmentService.deleteAppointment(appointment);
        appointmentView.refresh();
    }

    public void setAppointment(Appointment appointment) {
        binder.setBean(appointment);
    }
}
