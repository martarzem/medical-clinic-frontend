package com.crud.medicalclinicfrontend.view;

import com.crud.medicalclinicfrontend.domain.Doctor;
import com.crud.medicalclinicfrontend.form.DoctorForm;
import com.crud.medicalclinicfrontend.service.DoctorService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;


@Route("medical_clinic/doctor")
public class DoctorView extends VerticalLayout {
    private DoctorService doctorService = DoctorService.getInstance();
    private Grid doctorGrid = new Grid<>(Doctor.class);
    private TextField filter = new TextField();

    private DoctorForm doctorForm = new DoctorForm(this);

    public DoctorView() {
        filter.setPlaceholder("Filter by lastname...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(event -> findByLastname());
        doctorGrid.setColumns("id", "name", "lastname", "specialisation", "review");

        HorizontalLayout content = new HorizontalLayout(doctorGrid, doctorForm);
        content.setSizeFull();
        doctorGrid.setSizeFull();
        add(filter, content);

        setSizeFull();
        refresh();

        doctorGrid.asSingleSelect().addValueChangeListener(event ->
                doctorForm.setDoctor((Doctor) doctorGrid.asSingleSelect().getValue()));
    }

    public void refresh() {
        doctorGrid.setItems(doctorService.getAll());
    }

    public void findByLastname() {
        doctorGrid.setItems(doctorService.findByLastname(filter.getValue()));
    }
}
